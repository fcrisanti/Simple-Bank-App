package pl.bank.app.infrastructure.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bank.app.domain.user.User;
import pl.bank.app.domain.user.UserResponse;
import pl.bank.app.domain.user.UserRetrievalClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserRetrievalPostgresClient implements UserRetrievalClient {
    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<UserResponse> findAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(User::generateResponse)
                .collect(Collectors.toList());
    }
}

