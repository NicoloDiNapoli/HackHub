package unicam.java.idsproject.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.java.idsproject.model.UtenteRegistrato;

public interface UtenteRegistratoRepository extends JpaRepository<UtenteRegistrato, Long> {
    default UtenteRegistrato cercaUtenteId(long idUtente){
        return findById(idUtente).orElse(null);
    }

    default boolean possiedeTeam(long idUtente){
        return cercaUtenteId(idUtente).getTeam() != null;
    }

}
