package unicam.java.idsproject.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unicam.java.idsproject.model.Call;

import java.util.List;

public interface CallRepository extends JpaRepository<Call, Long> {
    default Call cercaCallId(Long idCall) {
        return findById(idCall).orElse(null);
    }

    // solo le richieste con stato CALL_PROPOSTA (come da nota nel VPP)
    @Query("SELECT r.call FROM RichiestaSupporto r " +
            "WHERE r.registrazione.team.idTeam = :idTeam " +
            "AND r.registrazione.hackathon.idHackathon = :idHackathon " +
            "AND r.stato = unicam.java.idsproject.model.StatoRichiesta.CALL_PROPOSTA")
    List<Call> cercaCallProposte(@Param("idTeam") Long idTeam,
                                 @Param("idHackathon") Long idHackathon);
}

