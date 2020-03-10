package pl.bank.app.infrastructure.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bank.app.domain.user.CreateUserClient;
import pl.bank.app.domain.user.User;

@Service
@RequiredArgsConstructor
class CreateUserPostgresClient implements CreateUserClient {

    private final UserRepository userRepository;

    @Override
    public void create(User user) {
        userRepository.save(user);
    }
}
