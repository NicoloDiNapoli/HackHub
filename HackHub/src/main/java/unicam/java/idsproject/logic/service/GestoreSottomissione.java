package unicam.java.idsproject.logic.service;

import unicam.java.idsproject.logic.repository.HackathonRepository;
import unicam.java.idsproject.logic.repository.RegistrazioneRepository;
import unicam.java.idsproject.logic.repository.SottomissioneRepository;
import unicam.java.idsproject.logic.repository.TeamRepository;
import unicam.java.idsproject.model.Hackathon;
import unicam.java.idsproject.model.Registrazione;
import unicam.java.idsproject.model.Sottomissione;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GestoreSottomissione {
    private HackathonRepository hackathonRepository;
    private TeamRepository teamRepository;
    private RegistrazioneRepository registrazioneRepository;
    private SottomissioneRepository sottomissioneRepository;


    public String inviaSottomissione(Long idTeam, Long idHackathon, String file, String titolo){
        if(file == null)
            throw new IllegalArgumentException("Errore, file inesistente");

        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);
        if (hackathon == null || hackathon.getScadenzaIscrizioni().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Errore, scadenza superata");

        Registrazione registrazione = registrazioneRepository.cercaTeamIscritto(idTeam, idHackathon);
        if(registrazione == null)
            throw new IllegalArgumentException("Errore, team non iscritto all'hackathon");

        if(registrazione.getSottomissione() == null){
            Sottomissione sottomissione = new Sottomissione(file, titolo);
            sottomissioneRepository.save(sottomissione);

            registrazione.setSottomissione(sottomissione);
            registrazioneRepository.save(registrazione);
        }else{
            Sottomissione vecchiaSottomissione = registrazione.getSottomissione();

            vecchiaSottomissione.setFile(file);
            vecchiaSottomissione.setTitolo(titolo);

            sottomissioneRepository.save(vecchiaSottomissione);
        }

        return "Sottomissione inviata con successo";
    }
}
