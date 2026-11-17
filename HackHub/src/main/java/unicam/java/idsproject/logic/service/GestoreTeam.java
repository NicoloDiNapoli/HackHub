package unicam.java.idsproject.logic.service;

import unicam.java.idsproject.model.repository.TeamRepository;
import unicam.java.idsproject.model.repository.UtenteRegistratoRepository;
import unicam.java.idsproject.model.entita.Invito;
import unicam.java.idsproject.model.entita.Team;
import unicam.java.idsproject.model.entita.UtenteRegistrato;

import java.util.List;


public class GestoreTeam {

    private final UtenteRegistratoRepository utenteRegistratoRepository;
    private final TeamRepository teamRepository;
    private final GestoreInvito gestoreInvito;

    public GestoreTeam(UtenteRegistratoRepository utenteRegistratoRepository,
                       TeamRepository teamRepository,
                       GestoreInvito gestoreInvito) {
        this.utenteRegistratoRepository = utenteRegistratoRepository;
        this.teamRepository = teamRepository;
        this.gestoreInvito = gestoreInvito;
    }

    public String creaTeam(Long idCreatore, List<UtenteRegistrato> utenti, String nomeTeam) {
        if(teamRepository.cercaTeamByNome(nomeTeam) != null)
            throw new IllegalArgumentException("Errore, nomeTeam già esistente");

        UtenteRegistrato creatore = utenteRegistratoRepository.cercaUtenteId(idCreatore);
        if(creatore.getTeam() != null)
            throw new IllegalArgumentException("Errore, creatore fa già parte di un Team");

        utenti.forEach(utenteRegistrato -> {
            if(utenteRegistratoRepository.cercaUtenteId(utenteRegistrato.getIdUtente()) == null)
                throw new IllegalArgumentException("Errore, utente inesistente");

            if(utenteRegistrato.getTeam() != null)
                throw new IllegalArgumentException("Errore, l'utente fa già parte di un Team");
        });

        Team team = new Team(nomeTeam);
        teamRepository.save(team);

        creatore.setTeam(team);
        utenteRegistratoRepository.save(creatore);

        utenti.forEach(utenteRegistrato -> {
            Invito invito = new Invito(team, utenteRegistrato);
            // MODIFICA APPORTATA INFASE DI IMPLEMENTAZIONE
            // gestoreInvito.inviaInvito(utenteRegistrato.getIdUtente(), invito);
            gestoreInvito.invitaUtente(utenteRegistrato.getNomeUtente(), team);
        });

        return "Team creato con successo";
    }

 }