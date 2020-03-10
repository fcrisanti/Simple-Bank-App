package pl.bank.app.domain.transfer;

import lombok.Builder;
import lombok.Getter;
import pl.bank.app.api.transfer.TransferRequest;

@Builder
@Getter
public class CreateTransferCommandMapper {

    public static CreateTransferCommand map(TransferRequest transferRequest) {
        return CreateTransferCommand.builder()
                .title(transferRequest.getTitle())
                .auctionOwnerId(transferRequest.getAuctionOwnerId())
                .auctionOwnerAccountId(transferRequest.getAuctionOwnerAccountId())
                .auctionWinnerAccountNumber(transferRequest.getAuctionWinnerAccountNumber())
                .amount(transferRequest.getAmount())
                .build();
    }
}
