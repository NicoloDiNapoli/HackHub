package unicam.java.idsproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtenteRegistrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUtente;
    private String nome;
    private String cognome;
    @Column(unique = true)
    private String email;

}
