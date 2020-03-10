package pl.bank.app.domain.account;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

class AccountNumberGenerator {

    private static final String BANK_CODE = "111";

    public static String generate() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.PL)
                .bankCode(BANK_CODE)
                .buildRandom();
        return iban.toString();
    }
}




