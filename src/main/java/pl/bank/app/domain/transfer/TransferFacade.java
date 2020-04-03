package pl.bank.app.domain.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bank.app.api.transfer.TransferRequest;

import java.util.List;

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

    public List<CreateTransferCommand> sendBackExecutedTransfersList(List<TransferRequest> transferRequests) {
      return transferExecutor.checkTransfersList(transferRequests);
    }
}
