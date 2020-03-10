package pl.bank.app.infrastructure.card;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bank.app.domain.card.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
