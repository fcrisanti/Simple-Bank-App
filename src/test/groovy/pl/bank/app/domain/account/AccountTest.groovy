package pl.bank.app.domain.account

import pl.bank.app.domain.transfer.exceptions.NotEnoughAmount
import spock.lang.Specification

class AccountTest extends Specification {
    Account account = Account.builder().amount(BigDecimal.valueOf(1000)).build();

    def "should decrease account credit correctly"() {
        given:
        account.subtractAmount(BigDecimal.valueOf(a));

        expect:
        account.getAmount() == BigDecimal.valueOf(b)

        where:
        a      | b
        1000L   | 0L
        500L    | 500L
        100L    | 900L
        999.99D | 0.01D
    }

    def "Should throw exception when there is no enough balance"() {
        when:
        account.subtractAmount(BigDecimal.valueOf(a));

        then:
        thrown(NotEnoughAmount)

        where:
        a << [100000L, 1000.01D, 1000.00001D]
    }

    def "Should increase balance correctly"() {
        given:
        account.addAmount(BigDecimal.valueOf(a));

        expect:
        account.getAmount() == BigDecimal.valueOf(b)

        where:
        a       | b
        1000L   | 2000L
        500L    | 1500L
        100L    | 1100L
        999.99D | 1999.99D
    }
}
