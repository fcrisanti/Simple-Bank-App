package pl.bank.app.api.user;

import lombok.Value;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
class UserRequest {
    @NotNull
    @NotEmpty
    private final String login;

    @NotNull
    @NotEmpty
    private final String firstName;

    @NotNull
    @NotEmpty
    private final String lastName;
}
