package hr.tvz.dodig.hardwareapp.security.service;

import hr.tvz.dodig.hardwareapp.security.command.LoginCommand;
import hr.tvz.dodig.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
