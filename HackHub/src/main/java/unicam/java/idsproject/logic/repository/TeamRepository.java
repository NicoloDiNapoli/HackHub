package unicam.java.idsproject.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.java.idsproject.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByNome(String name);

    Team findById(long id);

    // TODO
    // dal nome del team e l'hackathonrestituisce tutte le info del team (l'oggetto Team)
    default Team cercaTeamIscritto(String nomeTeam, long idHackathon){
        return findByNome(nomeTeam);
    }

    boolean existsByNome(String nome);

    // dal nome del Team verifica se questultimo esiste
    default boolean teamEsistente(String nomeTeam){
        return existsByNome(nomeTeam);
    }
}
