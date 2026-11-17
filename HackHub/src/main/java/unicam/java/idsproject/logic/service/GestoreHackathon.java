package unicam.java.idsproject.logic.service;

import unicam.java.idsproject.designPattern.builder.HackathonBuilder;
import unicam.java.idsproject.logic.repository.HackathonRepository;
import unicam.java.idsproject.logic.repository.RegistrazioneRepository;
import unicam.java.idsproject.logic.repository.TeamRepository;
import unicam.java.idsproject.logic.repository.UtenteRegistratoRepository;
import unicam.java.idsproject.model.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class GestoreHackathon {
    private UtenteRegistratoRepository utenteRegistratoRepository;
    private HackathonRepository hackathonRepository;
    private TeamRepository teamRepository;
    private RegistrazioneRepository registrazioneRepository;
    //private GestoreIscrizioni gestoreIscrizioni;

    public GestoreHackathon(UtenteRegistratoRepository utenteRegistratoRepository, HackathonRepository hackathonRepository, TeamRepository teamRepository) {
        this.utenteRegistratoRepository = utenteRegistratoRepository;
        this.hackathonRepository = hackathonRepository;
        this.teamRepository = teamRepository;
    }

    public String creaHackathon(UtenteRegistrato organizzatore, String regolamento,
                                LocalDateTime scadenzaIscrizioni, LocalDateTime dataInizio,
                                LocalDateTime dataFine, String luogo, float premioInDenaro,
                                int dimensioneTeam, UtenteRegistrato giudice,
                                List<UtenteRegistrato> mentori, String nome) {
        if (organizzatore == null)
            throw new IllegalArgumentException("Null organizzatore");
        if (regolamento == null || regolamento.isBlank())
            throw new IllegalArgumentException("Null o vuoto regolamento");
        if (scadenzaIscrizioni == null)
            throw new IllegalArgumentException("Null scadenzaIscrizioni");
        if (dataInizio == null)
            throw new IllegalArgumentException("Null dataInizio");
        if (dataFine == null)
            throw new IllegalArgumentException("Null dataFine");
        if (luogo == null || luogo.isBlank())
            throw new IllegalArgumentException("Null o vuoto luogo");
        if (giudice == null)
            throw new IllegalArgumentException("Null giudice");
        if (mentori == null || mentori.isEmpty())
            throw new IllegalArgumentException("Null o vuota lista mentori");
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Null o vuoto nome");

        utenteRegistratoRepository.cercaUtenteId(organizzatore.getIdUtente());

        return "Hackathon creato con successo";
    }

    // +proclamaVincitore(idOrganizzatore : Long, idHackathon : Long, idTeam : Long) : String
    public String proclamaVincitore(Long idOrganizzatore, Long idHackathon, Long idTeam){
        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);
        if(hackathon == null)
            throw new IllegalArgumentException("Errore, hackathon non trovato");

        if(!Objects.equals(idOrganizzatore, hackathon.getOrganizzatore().getIdMembroStaff()))
            throw new IllegalArgumentException("Errore, l'utente non è l'organizzatore dell'hackathon");

        Registrazione registrazione = registrazioneRepository.cercaTeamIscritto(idTeam, idHackathon);
        if(registrazione == null)
            throw new IllegalArgumentException("Errore, team non iscritto");
        if(hackathon.getStato() != StatoHackathon.IN_VALUTAZIONE)
            throw new IllegalArgumentException("Errore, hackathon non in valutazione");

        hackathon.setVincitore(registrazione);
        hackathon.setStato(StatoHackathon.CONCLUSO);

        hackathonRepository.save(hackathon);

        return "Vincitore proclamato con successo";
    }

}
