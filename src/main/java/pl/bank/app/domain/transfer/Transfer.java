package pl.bank.app.domain.transfer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.bank.app.shared.Auditable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PRIVATE)
public
class Transfer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "transfer_sequence")
    @SequenceGenerator(name = "transfer_sequence")
    private Long id;

    private Long auctionOwnerId;

    private Long auctionOwnerAccountId;

    private String auctionWinnerAccountNumber;

    private String title;

    private BigDecimal amount;

    public static Transfer generate(CreateTransferCommand createTransferCommand) {
        Transfer transfer = new Transfer();
        transfer.setAuctionOwnerId(createTransferCommand.getAuctionOwnerId());
        transfer.setAmount(createTransferCommand.getAmount());
        transfer.setTitle(createTransferCommand.getTitle());
        transfer.setAuctionOwnerAccountId(createTransferCommand.getAuctionOwnerAccountId());
        transfer.setAuctionWinnerAccountNumber(createTransferCommand.getAuctionWinnerAccountNumber());
        return transfer;
    };
}
