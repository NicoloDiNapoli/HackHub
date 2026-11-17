package unicam.java.idsproject.model.entita;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class UtenteRegistrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtente;
    @Column(unique = true)
    private String nomeUtente;
    private String nome;
    private String cognome;
    @Column(unique = true)
    private String email;
    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "invitato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invito> inviti;
    private List<MembroStaff> ruoliStaff;

    public UtenteRegistrato(String nomeUtente, String nome, String cognome, String email) {
        this.nomeUtente = nomeUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }
}
