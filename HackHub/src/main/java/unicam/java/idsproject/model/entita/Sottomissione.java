package unicam.java.idsproject.model.entita;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Sottomissione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String file;
    private String titolo;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Valutazione valutazione;

    public Sottomissione( String file, String titolo) {
        this.file = file;
        this.titolo = titolo;
    }

}
