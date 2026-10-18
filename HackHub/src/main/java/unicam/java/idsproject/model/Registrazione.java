package unicam.java.idsproject.model;

import java.time.LocalDate;

public class Registrazione {
    private long id;
    private LocalDate dataRegistrazione;
    private Sottomissione sottomissione;
    private Team team;
    private Hackathon hackathon;

    public Registrazione(LocalDate dataRegistrazione, Sottomissione sottomissione, Team team, Hackathon hackathon) {
        this.dataRegistrazione = dataRegistrazione;
        this.sottomissione = sottomissione;
        this.team = team;
        this.hackathon = hackathon;
    }
}
