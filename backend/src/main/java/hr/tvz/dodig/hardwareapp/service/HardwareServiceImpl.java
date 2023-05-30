package hr.tvz.dodig.hardwareapp.service;

import hr.tvz.dodig.hardwareapp.model.Hardware;
import hr.tvz.dodig.hardwareapp.command.HardwareCommand;
import hr.tvz.dodig.hardwareapp.dto.HardwareDTO;
import hr.tvz.dodig.hardwareapp.repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    public HardwareDTO mapHardwareToDTO(Hardware hardware)
    {
        return new HardwareDTO(hardware.getCode(), hardware.getName(), hardware.getPrice());
    }

    public Hardware mapCommandToHardware(HardwareCommand hardwareCommand)
    {
        return new Hardware(hardwareCommand.getName(),
                hardwareCommand.getCode(),
                hardwareCommand.getPrice(),
                hardwareCommand.getType(),
                hardwareCommand.getNumberOfComponents());
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(String code) {
        return hardwareRepository.findByCode(code).map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand command) {
        return hardwareRepository
                .save(mapCommandToHardware(command))
                .map(this::mapHardwareToDTO);
    }

    @Override
    public void delete(String code) {
        hardwareRepository.delete(code);
    }

    @Override
    public Optional<HardwareDTO> put(String code, HardwareCommand command) {
        return hardwareRepository
                .put(code, mapCommandToHardware(command))
                .map(this::mapHardwareToDTO);
    }

    @Override
    public List<HardwareDTO> filterPoStanju(Integer min, Integer max) {
        return hardwareRepository
                .filterPoStanju(min, max)
                .stream()
                .map(this::mapHardwareToDTO)
                .toList();
    }


}
