package unicam.java.idsproject.logic.service;

import unicam.java.idsproject.logic.repository.TeamRepository;
import unicam.java.idsproject.logic.repository.UtenteRegistratoRepository;
import unicam.java.idsproject.model.Invito;
import unicam.java.idsproject.model.Team;
import unicam.java.idsproject.model.UtenteRegistrato;


public class GestoreTeam {

    private final UtenteRegistratoRepository utenteRegistratoRepository;
    private final TeamRepository teamRepository;
    private final GestoreInviti gestoreInviti;

    public GestoreTeam(UtenteRegistratoRepository utenteRegistratoRepository,
                       TeamRepository teamRepository,
                       GestoreInviti gestoreInviti) {
        this.utenteRegistratoRepository = utenteRegistratoRepository;
        this.teamRepository = teamRepository;
        this.gestoreInviti = gestoreInviti;
    }


    public Team creaTeam(UtenteRegistrato creatore, UtenteRegistrato invitati, String nomeTeam) {
        if (nomeTeam == null || nomeTeam.isBlank() || teamRepository.teamEsistente(nomeTeam)) {
            throw new IllegalArgumentException("Nome team non valido o gia esistente");
        }

        if (utenteRegistratoRepository.possiedeTeam(creatore.getIdUtente())) {
         throw new IllegalStateException("L'utente appartiene gia a un team");
        }

        Team team = new Team();
        team.setNome(nomeTeam);
        team = teamRepository.save(team);

        creatore.setTeam(team);
        utenteRegistratoRepository.save(creatore);

        if (invitati != null) {
            gestoreInviti.inviaInvito(invitati.getIdUtente(), new Invito(team, invitati));
        }
        return team;
    }

     public boolean controllaTeam(long idTeam) {
        return teamRepository.existsById(idTeam);
     }
 }