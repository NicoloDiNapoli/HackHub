package unicam.java.idsproject.logic.service;

import unicam.java.idsproject.designPattern.builder.HackathonBuilder;
import unicam.java.idsproject.logic.repository.HackathonRepository;
import unicam.java.idsproject.logic.repository.TeamRepository;
import unicam.java.idsproject.logic.repository.UtenteRegistratoRepository;
import unicam.java.idsproject.model.*;

import java.time.LocalDate;
import java.util.List;

public class GestoreHackathon {
    private UtenteRegistratoRepository utenteRegistratoRepository;
    private HackathonRepository hackathonRepository;
    private TeamRepository teamRepository;
    //private GestoreIscrizioni gestoreIscrizioni;

    public GestoreHackathon(UtenteRegistratoRepository utenteRegistratoRepository, HackathonRepository hackathonRepository, TeamRepository teamRepository) {
        this.utenteRegistratoRepository = utenteRegistratoRepository;
        this.hackathonRepository = hackathonRepository;
        this.teamRepository = teamRepository;
    }

    public Hackathon creaHackathon(UtenteRegistrato organizzatore, String nome, String regolamento,
                                   LocalDate scadenzaIscrizioni, LocalDate dataInizio, LocalDate dataFine,
                                   String luogo, float premioInDenaro, int dimensioneTeam,
                                   UtenteRegistrato giudice, List<UtenteRegistrato> mentori){
        if(organizzatore == null || giudice == null || mentori.isEmpty()) throw new IllegalArgumentException("Organizzatore, giudice e mentori obbligatori");
        if(scadenzaIscrizioni.isAfter(dataInizio) || dataFine.isBefore(dataInizio)) throw new IllegalArgumentException("Data non valida");


        //if(utenteRegistratoRepository.cercaUtenteId(organizzatore.getIdUtente()) == null)
        // throw new IllegalArgumentException("Organizzatore non registrato");

        if(utenteRegistratoRepository.cercaUtenteId(giudice.getIdUtente()) == null)
            throw new IllegalArgumentException("Giudice non presente");

        mentori.forEach(mentore -> {
            if(utenteRegistratoRepository.cercaUtenteId(mentore.getIdUtente()) == null)
                throw new IllegalArgumentException("Mentore non presente");
        });

        HackathonBuilder builder = new HackathonBuilder();
        builder.setNome(nome);
        builder.setRegolamento(regolamento);
        builder.setScadenzaIscrizioni(scadenzaIscrizioni);
        builder.setDataInizio(dataInizio);
        builder.setDataFine(dataFine);
        builder.setLuogo(luogo);
        builder.setPremio(premioInDenaro);
        builder.setDimensioneTeamMax(dimensioneTeam);
        builder.setOrganizzatore(organizzatore);
        builder.setGiudice(giudice);
        builder.setMentori(mentori);
        builder.setStatoHackathon(StatoHackathon.IN_ISCRIZIONE);
        return hackathonRepository.save(builder.build());
    }

    // return true se esiste, false altrimenti
    public boolean controllaHackathon(long idHackathon) {
        return hackathonRepository.existsById(idHackathon);
    }

    public boolean teamIscritto(long idTeam /*, long idHackathon */){
        //TODO
        // creare RegistrazioniRepository e cercare se esiste la registrazione del team con l'idHackathon
        return false;
    }

    public boolean iscriviTeam(Team team, long idHackathon){
        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);
        if(team == null || idHackathon < 0 || !controllaHackathon(idHackathon)) throw new IllegalArgumentException("Team o Hackathon non validi");

        //TODO if(registrazioneRepository.teamIscritto(team.getIdTeam(), idHackathon) != null) throw new IllegalArgumentException("Team gia iscritto");

        Registrazione registrazione = new Registrazione(LocalDate.now(), team, hackathon);
        //return registrazioneRepository.save(registrazione)!=null;
        //TODO rimuovere return sotto
        return true;
    }

    public boolean proclamaVincitore(long idHackathon, long idTeam){
        if(! teamIscritto(idTeam) || !controllaHackathon(idHackathon)) throw new IllegalArgumentException("Hackathon o Team non validi");

        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);
        if(verificaFaseHackathon(idHackathon) != StatoHackathon.IN_VALUTAZIONE) throw new IllegalStateException("Hackathon non in valutazione");

        hackathon.setVincitore(teamRepository.findById(idTeam));
        hackathon.setStato(StatoHackathon.CONCLUSO);
        hackathonRepository.save(hackathon);
        //TODO aggiungere emettiPremio?
        return true;
    }

    public Sottomissione sottomissione(long idHackathon, long idTeam){
        //TODO chiamare gestoreIscrizioni e fare
        //return gestoreIscrizioni.cercaSottomissione(idHackathon, idTeam);
        return null;
    }

    public StatoHackathon verificaFaseHackathon(long idHackathon){
        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);
        if (hackathon == null) throw new IllegalArgumentException("Hackathon non trovato");
        if (hackathon.getStato() == StatoHackathon.CONCLUSO) return StatoHackathon.CONCLUSO;
        if (LocalDate.now().isBefore(hackathon.getDataInizio())) return StatoHackathon.IN_ISCRIZIONE;
        if (!LocalDate.now().isAfter(hackathon.getDataFine())) return StatoHackathon.IN_CORSO;
        return StatoHackathon.IN_VALUTAZIONE;
    }



}
