package unicam.java.idsproject.logic.service;


import unicam.java.idsproject.logic.repository.HackathonRepository;
import unicam.java.idsproject.logic.repository.SottomissioneRepository;
import unicam.java.idsproject.model.Hackathon;
import unicam.java.idsproject.model.Sottomissione;
import unicam.java.idsproject.model.StatoHackathon;
import unicam.java.idsproject.model.Valutazione;


public class GestoreValutazione {

    private SottomissioneRepository sottomissioneRepository;
    private HackathonRepository hackathonRepository;

    public String valutaSottomissione(Long idHackathon, Long idTeam, int voto, String giudizio, Long idGiudice) {
        if(voto < 0 || voto > 10)
            throw new IllegalArgumentException("Errore, voto fuori intervallo (0 - 10)");

        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);
        if(hackathon == null)
            throw new IllegalArgumentException("Errore, non trovato");
        if(hackathon.getStato() != StatoHackathon.IN_VALUTAZIONE)
            throw new IllegalArgumentException("Errore, non in fase di valutazione");
        if(hackathon.getGiudice().getIdMembroStaff().equals(idGiudice))
            throw new IllegalArgumentException("Errore, giudice non assegnato all'Hackathon");

        Sottomissione sottomissione = sottomissioneRepository.cercaSottomissione(idHackathon, idTeam);
        if(sottomissione == null)
            throw new IllegalArgumentException("Errore, sottomissione non trovata");

        Valutazione valutazione = new Valutazione(giudizio, voto, idGiudice);
        sottomissione.setValutazione(valutazione);
        sottomissioneRepository.save(sottomissione);

        return "Valutazione inserita con successo";
    }
}