package unicam.java.idsproject.logic.service;


import unicam.java.idsproject.logic.repository.SottomissioneRepository;
import unicam.java.idsproject.logic.service.GestoreHackathon;
import unicam.java.idsproject.model.Sottomissione;
import unicam.java.idsproject.model.StatoHackathon;
import unicam.java.idsproject.model.Valutazione;


public class GestoreValutazione {

    private final GestoreHackathon gestoreHackathon;
    private final SottomissioneRepository sottomissioneRepository;

    public GestoreValutazione(GestoreHackathon gestoreHackathon, SottomissioneRepository sottomissioneRepository) {
        this.gestoreHackathon = gestoreHackathon;
        this.sottomissioneRepository = sottomissioneRepository;
    }

    //TODO PRX ITER
    public Valutazione valutaSottomissione(long idHackathon, long idTeam, int voto, String giudizio) {
        if (voto < 0 || voto > 10) {
            throw new IllegalArgumentException("Voto fuori intervallo (0-10)");
        }
        /*
        if (gestoreHackathon.verificaFaseHackathon(idHackathon) != StatoHackathon.IN_VALUTAZIONE) {
            throw new IllegalStateException("L'hackathon non e in fase di valutazione");
        }
        */

        Sottomissione sottomissione = null;/*= gestoreHackathon.cercaSottomissione(idHackathon, idTeam);*/

        if (sottomissione == null) {
            throw new IllegalArgumentException("Nessuna sottomissione per questo team");
        }

        Valutazione valutazione = new Valutazione();
        valutazione.setPunteggio(voto);
        valutazione.setGiudizio(giudizio);
        sottomissione.setValutazione(valutazione);
        //return sottomissioneRepository.save(sottomissione);
        return valutazione;

    }
}