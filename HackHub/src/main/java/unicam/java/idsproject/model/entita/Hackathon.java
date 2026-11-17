package unicam.java.idsproject.model.entita;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicam.java.idsproject.model.enumerazione.StatoHackathon;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class Hackathon {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idHackathon;

    private String nome;
    private String regolamento;
    private LocalDateTime scadenzaIscrizioni;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String luogo;
    private double premio;
    private int dimensioneTeamMax;

//    @ManyToOne
//    private Team vincitore;
    @OneToOne(cascade = CascadeType.ALL)
    private Registrazione vincitore;

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registrazione> registrazioni = new ArrayList<>();

    @Enumerated(jakarta.persistence.EnumType.STRING)
    private StatoHackathon stato;

    @OneToOne(mappedBy = "hackathon", cascade = CascadeType.ALL , orphanRemoval = true)
    private Organizzatore organizzatore;

    @OneToOne(mappedBy = "hackathon", cascade = CascadeType.ALL , orphanRemoval = true)
    private Giudice giudice;

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Mentore> mentori = new ArrayList<>();

}
