package unicam.java.idsproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTeam;
    @Column(unique = true)
    private String nome;

    @OneToMany
    @JoinColumn(name = "team")
    private List<UtenteRegistrato> membri = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invito> inviti = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Registrazione> registrazioni = new ArrayList<>();

    public Team(String nome) {
        this.nome = nome;
    }
}
