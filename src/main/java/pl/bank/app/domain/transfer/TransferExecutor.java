package pl.bank.app.domain.transfer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bank.app.api.transfer.TransferRequest;
import pl.bank.app.domain.account.Account;
import pl.bank.app.domain.account.exceptions.AccountNotFound;
import pl.bank.app.domain.transfer.exceptions.UserNotFound;
import pl.bank.app.domain.account.AccountRetrievalClient;
import pl.bank.app.domain.user.User;
import pl.bank.app.domain.user.UserRetrievalClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

class TransferExecutor {

   public final CreateTransferClient createTransferClient;
   public final UserRetrievalClient userRetrievalClient;
   public final AccountRetrievalClient accountRetrievalClient;
   public final TransferRetrievalClient transferRetrievalClient;


   public Transfer create(CreateTransferCommand createTransferCommand) {
      return Transfer.generate(createTransferCommand);
   }

   @Transactional
   public void executeAndSaveOrThrow(Transfer transfer) {
      User seller = userRetrievalClient.findById(transfer.getAuctionOwnerId());
      Account sellerAccount = accountRetrievalClient.findById(transfer.getAuctionOwnerAccountId())
              .orElseThrow(() -> new AccountNotFound(String.format("Owner account %d doesn't exist", transfer.getAuctionOwnerAccountId())));
      userHasAccountOrThrow(seller, sellerAccount);
      sellerAccount.addAmount(transfer.getAmount());

      try {
         Account winnerAccount = accountRetrievalClient.findByAccountNumber(transfer.getAuctionWinnerAccountNumber())
                 .orElseThrow(() -> new AccountNotFound(String.format("Winner account %s doesn't exist", transfer.getAuctionWinnerAccountNumber())));
         winnerAccount.subtractAmount(transfer.getAmount());
      } catch (AccountNotFound ex) {
         log.info(String.format("Winner account %d doesn't exist", transfer.getAuctionOwnerAccountId()));
      }

      createTransferClient.create(transfer);
   }

   public void userHasAccountOrThrow(User user, Account account) throws UserNotFound {
      if (!user.isAccount(account))
         throw new UserNotFound(String.format("User %d does not have account %d", user.getId(), account.getId()));
   }

   void executePendingTransfers() {
      List<CreateTransferCommand> pendingTransferList = transferRetrievalClient.getTransferRequests();
      for (CreateTransferCommand transferRequest : pendingTransferList) {
         executeAndSaveOrThrow(create(transferRequest));
      }
   }

   List<CreateTransferCommand> checkTransfersList(List<TransferRequest> transferRequests) {
      List<CreateTransferCommand> executedTransfersFromProvidedList = new ArrayList<>();
      List<CreateTransferCommand> allExecutedTransfers = transferRetrievalClient.getTransferRequests();
      for (TransferRequest transferRequest : transferRequests) {
           CreateTransferCommand command = CreateTransferCommandMapper.map(transferRequest);
         if(allExecutedTransfers.contains(command)) {
            executedTransfersFromProvidedList.add(command);
         }
      }
      return executedTransfersFromProvidedList;
   }
}

