package hr.tvz.dodig.hardwareapp.repository;

import hr.tvz.dodig.hardwareapp.model.Hardware;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository {

    private static final String SELECT_ALL = "SELECT * FROM Hardware";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcHardwareRepository(JdbcTemplate jdbc) {

        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Hardware")
                .usingGeneratedKeyColumns("id");
    }

    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(
                rs.getString("naziv"),
                rs.getString("code"),
                rs.getDouble("cijena"),
                Hardware.Type.valueOf(rs.getString("tip")),
                rs.getInt("kolicina")
        );
    }

    @Override
    public List<Hardware> findAll() {
        return jdbc.query(SELECT_ALL, this::mapRowToHardware);
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return Optional.ofNullable(
              jdbc.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code)
            );

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try {
            saveHardwareDetail(hardware);
            return Optional.of(hardware);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    private void saveHardwareDetail(Hardware hardware) throws DuplicateKeyException {
        Map<String, Object> values = new HashMap<>();

        values.put("naziv", hardware.getName());
        values.put("code", hardware.getCode());
        values.put("cijena", hardware.getPrice());
        values.put("tip", hardware.getType());
        values.put("kolicina", hardware.getNumberOfComponents());

        inserter.execute(values);
    }

    @Override
    public void delete(String code) {
        jdbc.update("DELETE FROM Hardware WHERE code = ?", code);
    }

    @Override
    public Optional<Hardware> put(String code, Hardware hardware) {

        int executed = jdbc.update("UPDATE Hardware SET " +
                "naziv = ?, " +
                "cijena = ?, " +
                "tip = ?, " +
                "kolicina = ? " +
                "WHERE code = ?",
                hardware.getName(),
                hardware.getPrice(),
                hardware.getType().toString(),
                hardware.getNumberOfComponents(),
                hardware.getCode()
        );

        if (executed > 0) {
            return Optional.of(hardware);
        } else {
            return Optional.empty();
        }

    }

    @Override
    public List<Hardware> filterPoStanju(Integer min, Integer max) {
        try {
            return jdbc.query(
                            SELECT_ALL + " WHERE kolicina > ? AND kolicina < ? ",
                            this::mapRowToHardware,
                            min,
                            max);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }


}
