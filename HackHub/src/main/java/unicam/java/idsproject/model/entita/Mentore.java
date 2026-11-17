package unicam.java.idsproject.model.entita;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Mentore extends MembroStaff {
    @ManyToOne
    private Hackathon hackathon;

    @OneToMany(mappedBy = "mentore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Call> callProposte;

    public Mentore(UtenteRegistrato utente, Hackathon hackathon) {
        setUtente(utente);
        setHackathon(hackathon);
    }
}
