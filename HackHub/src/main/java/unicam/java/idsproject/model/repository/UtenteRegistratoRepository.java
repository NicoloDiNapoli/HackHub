package unicam.java.idsproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.java.idsproject.model.entita.UtenteRegistrato;

import java.util.ArrayList;
import java.util.List;

public interface UtenteRegistratoRepository extends JpaRepository<UtenteRegistrato, Long> {

    default UtenteRegistrato cercaUtenteId(Long idUtente) {
        return findById(idUtente).orElse(null);
    }

    UtenteRegistrato findFirstByNome(String nome);

    default UtenteRegistrato cercaUtenteByNome(String nomeUtente) {
        return findFirstByNome(nomeUtente);
    }

    List<UtenteRegistrato> findAllByNomeIn(List<String> nomi);

    default List<UtenteRegistrato> cercaUtentiByNome(List<String> nomiUtente) {
        if (nomiUtente == null || nomiUtente.isEmpty()) {
            return new ArrayList<>();
        }
        return findAllByNomeIn(nomiUtente);
    }

}
