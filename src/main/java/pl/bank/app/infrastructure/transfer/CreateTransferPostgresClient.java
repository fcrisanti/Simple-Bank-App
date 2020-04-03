package pl.bank.app.infrastructure.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bank.app.api.transfer.TransferRequest;
import pl.bank.app.domain.transfer.Transfer;
import pl.bank.app.domain.transfer.CreateTransferClient;

@Service
@RequiredArgsConstructor
class CreateTransferPostgresClient implements CreateTransferClient {
    private final TransferRepository transferRepository;

    @Override
    public void create(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public Transfer getTransferByTitle(String transferTitle) {
       return transferRepository.getTransferByTitle(transferTitle);
    }
}
