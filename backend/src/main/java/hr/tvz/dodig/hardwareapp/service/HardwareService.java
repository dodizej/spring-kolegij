package hr.tvz.dodig.hardwareapp.service;

import hr.tvz.dodig.hardwareapp.command.HardwareCommand;
import hr.tvz.dodig.hardwareapp.dto.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO>     findAll();

    Optional<HardwareDTO> findByCode(String code);

    Optional<HardwareDTO> save(HardwareCommand command);

    Optional<HardwareDTO> put(String code, HardwareCommand command);

    List<HardwareDTO>     filterPoStanju(Integer min, Integer max);

    void     delete(String code);
}
