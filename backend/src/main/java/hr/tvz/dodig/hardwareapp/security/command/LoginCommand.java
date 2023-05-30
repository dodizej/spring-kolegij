package hr.tvz.dodig.hardwareapp.security.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;


public class LoginCommand {

    public LoginCommand() {}

    public LoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @NotBlank(message = "Username must not be empty")
    private String username;

    @NotBlank(message = "Password must not be empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
