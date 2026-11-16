package unicam.java.idsproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class RichiestaSupporto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRichiesta;
    private String descrizione;
    private LocalDateTime dataInvio;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    private StatoRichiesta stato;

    @ManyToOne
    private Registrazione registrazione;

    @ManyToOne
    private Call call;
}
