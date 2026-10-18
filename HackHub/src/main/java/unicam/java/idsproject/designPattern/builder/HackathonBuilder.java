package unicam.java.idsproject.designPattern.builder;

import unicam.java.idsproject.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HackathonBuilder {
    private String nome;
    private String regolamento;
    private LocalDate scadenzaIscrizioni;
    private LocalDate dataInizio;
    private LocalDate dataFine;
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
        if (mentori != null){
            for (UtenteRegistrato u : mentori) {
                hackathon.getMentori().add(new Mentore(u, hackathon));
            }
        }
        return hackathon;
    }

    public HackathonBuilder setNome(String nome) { this.nome = nome; return this; }
    public HackathonBuilder setRegolamento(String regolamento) { this.regolamento = regolamento; return this; }
    public HackathonBuilder setScadenzaIscrizioni(LocalDate scadenza) { this.scadenzaIscrizioni = scadenza; return this; }
    public HackathonBuilder setDataInizio(LocalDate inizio) { this.dataInizio = inizio; return this; }
    public HackathonBuilder setDataFine(LocalDate fine) { this.dataFine = fine; return this; }
    public HackathonBuilder setLuogo(String luogo) { this.luogo = luogo; return this; }
    public HackathonBuilder setPremio(float premio) { this.premio = premio; return this; }
    public HackathonBuilder setStatoHackathon(StatoHackathon stato) { this.statoHackathon = stato; return this; }
    public HackathonBuilder setDimensioneTeamMax(int membri) { this.dimensioneTeamMax = membri; return this; }
    public HackathonBuilder setGiudice(UtenteRegistrato giudice) { this.giudice = giudice; return this; }
    public HackathonBuilder setMentori(List<UtenteRegistrato> mentori) { this.mentori = mentori; return this; }
    public HackathonBuilder setOrganizzatore(UtenteRegistrato organizzatore) { this.organizzatore = organizzatore; return this; }
}
