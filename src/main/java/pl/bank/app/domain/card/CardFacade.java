package pl.bank.app.domain.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bank.app.domain.card.CreateCardClient;

@Service
@RequiredArgsConstructor
public class CardFacade {

    private final CreateCardClient createCardClient;

    public void create(long accountId) {
        createCardClient.create(accountId);
    }
}
