package pl.bank.app.domain.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferFacade {
    private final TransferExecutor transferExecutor;

    public void executeTransfer(CreateTransferCommand createTransferCommand) {
        Transfer transfer = transferExecutor.create(createTransferCommand);
        transferExecutor.executeAndSaveOrThrow(transfer);
    }

    public void executePendingTransfers() {
        transferExecutor.executePendingTransfers();
    }
}
