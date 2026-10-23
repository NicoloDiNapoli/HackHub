package unicam.java.idsproject.logic.service;

import unicam.java.idsproject.logic.repository.HackathonRepository;
import unicam.java.idsproject.logic.repository.RegistrazioneRepository;
import unicam.java.idsproject.logic.repository.TeamRepository;
import unicam.java.idsproject.model.Hackathon;
import unicam.java.idsproject.model.Registrazione;
import unicam.java.idsproject.model.Sottomissione;

import java.time.LocalDate;

public class GestoreSottomissione {
    private HackathonRepository hackathonRepository;
    private TeamRepository teamRepository;
    private RegistrazioneRepository registrazioneRepository;


    public boolean inviaSottomissione(long idHackathon, long idTeam, String file, String titolo){
        if(idHackathon < 0 || idTeam < 0 || file==null || titolo == null) return false;
        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);

        if(hackathon == null) return false;

        if(hackathon.getScadenzaIscrizioni().isBefore(LocalDate.now())) return false;

        //TODO --> diventa caricaFile, aggiornare VPP
//        Registrazione teamRegistrato = registrazioneRepository.findByIdTeamAndIdHackathon(idTeam, idHackathon);
//        if(teamRegistrato == null) return false;
//
//        Sottomissione sottomissione = teamRegistrato.getSottomissione();
//        if(sottomissione == null) {
//            sottomissione = new Sottomissione(file, titolo);
//            teamRegistrato.setSottomissione(sottomissione);
//            return true;
//        }else{
//            sottomissione.setFile(file);
//            sottomissione.setTitolo(titolo);
//            return true;
//        }


        return true;
    }

}
