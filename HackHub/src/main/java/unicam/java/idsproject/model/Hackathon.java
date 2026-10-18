package unicam.java.idsproject.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hackathon {
    private long idHackathon;
    private final String nome;
    private final String regolamento;
    private final LocalDate scadenzaIscrizioni;
    private final LocalDate dataInizio;
    private final LocalDate dataFine;
    private final String luogo;
    private final float premio;
    private StatoHackathon stato;
    private final Organizzatore organizzatore;
    private final Giudice giudice;
    private final List<Mentore> mentori;

    public Hackathon(String nome, String regolamento, LocalDate scadenzaIscrizioni,
                     LocalDate dataInizio, LocalDate dataFine, String luogo, float premio,
                     UtenteRegistrato creatore, UtenteRegistrato giudice,
                     List<UtenteRegistrato> mentori) {
        if (giudice == null)
            throw new IllegalArgumentException("Il giudice è obbligatorio");
        if (mentori == null || mentori.isEmpty())
            throw new IllegalArgumentException("Un hackathon richiede almeno un mentore");
        if (scadenzaIscrizioni.isAfter(dataInizio) || dataInizio.isAfter(dataFine))
            throw new IllegalArgumentException("Date non coerenti");

        this.nome = nome;
        this.regolamento = regolamento;
        this.scadenzaIscrizioni = scadenzaIscrizioni;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.luogo = luogo;
        this.premio = premio;
        this.stato = StatoHackathon.IN_ISCRIZIONE;
        this.organizzatore = new Organizzatore(creatore, this);
        this.giudice = new Giudice(giudice, this);
        this.mentori = new ArrayList<>();
        for (UtenteRegistrato u : mentori)
            this.mentori.add(new Mentore(u, this));
    }

    public Organizzatore getOrganizzatore() { return organizzatore; }
    public Giudice getGiudice() { return giudice; }
    public List<Mentore> getMentori() { return Collections.unmodifiableList(mentori); }
    public StatoHackathon getStato() { return stato; }
    // altri getter...
}
