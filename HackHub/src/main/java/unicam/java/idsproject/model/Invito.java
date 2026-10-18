package unicam.java.idsproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Invito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idInvito;

    @ManyToOne
    private UtenteRegistrato invitato;   // invitato

    @ManyToOne
    private Team team; //Team a cui è invitato

    public Invito(Team team, UtenteRegistrato invitato) {
        this.team = team;
        this.invitato = invitato;
    }
}
