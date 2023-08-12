package forexbet.tradingforecasts.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangeAccountUsernameDTO {

    @NotBlank(message = "Username cannot be empty!")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    public ChangeAccountUsernameDTO() {
    }

    public String getUsername() {
        return username;
    }

    public ChangeAccountUsernameDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
