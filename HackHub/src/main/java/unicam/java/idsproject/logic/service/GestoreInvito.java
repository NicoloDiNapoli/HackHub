package unicam.java.idsproject.logic.service;


//import unicam.java.idsproject.model.repository.InvitoRespository;
import unicam.java.idsproject.model.repository.InvitoRepository;
import unicam.java.idsproject.model.repository.UtenteRegistratoRepository;
import unicam.java.idsproject.model.entita.Invito;
import unicam.java.idsproject.model.entita.Team;
import unicam.java.idsproject.model.entita.UtenteRegistrato;

import java.util.List;


public class GestoreInvito {
    private UtenteRegistratoRepository utenteRegistratoRepository;
    private InvitoRepository invitoRepository;

    public List<Invito> visualizzaInviti(Long idUtenteRegistrato){
        List<Invito> inviti = invitoRepository.cercaInviti(idUtenteRegistrato);

        if(inviti == null)
            return null;

        return inviti;
    }

    public String gestisciInvito(Invito invito, Long idUtenteRegistrato, boolean scelta){
        if(invito == null)
            throw new IllegalArgumentException("Invito non trovato");

        if(!scelta) {
            invitoRepository.delete(invito);
            return "Invito rifiutato";
        }

        UtenteRegistrato utente = utenteRegistratoRepository.cercaUtenteId(idUtenteRegistrato);
        if(utente.getTeam() != null)
            throw new IllegalArgumentException("Errore, l'utente appartiene ad un team");

        utente.setTeam(invito.getTeam());
        utenteRegistratoRepository.save(utente);
        invitoRepository.delete(invito);

        return "Invito accettato";
    }

    // +invitaUtente(nomeUtente : String, team : Team) : String
    public String invitaUtente(String nomeUtente, Team team){
        UtenteRegistrato utente = utenteRegistratoRepository.cercaUtenteByNome(nomeUtente);
        if(utente == null)
            throw new IllegalArgumentException("Errore, utente non trovato");

        if(utente.getTeam() != null)
            throw new IllegalArgumentException("Errore, l'utente appartiene ad un team");

        team.getInviti().forEach(invito -> {
            if(invito.getInvitato().equals(utente))
                throw new IllegalArgumentException("Errore, utente già invitato nel team");
        });

        Invito invito = new Invito(team, utente);
        invitoRepository.save(invito);

        return "Invito inviato con successo";
    }

}
