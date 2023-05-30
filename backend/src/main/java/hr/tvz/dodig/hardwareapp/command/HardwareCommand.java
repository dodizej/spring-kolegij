package hr.tvz.dodig.hardwareapp.command;


import hr.tvz.dodig.hardwareapp.model.Hardware;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class HardwareCommand {

    @NotEmpty
    private String name;

    @NotEmpty
    private String code;

    @Positive
    private Double price;

    @NotNull
    private Hardware.Type type;

    @NotNull
    private Integer numberOfComponents;


}
