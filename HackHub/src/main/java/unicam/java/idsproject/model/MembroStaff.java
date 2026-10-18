package unicam.java.idsproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class MembroStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMembroStaff;
    @ManyToOne
    private UtenteRegistrato utente;

}



