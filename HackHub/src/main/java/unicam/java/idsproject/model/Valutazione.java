package unicam.java.idsproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Valutazione {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idValutazione;

    private String giudizio;
    private int punteggio;

    // in teoria è stata rimossa, da vedere se va bene cosi, intanto la lascio
    @ManyToOne
    private Long idGiudice;


    public Valutazione(String giudizio, int punteggio, Long idGiudice) {
        this.giudizio = giudizio;
        this.punteggio = punteggio;
        this.idGiudice = idGiudice;
    }
}
