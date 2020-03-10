package pl.bank.app.infrastructure.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bank.app.domain.account.Account;
import pl.bank.app.domain.account.AccountRetrievalClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class AccountRetrievalPostgresClient implements AccountRetrievalClient {

    public final AccountRepository accountRepository;

    @Override
    public Optional<Account> findById(Long accountId) {
       return accountRepository.findById(accountId);
    }
}
