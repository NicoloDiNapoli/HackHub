package unicam.java.idsproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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



