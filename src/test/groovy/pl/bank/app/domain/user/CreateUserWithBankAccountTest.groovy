package pl.bank.app.domain.user


import pl.bank.app.domain.account.CreateAccountClient
import pl.bank.app.domain.user.exceptions.LoginAlreadyExistsException
import spock.lang.Specification

class CreateUserWithBankAccountTest extends Specification {

    CreateAccountClient createAccountClient = Mock(CreateAccountClient);
    CreateUserClient createUserClient = Mock(CreateUserClient);
    UserRetrievalClient userRetrievalClient = Mock(UserRetrievalClient);
    CreateUserWithBankAccount createUserWithAccount = new CreateUserWithBankAccount(createAccountClient, createUserClient, userRetrievalClient)
    CreateUserCommand createUserCommand = Mock(CreateUserCommand)
    Optional<User> maybeUser = Optional.of(new User(123, "Francesco", "Crisanti", null, "Franek"))

    def "creation of a user with bank account existing login"() {
        //testujemy próbę utworzenia dwóch użytkowników o tym samym loginie
        given:

        String login = "test"
        createUserCommand.getLogin() >> login
        userRetrievalClient.findByLogin(login) >> maybeUser

        when:
        createUserWithAccount.userOnboardingWithStandardAccount(createUserCommand)

        then:
        thrown(LoginAlreadyExistsException)
    }

    def "creation of a user with bank account new login"() {
        //testujemy próbę utworzenia użytkownika o nowym loginie
        given:

        String login = "test"
        createUserCommand.getLogin() >> login
        userRetrievalClient.findByLogin(login) >> Optional.empty()

        when:
        createUserWithAccount.userOnboardingWithStandardAccount(createUserCommand)

        then:
        notThrown(LoginAlreadyExistsException)
    }
}
