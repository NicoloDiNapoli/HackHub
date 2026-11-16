package unicam.java.idsproject.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.java.idsproject.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByNome(String nome);

    default Team cercaTeamByNome(String nomeTeam) {
        return findByNome(nomeTeam);
    }

    default Team cercaTeamById(Long idTeam) {
        return findById(idTeam).orElse(null);
    }
    // save(team) ereditato da JpaRepository
}
