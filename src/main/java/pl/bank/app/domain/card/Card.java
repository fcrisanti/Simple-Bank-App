package pl.bank.app.domain.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.bank.app.domain.account.Account;
import pl.bank.app.shared.Auditable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Card extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "card_sequence")
    @SequenceGenerator(name = "card_sequence")
    private Long id;

    private String cardNumber;
    private String cvv;
    private LocalDate expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @Setter
    private Account account;

    public static Card generateCard(Account account) {
        return Card.builder()
                .cardNumber("1234")
                .cvv("123")
                .account(account)
                .expirationDate(LocalDate.now().plusYears(5))
                .build();
    }
}
