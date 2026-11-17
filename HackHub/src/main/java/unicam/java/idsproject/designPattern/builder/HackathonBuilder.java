package unicam.java.idsproject.designPattern.builder;

import unicam.java.idsproject.model.*;
import unicam.java.idsproject.model.entita.*;
import unicam.java.idsproject.model.enumerazione.StatoHackathon;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HackathonBuilder {
    private String nome;
    private String regolamento;
    private LocalDateTime scadenzaIscrizioni;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String luogo;
    private float premio;
    private StatoHackathon statoHackathon = StatoHackathon.IN_ISCRIZIONE;
    private int dimensioneTeamMax;
    private UtenteRegistrato giudice;
    private List<UtenteRegistrato> mentori = new ArrayList<>();
    private UtenteRegistrato organizzatore;

    public Hackathon build() {
        Hackathon hackathon = new Hackathon();
        hackathon.setNome(nome);
        hackathon.setRegolamento(regolamento);
        hackathon.setScadenzaIscrizioni(scadenzaIscrizioni);
        hackathon.setDataInizio(dataInizio);
        hackathon.setDataFine(dataFine);
        hackathon.setLuogo(luogo);
        hackathon.setPremio(premio);
        hackathon.setStato(statoHackathon);
        hackathon.setDimensioneTeamMax(dimensioneTeamMax);

        hackathon.setOrganizzatore(new Organizzatore(organizzatore, hackathon));
        hackathon.setGiudice(new Giudice(giudice, hackathon));
        if (mentori != null) {
            for (UtenteRegistrato utenteMentore : mentori) {
                hackathon.getMentori().add(new Mentore(utenteMentore, hackathon));
            }
        }
        return hackathon;
    }

    public HackathonBuilder setNome(String nome) { this.nome = nome; return this; }
    public HackathonBuilder setRegolamento(String regolamento) { this.regolamento = regolamento; return this; }
    public HackathonBuilder setScadenzaIscrizioni(LocalDateTime scadenza) { this.scadenzaIscrizioni = scadenza; return this; }
    public HackathonBuilder setDataInizio(LocalDateTime inizio) { this.dataInizio = inizio; return this; }
    public HackathonBuilder setDataFine(LocalDateTime fine) { this.dataFine = fine; return this; }
    public HackathonBuilder setLuogo(String luogo) { this.luogo = luogo; return this; }
    public HackathonBuilder setPremio(float premio) { this.premio = premio; return this; }
    public HackathonBuilder setStatoHackathon(StatoHackathon stato) { this.statoHackathon = stato; return this; }
    public HackathonBuilder setDimensioneTeamMax(int membri) { this.dimensioneTeamMax = membri; return this; }
    public HackathonBuilder setGiudice(UtenteRegistrato giudice) { this.giudice = giudice; return this; }
    public HackathonBuilder setMentori(List<UtenteRegistrato> mentori) { this.mentori = mentori; return this; }
    public HackathonBuilder setOrganizzatore(UtenteRegistrato organizzatore) { this.organizzatore = organizzatore; return this; }
}
