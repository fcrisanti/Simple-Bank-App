package pl.bank.app.infrastructure.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bank.app.domain.transfer.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
