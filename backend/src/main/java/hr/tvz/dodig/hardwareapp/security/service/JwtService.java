package hr.tvz.dodig.hardwareapp.security.service;


import hr.tvz.dodig.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
