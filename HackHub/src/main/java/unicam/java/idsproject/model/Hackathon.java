package unicam.java.idsproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hackathon {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long idHackathon;

    private String nome;
    private String regolamento;
    private LocalDate scadenzaIscrizioni;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String luogo;
    private int dimensioneTeamMax;
    private float premio;

    @ManyToOne
    private Team vincitore;

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
