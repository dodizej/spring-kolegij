package hr.tvz.dodig.hardwareapp.controller;

import hr.tvz.dodig.hardwareapp.dto.HardwareDTO;
import hr.tvz.dodig.hardwareapp.command.HardwareCommand;
import hr.tvz.dodig.hardwareapp.service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAllHardware() {
        return hardwareService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable final String code) {
        return hardwareService
                .findByCode(code)
                .map(hardwareDTO -> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO))
                .orElseGet(   () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("filterPoStanju/{min}/{max}")
    public List<HardwareDTO> getHardwareFromNtoM(@PathVariable final Integer min,
                                                           @PathVariable final Integer max) {
        return hardwareService.filterPoStanju(min, max);
    }

    @Secured({"ROLE_ADMIN", "ROLE_CREATOR"})
    @PostMapping
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command) {
        return hardwareService
                .save(command)
                .map(hardwareDTO -> ResponseEntity.status(HttpStatus.CREATED).body(hardwareDTO))
                .orElseGet(   () -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String code)
    {
        hardwareService.delete(code);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> put(@PathVariable final String code,
                                           @Valid @RequestBody final HardwareCommand command) {
        return hardwareService.put(code, command)
                .map(hardwareDTO -> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO))
                .orElseGet(   () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
