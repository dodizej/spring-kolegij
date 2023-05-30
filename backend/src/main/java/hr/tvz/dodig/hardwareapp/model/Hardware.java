package hr.tvz.dodig.hardwareapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="Hardware")
public class Hardware {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="naziv")
    private String name;

    @Column(name="code")
    private String code;

    @Column(name="cijena")
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name="tip")
    private Type type;

    @Column(name="kolicina")
    private Integer numberOfComponents;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="hardware")
    private List<Review> reviews;

    public Hardware() {

    }

    public Hardware(String name, String code, Double price, Type type, Integer numberOfComponents) {
        this.name  = name;
        this.code  = code;
        this.price = price;
        this.type  = type;
        this.numberOfComponents = numberOfComponents;
    }

    public static enum Type {
        CPU, GPU, MBO, RAM, STORAGE, OTHER
    }
}
