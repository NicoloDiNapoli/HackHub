package unicam.java.idsproject.logic.service;

import lombok.Data;
import unicam.java.idsproject.designPattern.builder.HackathonBuilder;
import unicam.java.idsproject.model.entita.Hackathon;
import unicam.java.idsproject.model.entita.Registrazione;
import unicam.java.idsproject.model.entita.UtenteRegistrato;
import unicam.java.idsproject.model.enumerazione.StatoHackathon;
import unicam.java.idsproject.model.repository.HackathonRepository;
import unicam.java.idsproject.model.repository.RegistrazioneRepository;
import unicam.java.idsproject.model.repository.TeamRepository;
import unicam.java.idsproject.model.repository.UtenteRegistratoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
public class GestoreHackathon {
    private UtenteRegistratoRepository utenteRegistratoRepository;
    private HackathonRepository hackathonRepository;
    private TeamRepository teamRepository;
    private RegistrazioneRepository registrazioneRepository;
    //private GestoreIscrizioni gestoreIscrizioni;

    public String creaHackathon(Long idOrganizzatore, String regolamento,
                                LocalDateTime scadenzaIscrizioni, LocalDateTime dataInizio,
                                LocalDateTime dataFine, String luogo, float premioInDenaro,
                                int dimensioneTeam, String nomeGiudice,
                                List<String> nomeMentori, String nome) {

        if(!controllaDati(idOrganizzatore, regolamento, scadenzaIscrizioni,
                dataInizio, dataFine, luogo, premioInDenaro, dimensioneTeam, nomeGiudice, nomeMentori, nome))
            return "Vincoli non rispettati";

        UtenteRegistrato utenteOrganizzatore = utenteRegistratoRepository.cercaUtenteId(idOrganizzatore);
        if(utenteOrganizzatore == null) throw new IllegalArgumentException("Errore, utente non trovato");

        UtenteRegistrato utenteGiudice = utenteRegistratoRepository.cercaUtenteByNome(nomeGiudice);
        if(utenteGiudice == null) throw new IllegalArgumentException("Errore, utente non trovato");

        List<UtenteRegistrato> utentiMentore = utenteRegistratoRepository.cercaUtentiByNome(nomeMentori);

        Hackathon hackathon = new HackathonBuilder()
                .setNome(nome)
                .setRegolamento(regolamento)
                .setScadenzaIscrizioni(scadenzaIscrizioni)
                .setDataInizio(dataInizio)
                .setDataFine(dataFine)
                .setLuogo(luogo)
                .setPremio(premioInDenaro)
                .setStatoHackathon(StatoHackathon.IN_ISCRIZIONE)
                .setDimensioneTeamMax(dimensioneTeam)
                .setGiudice(utenteGiudice)
                .setMentori(utentiMentore)
                .setOrganizzatore(utenteOrganizzatore)
                .build();

        hackathonRepository.save(hackathon);
        return "Hackathon creato con successo";
    }



    public String proclamaVincitore(Long idOrganizzatore, Long idHackathon, Long idTeam){
        Hackathon hackathon = hackathonRepository.cercaHackathonId(idHackathon);
        if(hackathon == null)
            throw new IllegalArgumentException("Errore, hackathon non trovato");

        if(!Objects.equals(idOrganizzatore, hackathon.getOrganizzatore().getIdMembroStaff()))
            throw new IllegalArgumentException("Errore, l'utente non è l'organizzatore dell'hackathon");

        Registrazione registrazione = registrazioneRepository.cercaTeamIscritto(idTeam, idHackathon);
        if(registrazione == null)
            throw new IllegalArgumentException("Errore, team non iscritto");
        if(hackathon.getStato() != StatoHackathon.IN_VALUTAZIONE)
            throw new IllegalArgumentException("Errore, hackathon non in valutazione");

        hackathon.setVincitore(registrazione);
        hackathon.setStato(StatoHackathon.CONCLUSO);

        hackathonRepository.save(hackathon);

        return "Vincitore proclamato con successo";
    }

    private boolean controllaDati(Long organizzatore, String regolamento,
                                  LocalDateTime scadenzaIscrizioni, LocalDateTime dataInizio,
                                  LocalDateTime dataFine, String luogo, float premioInDenaro,
                                  int dimensioneTeam, String giudice,
                                  List<String> mentori, String nome) {
        if (organizzatore == null)
            throw new IllegalArgumentException("Null organizzatore");
        if (regolamento == null || regolamento.isBlank())
            throw new IllegalArgumentException("Null o vuoto regolamento");
        if (scadenzaIscrizioni == null)
            throw new IllegalArgumentException("Null scadenzaIscrizioni");
        if (dataInizio == null)
            throw new IllegalArgumentException("Null dataInizio");
        if (dataFine == null)
            throw new IllegalArgumentException("Null dataFine");
        if (luogo == null || luogo.isBlank())
            throw new IllegalArgumentException("Null o vuoto luogo");
        if (giudice == null)
            throw new IllegalArgumentException("Null giudice");
        if (premioInDenaro < 0) {
            throw new IllegalStateException("Il premio non può essere negativo.");
        }
        if (dimensioneTeam <= 0) {
            throw new IllegalStateException("La dimensione massima del team deve essere almeno 1.");
        }
        if (!scadenzaIscrizioni.isBefore(dataInizio)) {
            throw new IllegalStateException("La scadenza delle iscrizioni deve essere precedente alla data di inizio.");
        }

        if (!dataInizio.isBefore(dataFine)) {
            throw new IllegalStateException("La data di inizio deve essere precedente alla data di fine.");
        }

        if (mentori != null) {
            for (String utenteMentore : mentori) {
                if (utenteMentore == null) {
                    throw new IllegalStateException("La lista dei mentori non può contenere elementi null.");
                }
                if (utenteMentore.equals(giudice)) {
                    throw new IllegalStateException("Il giudice non può ricoprire anche il ruolo di mentore.");
                }
            }
            long mentoriUnici = mentori.stream().distinct().count();
            if (mentoriUnici != mentori.size()) {
                throw new IllegalStateException("La lista dei mentori contiene utenti duplicati.");
            }
        }
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Null o vuoto nome");
        return true;
    }
}




