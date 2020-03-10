package pl.bank.app.domain.transfer
import pl.bank.app.domain.account.Account
import pl.bank.app.domain.account.AccountType
import pl.bank.app.domain.transfer.exceptions.UserNotFound
import pl.bank.app.domain.account.AccountRetrievalClient
import pl.bank.app.domain.user.CreateUserCommand
import pl.bank.app.domain.user.User
import pl.bank.app.domain.user.UserRetrievalClient
import spock.lang.Shared
import spock.lang.Specification


class TransferExecutorTest extends Specification {

    public final CreateTransferClient createTransferClient = Mock(CreateTransferClient)
    public final UserRetrievalClient userRetrievalClient = Mock(UserRetrievalClient)
    public final AccountRetrievalClient accountRetrievalClient = Mock(AccountRetrievalClient)
    public final TransferRetrievalClient transferRetrievalClient = Mock(TransferRetrievalClient)
    TransferExecutor transferExecutor = new TransferExecutor(createTransferClient ,userRetrievalClient,accountRetrievalClient,transferRetrievalClient)

    @Shared
    CreateUserCommand command = new CreateUserCommand("Francesco", "Crisanti", "Franek")
    User user = User.generateUser(command)
    Account account = Account.generateAccount(user, AccountType.STANDARD)
    Optional<Account> maybeAccount = Optional.of(account)

    def "checking whether user has an account"() {
        given:

        user.addAccount(account)

        when:
        transferExecutor.userHasAccountOrThrow(user,account)

        then:
        notThrown(UserNotFound)
    }

    def "should throw exception when if user has no account"() {
        given:


        when:
        transferExecutor.userHasAccountOrThrow(user, account)

        then:
        thrown(UserNotFound)
    }

    def "should subtract from account when transfer is processed"() {
        given:
        user.addAccount(account)
        userRetrievalClient.findById(user.getId()) >> user
        accountRetrievalClient.findById(account.getId()) >> maybeAccount
        BigDecimal AmountToSubtract = BigDecimal.TEN;
        Transfer transfer = new Transfer(1,user.getId(),account.getId(),account.getAccountNumber(),"test",AmountToSubtract)

        when:
        transferExecutor.executeAndSaveOrThrow(transfer)

        then:
        account.getAmount() == account.getINITIAL_AMOUNT() - AmountToSubtract
    }
}
