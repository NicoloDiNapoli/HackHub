package unicam.java.idsproject.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.java.idsproject.model.Hackathon;

import java.time.LocalDate;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
    default Hackathon cercaHackathonId(Long idHackathon) {
        return findById(idHackathon).orElse(null);
    }

}
