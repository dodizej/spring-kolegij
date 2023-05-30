package hr.tvz.dodig.hardwareapp.repository;

import hr.tvz.dodig.hardwareapp.model.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository  {

    List<Hardware>        findAll();
    Optional<Hardware>    findByCode(String code);
    Optional<Hardware>    save(Hardware hardware);
    Optional<Hardware>    put(String code, Hardware hardware);
    List<Hardware>        filterPoStanju(Integer min, Integer max);
    void delete(String code);

}

