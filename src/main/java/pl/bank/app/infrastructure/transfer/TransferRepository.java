package pl.bank.app.infrastructure.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bank.app.api.transfer.TransferRequest;
import pl.bank.app.domain.transfer.Transfer;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    Transfer getTransferByTitle(String transferTitle);
}
