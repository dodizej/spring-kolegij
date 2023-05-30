package hr.tvz.dodig.hardwareapp.security.service;

import hr.tvz.dodig.hardwareapp.security.domain.Authority;
import hr.tvz.dodig.hardwareapp.security.domain.User;
import hr.tvz.dodig.hardwareapp.security.user.ApplicationUser;
import hr.tvz.dodig.hardwareapp.security.user.UserAuthentication;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {

    private static final Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);
    private static final String AUTHORITIES_KEY = "authorities";

    @Value("${security.authentication.jwt.access-token-validity-seconds}")
    private Long accessTokenValiditySeconds;

    @Value("${security.authentication.jwt.base64-secret}")
    private String secretKey;

    @Override
    public boolean authenticate(String token) {
        if (isJwtInvalid(token)) {
            return false;
        }

        ApplicationUser applicationUser = getUserDataFromJwt(token);
        saveAuthentication(applicationUser);

        return true;
    }

    @Override
    public String createJwt(User user) {
        Instant expiration = Instant.now().plusSeconds(accessTokenValiditySeconds);
        String authorities = user.getAuthorities()
                .stream()
                .map(Authority::getName)
                .collect(Collectors.joining(","));

        return Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setSubject(user.getUsername())
                .setExpiration(new Date(expiration.toEpochMilli()))
                .setIssuedAt(new Date())
                .claim(AUTHORITIES_KEY, authorities)
                .compact();
    }

    private boolean isJwtInvalid(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return false;
        } catch (SignatureException e) {
            log.debug("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.debug("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.debug("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.debug("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e.getMessage());
        }
        return true;
    }

    private ApplicationUser getUserDataFromJwt(String jwtToken) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken)
                .getBody();

        List<SimpleGrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(claims.getSubject());
        applicationUser.setAuthorities(authorities);

        return applicationUser;
    }

    private void saveAuthentication(ApplicationUser applicationUser) {
        Authentication authentication = new UserAuthentication(applicationUser);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
