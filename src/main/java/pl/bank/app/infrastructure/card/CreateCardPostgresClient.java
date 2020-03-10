package pl.bank.app.infrastructure.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bank.app.domain.account.Account;
import pl.bank.app.domain.account.exceptions.AccountNotFound;
import pl.bank.app.domain.card.CreateCardClient;
import pl.bank.app.domain.card.Card;
import pl.bank.app.infrastructure.account.AccountRepository;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCardPostgresClient implements CreateCardClient {

    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Override
    @Transactional
    public void create(long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFound(String.format("Account %d doesn't exist", accountId)));
        Card card = Card.generateCard(account);
        account.addCard(card);
        cardRepository.save(card);
    }
}
