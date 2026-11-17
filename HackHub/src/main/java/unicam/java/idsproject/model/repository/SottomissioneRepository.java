package unicam.java.idsproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unicam.java.idsproject.model.entita.Sottomissione;

public interface SottomissioneRepository extends JpaRepository<Sottomissione, Long> {
    @Query("SELECT r.sottomissione FROM Registrazione r " +
            "WHERE r.hackathon.idHackathon = :idHackathon AND r.team.idTeam = :idTeam")
    Sottomissione cercaSottomissione(@Param("idHackathon") Long idHackathon,
                                     @Param("idTeam") Long idTeam);
}
