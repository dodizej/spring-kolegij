package hr.tvz.dodig.hardwareapp.security.repository;

import hr.tvz.dodig.hardwareapp.security.domain.Authority;
import hr.tvz.dodig.hardwareapp.security.domain.User;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Primary
@Repository
public class JdbcUserAuthorityRepository implements UserRepository {

    private static final String SELECT_ALL_USERS = "SELECT * FROM USER";

    private static final String SELECT_USER_AUTHORITY = "SELECT USER.ID, USER.USERNAME, USER.PASSWORD, AUTHORITY.AUTHORITY_NAME " +
            "FROM " +
            "USER " +
            "INNER JOIN USER_AUTHORITY ON USER.ID = USER_AUTHORITY.USER_ID " +
            "INNER JOIN AUTHORITY ON USER_AUTHORITY.AUTHORITY_ID = AUTHORITY.ID " +
            "WHERE USER.USERNAME = ? ";

    private final JdbcTemplate jdbc;

    public JdbcUserAuthorityRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("ID"),
                rs.getString("USERNAME"),
                rs.getString("PASSWORD"));
    }

    public Authority mapRowToAuthority(ResultSet rs, int rowNum) throws SQLException {
        return new Authority(rs.getString("AUTHORITY_NAME"));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = jdbc.queryForObject(SELECT_ALL_USERS + " WHERE USERNAME = ?", this::mapRowToUser, username);
        if (user != null) {
            List<Authority> authorities = jdbc.query(SELECT_USER_AUTHORITY, this::mapRowToAuthority, user.getUsername());
            user.setAuthorities(new HashSet<>(authorities));
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
