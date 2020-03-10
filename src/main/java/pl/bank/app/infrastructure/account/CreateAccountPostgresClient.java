package pl.bank.app.infrastructure.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bank.app.domain.account.Account;
import pl.bank.app.domain.account.AccountType;
import pl.bank.app.domain.account.CreateAccountClient;
import pl.bank.app.domain.user.User;
import pl.bank.app.infrastructure.user.UserRepository;

@Service
@RequiredArgsConstructor
class CreateAccountPostgresClient implements CreateAccountClient {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public void create(Account account) {
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void create(long userId, AccountType accountType) {
        User user = userRepository.getOne(userId);
        Account account = Account.generateAccount(user, accountType);
        user.addAccount(account);
        accountRepository.save(account);
    }

}
