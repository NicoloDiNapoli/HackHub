package unicam.java.idsproject.model.entita;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Giudice extends MembroStaff {
    @OneToOne
    private Hackathon hackathon;

    @OneToMany
    private List<Valutazione> valutazioniEffettuate;

    public Giudice(UtenteRegistrato utente, Hackathon hackathon) {
        setUtente(utente);
        setHackathon(hackathon);
    }
}
