package unicam.java.idsproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.java.idsproject.model.entita.RichiestaSupporto;
import unicam.java.idsproject.model.enumerazione.StatoRichiesta;

import java.util.List;

public interface RichiestaSupportoRepository extends JpaRepository<RichiestaSupporto, Long> {
    default RichiestaSupporto cercaRichiesta(Long idRichiesta) {
        return findById(idRichiesta).orElse(null);
    }

    List<RichiestaSupporto> findByRegistrazione_Hackathon_IdHackathonAndStato(Long idHackathon, StatoRichiesta stato);

    default List<RichiestaSupporto> cercaRichiesteInAttesa(Long idHackathon) {
        return findByRegistrazione_Hackathon_IdHackathonAndStato(idHackathon, StatoRichiesta.IN_ATTESA);
    }
}
