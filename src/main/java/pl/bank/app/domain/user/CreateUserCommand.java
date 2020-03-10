package pl.bank.app.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Column;

@Builder
@Getter
public class CreateUserCommand {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String login;


}
