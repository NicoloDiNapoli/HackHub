package unicam.java.idsproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sottomissione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String file;
    private String titolo;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Valutazione valutazione;

    public Sottomissione( String file, String titolo) {
        this.file = file;
        this.titolo = titolo;
    }

}
