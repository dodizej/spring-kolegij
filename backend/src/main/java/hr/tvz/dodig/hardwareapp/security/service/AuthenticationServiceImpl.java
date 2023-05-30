package hr.tvz.dodig.hardwareapp.security.service;


import hr.tvz.dodig.hardwareapp.security.command.LoginCommand;
import hr.tvz.dodig.hardwareapp.security.domain.User;
import hr.tvz.dodig.hardwareapp.security.dto.LoginDTO;
import hr.tvz.dodig.hardwareapp.security.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(JwtService jwtService,
                                     UserRepository userRepository,
                                     PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }
}
