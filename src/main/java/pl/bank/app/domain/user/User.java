package pl.bank.app.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLock;
import pl.bank.app.domain.account.Account;
import pl.bank.app.domain.user.exceptions.LoginAlreadyExistsException;
import pl.bank.app.shared.Auditable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "USERS")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence")
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OptimisticLock(excluded = true)
    private Set<Account> accounts = new HashSet<>();

    @Column(unique = true)
    private String login;

    public static User generateUser(CreateUserCommand createUserCommand) {
        User user = new User();
        user.setFirstName(createUserCommand.getFirstName());
        user.setLastName(createUserCommand.getLastName());
        user.setLogin(createUserCommand.getLogin());
        return user;
        }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setUser(this);
    }

    public boolean isAccount(Account account) {
        return accounts.contains(account);
    }

    public UserResponse generateResponse() {
        return UserResponse.builder()
                .login(login)
                .id(id)
                .build();
    }
}
