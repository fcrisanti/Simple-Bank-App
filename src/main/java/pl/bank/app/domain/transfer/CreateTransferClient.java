package pl.bank.app.domain.transfer;

import pl.bank.app.domain.transfer.Transfer;

import java.util.Set;

public interface CreateTransferClient {
    void create(Transfer transfer);

}
