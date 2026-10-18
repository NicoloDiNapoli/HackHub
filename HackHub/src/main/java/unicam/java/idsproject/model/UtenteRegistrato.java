package unicam.java.idsproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UtenteRegistrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUtente;
    private String nome;
    private String cognome;
    @Column(unique = true)
    private String email;
    @ManyToOne
    private Team team;

    public UtenteRegistrato(String nome, String cognome, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }
}
