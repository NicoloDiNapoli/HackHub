package unicam.java.idsproject.model.entita;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data
@NoArgsConstructor
public abstract class MembroStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMembroStaff;

    @ManyToOne
    private UtenteRegistrato utente;

}



