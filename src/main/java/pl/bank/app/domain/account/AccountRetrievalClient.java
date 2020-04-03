package pl.bank.app.domain.account;

import pl.bank.app.domain.account.Account;

import java.util.Optional;

public interface AccountRetrievalClient {

    public Optional<Account> findById(Long accountId);

    public Optional<Account> findByAccountNumber(String accountNumber);
}
