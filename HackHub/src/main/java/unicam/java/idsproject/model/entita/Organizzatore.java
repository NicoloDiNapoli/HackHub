package unicam.java.idsproject.model.entita;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Organizzatore extends MembroStaff {
    @OneToOne
    private Hackathon hackathon;

    public Organizzatore(UtenteRegistrato utente, Hackathon hackathon) {
        setUtente(utente);
        setHackathon(hackathon);
    }
}
