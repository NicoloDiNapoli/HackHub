package unicam.java.idsproject.model.entita;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Registrazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistrazione;

    private LocalDateTime dataRegistrazione;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Sottomissione sottomissione;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Hackathon hackathon;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RichiestaSupporto> richiesteSupporto;

    public Registrazione(LocalDateTime dataRegistrazione, Team team, Hackathon hackathon) {
        this.dataRegistrazione = dataRegistrazione;
        this.team = team;
        this.hackathon = hackathon;
    }


}
