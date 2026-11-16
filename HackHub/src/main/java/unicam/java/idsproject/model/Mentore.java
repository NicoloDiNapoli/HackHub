package unicam.java.idsproject.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unicam.java.idsproject.model.Call;

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
