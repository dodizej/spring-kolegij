package hr.tvz.dodig.hardwareapp.security.repository;

import hr.tvz.dodig.hardwareapp.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  {

    Optional<User> findByUsername(String username);

}
