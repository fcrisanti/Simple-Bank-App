package pl.bank.app.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountFacade {

    private final CreateAccountClient createAccountClient;
//    private final AccountRetrievalClient accountRetrievalClient;

    public void create(Account account) {
        createAccountClient.create(account);
    }


//    public AccountResponse findByAccountNumber(String accountNumber) {
//        return accountRetrievalClient.findByAccountNumber(accountNumber).generateResponse();
//    }

    public static String generateNewAccountNumber() {
        return AccountNumberGenerator.generate();
    }

    public void createPremium(Long userId) {
        createAccountClient.create(userId,AccountType.PREMIUM);
    }

    public void createStandard(Long userId) {
        createAccountClient.create(userId,AccountType.STANDARD);
    }
}
