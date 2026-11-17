package unicam.java.idsproject.logic.service;

import unicam.java.idsproject.model.repository.HackathonRepository;
import unicam.java.idsproject.model.repository.RegistrazioneRepository;
import unicam.java.idsproject.model.repository.TeamRepository;
import unicam.java.idsproject.model.repository.UtenteRegistratoRepository;
import unicam.java.idsproject.model.entita.Hackathon;
import unicam.java.idsproject.model.entita.Registrazione;
import unicam.java.idsproject.model.enumerazione.StatoHackathon;
import unicam.java.idsproject.model.entita.Team;

import java.time.LocalDateTime;

public class GestoreRegistrazione {
    private GestoreHackathon gestoreHackathon;
    private GestoreTeam gestoreTeam;
    private TeamRepository teamRepository;
    private UtenteRegistratoRepository utenteRegistratoRepository;
    private HackathonRepository hackathonRepository;
    private RegistrazioneRepository registrazioneRepository;

    public GestoreRegistrazione(GestoreHackathon gestoreHackathon, GestoreTeam gestoreTeam, TeamRepository teamRepository, UtenteRegistratoRepository utenteRegistratoRepository, HackathonRepository hackathonRepository) {
        this.gestoreHackathon = gestoreHackathon;
        this.gestoreTeam = gestoreTeam;
        this.teamRepository = teamRepository;
        this.utenteRegistratoRepository = utenteRegistratoRepository;
        this.hackathonRepository = hackathonRepository;
    }

    public String iscriviTeam(Long idHackathon, Long idTeam) {
        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);
        if(hackathon == null)
            throw  new IllegalArgumentException("Errore, Hackathon non trovato");

        Team team = teamRepository.cercaTeamById(idTeam);
        if(team == null)
            throw  new IllegalArgumentException("Errore, Team non trovato");

        if(hackathon.getStato() != StatoHackathon.IN_ISCRIZIONE || hackathon.getDimensioneTeamMax() < team.getMembri().size())
            throw  new IllegalArgumentException("Errore, vincoli non rispettati");

        if(registrazioneRepository.cercaTeamIscritto(idTeam, idHackathon) != null)
            throw  new IllegalArgumentException("Errore, team già iscritto");

        Registrazione registrazione = new Registrazione(LocalDateTime.now(), team, hackathon);
        registrazioneRepository.save(registrazione);

        return "Team iscritto cobn successo";
    }

}
