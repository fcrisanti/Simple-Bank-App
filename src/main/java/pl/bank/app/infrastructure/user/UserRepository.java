package pl.bank.app.infrastructure.user;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.bank.app.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
