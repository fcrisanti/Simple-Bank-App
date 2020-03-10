package pl.bank.app.domain.transfer.exceptions;

public class NotEnoughAmount extends RuntimeException {
    public NotEnoughAmount(String message) {
        super(message);
    }
}
