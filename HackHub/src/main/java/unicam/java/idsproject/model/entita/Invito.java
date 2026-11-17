package unicam.java.idsproject.model.entita;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Invito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvito;

    @ManyToOne
    private UtenteRegistrato invitato;   // invitato

    @ManyToOne
    private Team team; //Team a cui è invitato

    public Invito(Team team, UtenteRegistrato invitato) {
        this.team = team;
        this.invitato = invitato;
    }

}
