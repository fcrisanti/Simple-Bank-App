package pl.bank.app.api.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Builder
@JsonDeserialize(builder = TransferRequest.TransferRequestBuilder.class)
public class TransferRequest {

    @Min(1)
    @JsonProperty("ownerId")
    private Long auctionOwnerId;

    @Min(1)
    @JsonProperty("ownerAccountId")
    private Long auctionOwnerAccountId;

    @NotNull
    @NotEmpty
    @JsonProperty("clientAccountNumber")
    private String auctionWinnerAccountNumber;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @JsonProperty("totalPrice")
    private BigDecimal amount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TransferRequestBuilder {
    }
}
