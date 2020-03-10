package pl.bank.app.api.card;

import pl.bank.app.domain.card.CardFacade;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final CardFacade cardFacade;

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCard(@NonNull @RequestParam long accountId) {
        log.info("Card creation {}", accountId);
        cardFacade.create(accountId);
    }
}
