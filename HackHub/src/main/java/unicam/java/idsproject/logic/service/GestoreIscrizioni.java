package unicam.java.idsproject.logic.service;

import unicam.java.idsproject.logic.repository.HackathonRepository;
import unicam.java.idsproject.logic.repository.TeamRepository;
import unicam.java.idsproject.logic.repository.UtenteRegistratoRepository;
import unicam.java.idsproject.model.Hackathon;
import unicam.java.idsproject.model.Registrazione;
import unicam.java.idsproject.model.Team;

public class GestoreIscrizioni {
    private GestoreHackathon gestoreHackathon;
    private GestoreTeam gestoreTeam;
    private TeamRepository teamRepository;
    private UtenteRegistratoRepository utenteRegistratoRepository;
    private HackathonRepository hackathonRepository;

    public GestoreIscrizioni(GestoreHackathon gestoreHackathon, GestoreTeam gestoreTeam, TeamRepository teamRepository, UtenteRegistratoRepository utenteRegistratoRepository, HackathonRepository hackathonRepository) {
        this.gestoreHackathon = gestoreHackathon;
        this.gestoreTeam = gestoreTeam;
        this.teamRepository = teamRepository;
        this.utenteRegistratoRepository = utenteRegistratoRepository;
        this.hackathonRepository = hackathonRepository;
    }

    public void iscriviTeam(long idHackathon, long idTeam) {
        if (!gestoreHackathon.controllaHackathon(idHackathon)) {
            throw new IllegalArgumentException("Hackathon inesistente");
        }
        if (!gestoreTeam.controllaTeam(idTeam)) {
            throw new IllegalArgumentException("Team inesistente");
        }
        Team team = teamRepository.findById(idTeam);
        if (!controllaVincoli(idHackathon, team)) {
            throw new IllegalStateException("Vincoli di iscrizione non rispettati");
        }
        gestoreHackathon.iscriviTeam(team, idHackathon);
    }

    private boolean controllaVincoli(long idHackathon, Team team){
        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);
        if (hackathon == null || hackathonRepository.scadenzaSuperata(idHackathon)) return false;
        Registrazione registrazione = hackathon.getRegistrazioni().stream().
                filter(r -> r.getTeam().getIdTeam() == team.getIdTeam())
                .findFirst().orElse(null);

        if(registrazione != null) return false;
        else{
            return team.getMembri().size() <= hackathon.getDimensioneTeamMax();
        }
    }
}
