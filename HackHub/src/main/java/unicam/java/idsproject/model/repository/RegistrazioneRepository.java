package unicam.java.idsproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unicam.java.idsproject.model.entita.Registrazione;

public interface RegistrazioneRepository extends JpaRepository<Registrazione, Long> {
    @Query("SELECT r FROM Registrazione r " +
            "WHERE r.team.idTeam = :idTeam AND r.hackathon.idHackathon = :idHackathon")
    Registrazione cercaTeamIscritto(@Param("idTeam") Long idTeam,
                                    @Param("idHackathon") Long idHackathon);
}
