package hr.tvz.dodig.hardwareapp.dto;


import lombok.Data;

@Data
public class ReviewDTO {

    private final String naslov;

    private final String tekst;

    private final Integer ocjena;

    public ReviewDTO(String naslov, String tekst, Integer ocjena) {
        this.naslov = naslov;
        this.tekst = tekst;
        this.ocjena = ocjena;
    }


}
