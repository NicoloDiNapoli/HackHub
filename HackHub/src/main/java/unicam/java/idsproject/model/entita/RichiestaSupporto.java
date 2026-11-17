package unicam.java.idsproject.model.entita;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicam.java.idsproject.model.enumerazione.StatoRichiesta;

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

    public RichiestaSupporto(String descrizione, LocalDateTime dataInvio, Registrazione registrazione, StatoRichiesta stato) {
        this.descrizione = descrizione;
        this.dataInvio = dataInvio;
        this.registrazione = registrazione;
        this.stato = stato;
    }

}
