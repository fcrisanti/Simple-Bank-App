package pl.bank.app.domain.transfer;

import pl.bank.app.api.transfer.TransferRequest;
import pl.bank.app.domain.transfer.CreateTransferCommand;

import java.util.List;

public interface TransferRetrievalClient {

    List<CreateTransferCommand> getTransferRequests();

}
