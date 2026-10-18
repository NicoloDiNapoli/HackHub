package unicam.java.idsproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Registrazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRegistrazione;

    private LocalDate dataRegistrazione;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Sottomissione sottomissione;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Hackathon hackathon;

    public Registrazione(LocalDate dataRegistrazione, Team team, Hackathon hackathon) {
        this.dataRegistrazione = dataRegistrazione;
        this.team = team;
        this.hackathon = hackathon;
    }


}
