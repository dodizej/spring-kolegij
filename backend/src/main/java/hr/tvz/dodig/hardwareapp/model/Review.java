package hr.tvz.dodig.hardwareapp.model;


import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name="Review")
public class Review {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer    id;

    @Column(name="naslov")
    private String naslov;

    @Column(name="tekst")
    private String tekst;

    @Column(name="ocjena")
    private Integer ocjena;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="hardware_id", nullable = false)
    private Hardware hardware;

    public Review() {

    }

    public Review(Integer id, String naslov, String tekst, Integer ocjena) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.ocjena = ocjena;
    }



}
