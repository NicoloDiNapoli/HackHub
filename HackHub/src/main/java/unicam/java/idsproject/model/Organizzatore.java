package unicam.java.idsproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Organizzatore extends MembroStaff {
    @OneToOne
    private Hackathon hackathon;
    public Organizzatore(UtenteRegistrato utente, Hackathon hackathon) {
        setUtente(utente);
        setHackathon(hackathon);
    }
}
