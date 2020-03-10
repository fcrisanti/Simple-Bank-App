package pl.bank.app.api.account;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.bank.app.domain.account.AccountFacade;
import pl.bank.app.domain.account.AccountType;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountFacade accountFacade;

    @PostMapping(path = "/premium/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPremiumAccount(@NonNull @RequestParam long userId) {
        log.info("Create premium account for {}", userId);
        accountFacade.createPremium(userId);
    }

    @PostMapping(path = "/standard/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStandardAccount(@NonNull @RequestParam long userId) {
        log.info("Create standard account for {}", userId);
        accountFacade.createStandard(userId);
    }
}
