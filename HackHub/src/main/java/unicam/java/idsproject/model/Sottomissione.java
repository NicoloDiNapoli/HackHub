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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Valutazione valutazione;

    public Sottomissione( String file) {
        this.file = file;
    }

}
