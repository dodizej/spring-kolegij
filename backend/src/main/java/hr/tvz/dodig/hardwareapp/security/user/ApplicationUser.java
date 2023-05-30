package hr.tvz.dodig.hardwareapp.security.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ApplicationUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 8533039291044343363L;

    private String username;
    private String password;
    private List<SimpleGrantedAuthority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
