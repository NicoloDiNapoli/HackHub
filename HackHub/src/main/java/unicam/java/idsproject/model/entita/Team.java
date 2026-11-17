package unicam.java.idsproject.model.entita;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTeam;

    @Column(unique = true)
    private String nome;

    @OneToMany(mappedBy = "team")
    private List<UtenteRegistrato> membri = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invito> inviti = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Registrazione> registrazioni = new ArrayList<>();

    public Team(String nome) {
        this.nome = nome;
    }

}
