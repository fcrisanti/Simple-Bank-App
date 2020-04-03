package pl.bank.app.domain.account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.bank.app.domain.card.Card;
import pl.bank.app.domain.transfer.exceptions.NotEnoughAmount;
import pl.bank.app.domain.user.User;
import pl.bank.app.shared.Auditable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class Account extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "account_sequence")
    @SequenceGenerator(name = "account_sequence")
    private Long id;

    private String accountNumber;

    private final BigDecimal INITIAL_AMOUNT = BigDecimal.valueOf(1000); //FOR TESTING PURPOSE
    private final BigDecimal STANDARD_FEE = BigDecimal.ZERO; //FOR TESTING PURPOSE
    private final BigDecimal PREMIUM_FEE = BigDecimal.valueOf(5); //FOR TESTING PURPOSE

    @Setter(AccessLevel.PRIVATE)
    private BigDecimal amount;

    private BigDecimal fee;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users")
    @Setter
    private User user;

    public static Account generateAccount(User user, AccountType accountType) {
        Account account = Account.builder()
                .accountType(accountType)
                .user(user)
                .accountNumber(AccountFacade.generateNewAccountNumber())
                .build();
        account.setAmount(account.INITIAL_AMOUNT);
        if(accountType==AccountType.PREMIUM) {
            account.setFee(account.STANDARD_FEE);
        } else account.setFee(account.PREMIUM_FEE);
        return account;
    }

    @OneToMany(mappedBy ="account",
            cascade = CascadeType.ALL)
    private Set<Card> cards = new HashSet<>();

    public boolean isEnoughMoneyToPay(BigDecimal payment) {
        return (amount.subtract(payment).compareTo(BigDecimal.ZERO) >= 0);
    }

    public void subtractAmount(BigDecimal payment) {
        accountHasAmountOrThrow(payment);
        this.amount = amount.subtract(payment);
    }

    public void addAmount(BigDecimal addition) {
        this.amount = amount.add(addition);
    }

    public void accountHasAmountOrThrow(BigDecimal amount) throws NotEnoughAmount {
        if (!this.isEnoughMoneyToPay(amount))
            throw new NotEnoughAmount(String.format("Account %d does not have enough money. Has %d, should be >= %d", this.getId(), this.getAmount().intValue(), amount.intValue()));
    }

    public void addCard(Card card) {
        cards.add(card);
        card.setAccount(this);
    }
}
