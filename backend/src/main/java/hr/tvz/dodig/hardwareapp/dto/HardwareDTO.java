package hr.tvz.dodig.hardwareapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class HardwareDTO {

    private final String code;
    private final String name;
    private final Double price;

    public HardwareDTO(String code, String name, Double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return this.name + " " + this.price;
    }

}
