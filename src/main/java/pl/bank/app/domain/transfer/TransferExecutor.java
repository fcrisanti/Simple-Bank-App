package pl.bank.app.domain.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bank.app.domain.account.Account;
import pl.bank.app.domain.account.exceptions.AccountNotFound;
import pl.bank.app.domain.transfer.exceptions.UserNotFound;
import pl.bank.app.domain.account.AccountRetrievalClient;
import pl.bank.app.domain.user.User;
import pl.bank.app.domain.user.UserRetrievalClient;

import java.util.List;

@Service
@RequiredArgsConstructor

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
      User user = userRetrievalClient.findById(transfer.getAuctionOwnerId());
      Account account = accountRetrievalClient.findById(transfer.getAuctionOwnerAccountId())
              .orElseThrow(() -> new AccountNotFound(String.format("Account %d doesn't exist", transfer.getAuctionOwnerAccountId())));
      userHasAccountOrThrow(user, account);
      account.subtractAmount(transfer.getAmount());
      createTransferClient.create(transfer);
   }

   public void userHasAccountOrThrow(User user, Account account) throws UserNotFound {
      if (!user.isAccount(account))
         throw new UserNotFound(String.format("User %d does not have account %d", user.getId(), account.getId()));
   }

   void executePendingTransfers() {
      List<CreateTransferCommand> pendingTransferList = transferRetrievalClient.getTransferRequests();
      for (CreateTransferCommand transferRequest : pendingTransferList) {
//         create(transferRequest);
         executeAndSaveOrThrow(create(transferRequest));
      }
   }
}

