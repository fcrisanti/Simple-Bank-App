package pl.bank.app.infrastructure.account;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bank.app.domain.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
