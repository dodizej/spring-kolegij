package hr.tvz.dodig.hardwareapp.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class HardwarePatchCommand {

    @NotEmpty
    private String name;

}
