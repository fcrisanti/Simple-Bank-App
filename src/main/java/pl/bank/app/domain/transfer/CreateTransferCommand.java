package pl.bank.app.domain.transfer;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
public
class CreateTransferCommand {

    @NonNull
    private Long auctionOwnerId;

    @NonNull
    private Long auctionOwnerAccountId;

    @NonNull
    private String auctionWinnerAccountNumber;

    @NonNull
    private String title;

    @NonNull
    private BigDecimal amount;
}
