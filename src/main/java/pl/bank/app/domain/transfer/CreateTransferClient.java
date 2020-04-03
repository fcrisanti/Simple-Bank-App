package pl.bank.app.domain.transfer;

public interface CreateTransferClient {
    void create(Transfer transfer);

    Transfer getTransferByTitle(String transferTitle);
}
