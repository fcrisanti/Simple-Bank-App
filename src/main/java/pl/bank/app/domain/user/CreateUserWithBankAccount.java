package pl.bank.app.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bank.app.domain.account.Account;
import pl.bank.app.domain.account.AccountType;
import pl.bank.app.domain.account.CreateAccountClient;
import pl.bank.app.domain.user.exceptions.LoginAlreadyExistsException;


@Service
@RequiredArgsConstructor
class CreateUserWithBankAccount {

    public final CreateAccountClient createAccountClient;
    public final CreateUserClient createUserClient;
    public final UserRetrievalClient userRetrievalClient;

    private void ifLoginExistsThrow(String login) {
        if (userRetrievalClient.findByLogin(login).isPresent()) {
            throw new LoginAlreadyExistsException(String.format("This login is taken. Cannot create user %s", login));
        }
    }

    public void userOnboardingWithStandardAccount(CreateUserCommand createUserCommand) {
        ifLoginExistsThrow(createUserCommand.getLogin());
        User user = User.generateUser(createUserCommand);
        Account account = Account.generateAccount(user, AccountType.STANDARD);
        user.addAccount(account);
        createUserClient.create(user);
    }

//    public void createEmptyAccount(CreateUserCommand createUserCommand) {
//        ifLoginExistsThrow(createUserCommand.getLogin());
//        User user = User.generateUser(createUserCommand);
//        Account account = Account.generateAccount();
//        user.isExist(account);
//        createUserClient.create(user);
//    }
}
