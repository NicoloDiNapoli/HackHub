package unicam.java.idsproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mentore extends MembroStaff {
    @ManyToOne
    private Hackathon hackathon;
    public Mentore(UtenteRegistrato utente, Hackathon hackathon) {
        setUtente(utente);
        setHackathon(hackathon);
    }
}
