package pl.bank.app

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.bank.app.api.account.AccountController
import pl.bank.app.api.card.CardController
import pl.bank.app.api.transfer.TransferController
import pl.bank.app.api.user.UserController
import spock.lang.Specification

@SpringBootTest(classes = BankApplication.class)
class LoadContextTest extends Specification {

    @Autowired (required = false)
    private UserController userController

    @Autowired (required = false)
    private CardController cardController

    @Autowired (required = false)
    private TransferController transferController

    @Autowired (required = false)
    private AccountController accountController

    def "Bean creation test"() {
        expect:
        userController
        cardController
        transferController
        accountController
    }
}