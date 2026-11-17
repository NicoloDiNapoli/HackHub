package unicam.java.idsproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.java.idsproject.model.entita.Hackathon;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
    default Hackathon cercaHackathonId(Long idHackathon) {
        return findById(idHackathon).orElse(null);
    }

}
