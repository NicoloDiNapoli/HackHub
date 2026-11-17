package unicam.java.idsproject.logic.service;

import lombok.Data;
import unicam.java.idsproject.model.entita.*;
import unicam.java.idsproject.model.enumerazione.StatoHackathon;
import unicam.java.idsproject.model.enumerazione.StatoRichiesta;
import unicam.java.idsproject.model.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GestoreCall {
    private CallRepository callRepository;
    private RichiestaSupportoRepository richiestaSupportoRepository;
    private HackathonRepository hackathonRepository;
    private UtenteRegistratoRepository utenteRegistratoRepository;
    private RegistrazioneRepository registrazioneRepository;

    public String gestisciCall(Long idCall, boolean scelta){
        Call call = callRepository.cercaCallId(idCall);
        RichiestaSupporto richiesta = call.getRichiestaSupporto();

        if(scelta){
            richiesta.setStato(StatoRichiesta.CALL_ACCETTATA);
            richiestaSupportoRepository.save(richiesta);
            return "Call accettata";
        }else{
            //TODO capire COME GESTIRE CALENDAR
            callRepository.delete(call);
            richiesta.setStato(StatoRichiesta.IN_ATTESA);
            richiestaSupportoRepository.save(richiesta);
            return "Call rifiutata";
        }
    }

    public String inviaRichiestaSupporto(Long idTeam, Long idHackathon, String descrizione){
        if(descrizione == null || descrizione.isBlank()) throw new IllegalArgumentException("Errore, descrizione non valida");

        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);

        if(hackathon == null) throw new IllegalArgumentException("Errore, hackathon non trovato");

        if(hackathon.getStato() != StatoHackathon.IN_CORSO) throw new IllegalArgumentException("Errore, hackathon non in corso");

        Registrazione registrazione = registrazioneRepository.cercaTeamIscritto(idTeam, idHackathon);

        if(registrazione == null) throw new IllegalArgumentException("Errore, team non iscritto");

        RichiestaSupporto richiestaSupporto = new RichiestaSupporto(descrizione, LocalDateTime.now(), registrazione, StatoRichiesta.IN_ATTESA);
        richiestaSupportoRepository.save(richiestaSupporto);

        return "Richiesta creata con successo";
    }

    public List<RichiestaSupporto> visualizzaRichieste(Long idMentore, Long idHackathon){
        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);
        if(hackathon == null) throw new IllegalArgumentException("Errore, hackathon non trovato");

        if(hackathon.getStato() != StatoHackathon.IN_CORSO) throw new IllegalArgumentException("Errore, hackathon non in corso");

        //Controllo che nei mentori dell'hackathon non c'è l'idMentore, in caso lancio un errore
        hackathon.getMentori().forEach(mentore -> {
            if(!mentore.getUtente().getIdUtente().equals(idMentore))
                throw new IllegalArgumentException("Errore, mentore non assegnato");
        });

        return richiestaSupportoRepository.cercaRichiesteInAttesa(idHackathon);
    }

    public String proponiCall(Long idRichiesta, Long idMentore, LocalDateTime data){
        RichiestaSupporto richiesta = richiestaSupportoRepository.cercaRichiesta(idRichiesta);
        if(richiesta == null) throw new IllegalArgumentException("Errore, richiesta non trovata");

        Hackathon hackathon = richiesta.getRegistrazione().getHackathon();

        if(hackathon.getStato() == StatoHackathon.IN_CORSO)
            throw new IllegalArgumentException("Errore, hackathon in corso");

        //cerco il mentore dell'hackathon nel repository utente
        UtenteRegistrato utenteMentore = utenteRegistratoRepository.cercaUtenteId(idMentore);
        if(utenteMentore == null) throw new IllegalArgumentException("Errore, utente non presente");

        //Controllo se i mentori dell'hackathon contengono l'utente
        hackathon.getMentori().forEach(mentoreHackathon -> {
            if(!mentoreHackathon.getUtente().equals(utenteMentore))
                throw new IllegalArgumentException("Errore, mentore non assegnato all'hackathon");
        });

        if(data.isBefore(hackathon.getDataInizio()) || data.isAfter(hackathon.getDataFine()))
            throw new IllegalArgumentException("Errore, data non valida");

        //TODO CAPIRE COME GETSIRE CALENDAR
        // verificare se lo slot è disponibile altrimenti errore slot già occupato
        // prenota slot che restituisce un link

        String link = "link";

        //prendo il mentore dell'hackathon riferito all'utenteMentore
        Mentore mentore = hackathon.getMentori().stream()
                .filter(mentoreHackathon ->
                        mentoreHackathon.getUtente().equals(utenteMentore)).findFirst()
                .orElse(null);
        Call call = new Call(data, link, richiesta, mentore);
        callRepository.save(call);

        richiesta.setCall(call);
        richiesta.setStato(StatoRichiesta.CALL_PROPOSTA);
        richiestaSupportoRepository.save(richiesta);

        return "Call proposta con successo";
    }

    public List<Call> visualizzaCall(Long idTeam, Long idHackathon){
        Registrazione registrazione = registrazioneRepository.cercaTeamIscritto(idTeam, idHackathon);
        if(registrazione == null) throw new IllegalArgumentException("Errore, team non iscritto");

        return callRepository.cercaCallProposte(idTeam, idHackathon);
    }

}
