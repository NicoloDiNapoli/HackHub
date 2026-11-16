package unicam.java.idsproject.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.java.idsproject.model.UtenteRegistrato;

public interface UtenteRegistratoRepository extends JpaRepository<UtenteRegistrato, Long> {

    default UtenteRegistrato cercaUtenteId(Long idUtente) {
        return findById(idUtente).orElse(null);
    }

    // ATTENZIONE: "nome" non e' univoco. Se possibile usare uno username o l'email.
    UtenteRegistrato findFirstByNome(String nome);

    default UtenteRegistrato cercaUtenteByNome(String nomeUtente) {
        return findFirstByNome(nomeUtente);
    }
    // save, update (= save) e findAllById ereditati da JpaRepository
}
