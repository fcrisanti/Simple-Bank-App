package pl.bank.app.infrastructure.user

import pl.bank.app.domain.user.CreateUserCommand
import pl.bank.app.domain.user.User
import pl.bank.app.domain.user.UserRetrievalClient
import spock.lang.Specification

class UserRetrievalPostgresClientTest extends Specification {

    UserRepository userRepository = Mock(UserRepository)
    UserRetrievalClient userRetrievalClient = new UserRetrievalPostgresClient(userRepository)

    static User user1 = User.generateUser(new CreateUserCommand("Francesco","Crisanti","fcrisanti"))
    static User user2 = User.generateUser(new CreateUserCommand("Adam","Spadam","aspadam"))
    static User user3 = User.generateUser(new CreateUserCommand("Jacek","Placek","jplacek"))
    static List<User> users = new ArrayList<>([user1, user2, user3])
    static List<UserResponse> userResponses = new ArrayList<>([user1.generateResponse(),
                                                               user2.generateResponse(),
                                                               user3.generateResponse()]) as List<UserResponse>

    def "should return UserResponse List correctly"() {
        given:
        userRepository.findAll() >> users

        expect:
        userRetrievalClient.findAllUser() == userResponses
    }

    def "should return User by id correctly"() {
        given:
        userRepository.getOne(3) >> user2

        expect:
        userRetrievalClient.findById(3L) == user2
    }
}
