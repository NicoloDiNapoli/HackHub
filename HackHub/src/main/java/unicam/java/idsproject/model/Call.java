package unicam.java.idsproject.model;

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

}
