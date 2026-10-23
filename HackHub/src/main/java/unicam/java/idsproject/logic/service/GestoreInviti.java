package unicam.java.idsproject.logic.service;


//import unicam.java.idsproject.logic.repository.InvitoRespository;
import unicam.java.idsproject.logic.repository.UtenteRegistratoRepository;
import unicam.java.idsproject.model.*;


public class GestoreInviti {


    //private final InvitoRepository invitoRepository;
    private final UtenteRegistratoRepository utenteRegistratoRepository;

    public GestoreInviti(//InvitoRepository invitoRepository,
                         UtenteRegistratoRepository utenteRegistratoRepository) {
        //this.invitoRepository = invitoRepository;
        this.utenteRegistratoRepository = utenteRegistratoRepository;
    }

    public boolean inviaInvito(long idUtenteRegistrato, Invito invito) {
        UtenteRegistrato invitato = utenteRegistratoRepository.cercaUtenteId(idUtenteRegistrato);
        if (invitato == null || utenteRegistratoRepository.possiedeTeam(idUtenteRegistrato)) {
            return false;
        }
        /*if (invitoRepository.existsByTeamAndInvitato(invito.getTeam(), invitato)) {
            return false;
        }

         */
        invito.setInvitato(invitato);
        //invitoRepository.save(invito);
        return true;
    }
}
