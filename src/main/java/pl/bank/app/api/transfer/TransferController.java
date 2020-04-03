package pl.bank.app.api.transfer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.bank.app.domain.transfer.TransferFacade;
import pl.bank.app.domain.transfer.CreateTransferCommand;
import pl.bank.app.domain.transfer.CreateTransferCommandMapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
@Slf4j
    class TransferController {

    private final TransferFacade transferFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody TransferRequest transferRequest) {
        CreateTransferCommand createTransferCommand = CreateTransferCommandMapper.map(transferRequest);
        transferFacade.executeTransfer(createTransferCommand);
        log.info("Transfer created {}", transferRequest.toString());
    }

    @PostMapping(path = "/getpaid")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<CreateTransferCommand>> sendBackExecutedTransfers(@Valid @RequestBody List<TransferRequest> transferRequests) {
        log.info("Sending executed transfers to the auction app");
        return ResponseEntity.ok(transferFacade.sendBackExecutedTransfersList(transferRequests));
    }

    @PostMapping(path = "/pending")
    @ResponseStatus(HttpStatus.CREATED)
    public void pullPendingTransfers() {
        transferFacade.executePendingTransfers();
        log.info("Pulling pending transfers from the auction app");
    }
}


