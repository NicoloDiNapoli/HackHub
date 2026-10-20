package unicam.java.idsproject.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.java.idsproject.model.Hackathon;

import java.time.LocalDate;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
    default Hackathon cercaHackathonId(long idHackathon) {
        return findById(idHackathon).orElse(null);
    }

    default boolean scadenzaSuperata(long idHackathon) {
        Hackathon hackathon = cercaHackathonId(idHackathon);
        return hackathon != null &&
                hackathon.getScadenzaIscrizioni() != null &&
                hackathon.getScadenzaIscrizioni().isBefore(LocalDate.now()); //se la scadenza è il 10, il 10 ancora poso iscrivermi
    }; //TODO
}
