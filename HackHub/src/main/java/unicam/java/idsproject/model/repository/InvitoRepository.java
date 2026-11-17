package unicam.java.idsproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.java.idsproject.model.entita.Invito;
import unicam.java.idsproject.model.entita.Team;
import unicam.java.idsproject.model.entita.UtenteRegistrato;

import java.util.List;

public interface InvitoRepository extends JpaRepository<Invito, Long> {
    default Invito cercaInvito(Long idInvito) {
        return findById(idInvito).orElse(null);
    }

    List<Invito> findByInvitato_IdUtente(Long idUtente);

    default List<Invito> cercaInviti(Long idUtenteRegistrato) {
        return findByInvitato_IdUtente(idUtenteRegistrato);
    }

    // serve al controllo "utente gia' invitato nel team" (manca nel sequence di InvitaUtenti)
    boolean existsByTeamAndInvitato(Team team, UtenteRegistrato invitato);
}
