package pl.bank.app.domain.account;

import pl.bank.app.domain.account.AccountType;

public interface CreateAccountClient {
    void create(Account account);

    void create(long userId, AccountType accountType);
}
