package hr.tvz.dodig.hardwareapp.repository;

import hr.tvz.dodig.hardwareapp.model.Hardware;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public class MockHardwareRepository //implements HardwareRepository {
{
    /*
    @Override
    public List<Hardware> findAll() {
        return null;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return Optional.empty();
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        return Optional.empty();
    }

    @Override
    public void delete(String code) {

    }

    @Override
    public Optional<Hardware> put(String code, Hardware hardware) {
        return Optional.empty();
    }

    @Override
    public List<Hardware> filterPoStanju(Integer min, Integer max) {
        return null;
    }
*/

    /*
     * private String name;
     *     private String code;
     *     private Double price;
     *     private Type type;
     */
/*
    private List<Hardware> hardware = new ArrayList<>(Arrays.asList(
            new Hardware("INTEL i7-9700",
                    "XYZ", 300.0,
                    Hardware.Type.CPU,
                    2),

            new Hardware("NVIDIA GTX 980 Ti",
                    "ASD", 200.0, Hardware.Type.GPU,
                    4),

            new Hardware("SAMSUNG 2667MHz 4GB",
                    "QWE",
                    75.0, Hardware.Type.RAM,
                    5
                    ),

            new Hardware("KINGSTON SSD 250GB",
                    "RTZ",
                    55.0, Hardware.Type.STORAGE,
                    6
                    )

    ));

    @Override
    public List<Hardware> findAll() {
        return hardware;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return hardware.stream().filter(h -> h.getCode().equals(code)).findAny();
    }

    @Override
    public Optional<Hardware> save(HardwareCommand command) {

        if (hardware.stream().anyMatch(h -> h.getCode().equals(command.getCode()))) {
            return Optional.empty();
        } else {
            Hardware newHardware = new Hardware(command.getName(), command.getCode(), command.getPrice(), command.getType(),
                    command.getNumberOfComponents());
            hardware.add(newHardware);
            return Optional.of(newHardware);
        }
    }

    @Override
    public Optional<Boolean> delete(String code) {
        if (hardware.removeIf(h -> h.getCode().equals(code))) {
            return Optional.of(Boolean.TRUE);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Hardware> put(String code, HardwareCommand command) {
        if (hardware.stream().anyMatch(h -> h.getCode().equals(command.getCode()))) {
            hardware.stream().filter(h -> h.getCode().equals(command.getCode())).forEach(h -> {
                h.setPrice(command.getPrice());
                h.setName(command.getName());
                h.setType(command.getType());
            });
            return hardware.stream().filter(h -> h.getCode().equals(code)).findFirst();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Hardware> patch(String code, HardwarePatchCommand command) {
        if (hardware.stream().anyMatch(h -> h.getCode().equals(code))) {
            hardware.stream()
                    .filter(h -> h.getCode().equals(code))
                    .forEach(h -> {
                        h.setName(command.getName());
                    });
            return hardware.stream().filter(h -> h.getCode().equals(code)).findFirst();
        }
        return Optional.empty();
    }
*/
}
