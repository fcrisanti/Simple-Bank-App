package pl.bank.app.domain.account;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value
public class AccountResponse {

    long id;
    long userID;
    String accountNumber;
    BigDecimal amount;
    BigDecimal fee;
    AccountType accountType;
}
