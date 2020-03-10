package pl.bank.app.domain.user;

import java.util.List;
import java.util.Optional;


public interface UserRetrievalClient {

    User findById(Long id);

    Optional<User> findByLogin(String login);

    List<UserResponse> findAllUser();

}