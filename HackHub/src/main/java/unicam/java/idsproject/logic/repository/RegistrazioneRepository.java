package unicam.java.idsproject.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unicam.java.idsproject.model.Registrazione;

//TODO --> aggiornare VPP
public interface RegistrazioneRepository extends JpaRepository<Registrazione, Long> {
    @Query("SELECT r FROM Registrazione r WHERE r.team.idTeam = :idTeam AND r.hackathon.idHackathon = :idHackathon")
    Registrazione findByIdTeamAndIdHackathon(@Param("idTeam") long idTeam, @Param("idHackathon") long idHackathon);
}
