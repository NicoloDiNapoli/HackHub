package unicam.java.idsproject.model.entita;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Call {
    @Id
    @GeneratedValue
    private Long idCall;

    private LocalDateTime data;
    private String link;

    @OneToOne
    private RichiestaSupporto richiestaSupporto;

    @ManyToOne
    private Mentore mentore;

    public Call(LocalDateTime data, String link, RichiestaSupporto richiestaSupporto, Mentore mentore) {
        this.data = data;
        this.link = link;
        this.richiestaSupporto = richiestaSupporto;
        this.mentore = mentore;
    }

}
