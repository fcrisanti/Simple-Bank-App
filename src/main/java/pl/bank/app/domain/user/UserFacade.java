package pl.bank.app.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final CreateUserWithBankAccount createUserWithBankAccount;

        public void createUserWithNewBankAccount(CreateUserCommand createUserCommand) {
        createUserWithBankAccount.userOnboardingWithStandardAccount(createUserCommand);
    }

}
