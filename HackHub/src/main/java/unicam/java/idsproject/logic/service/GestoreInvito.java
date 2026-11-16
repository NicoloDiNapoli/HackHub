package unicam.java.idsproject.logic.service;


//import unicam.java.idsproject.logic.repository.InvitoRespository;
import unicam.java.idsproject.logic.repository.UtenteRegistratoRepository;
import unicam.java.idsproject.model.Invito;


public class GestoreInvito {


    //private final InvitoRepository invitoRepository;
    private final UtenteRegistratoRepository utenteRegistratoRepository;

    public GestoreInvito(//InvitoRepository invitoRepository,
                         UtenteRegistratoRepository utenteRegistratoRepository) {
        //this.invitoRepository = invitoRepository;
        this.utenteRegistratoRepository = utenteRegistratoRepository;
    }



    // inviaInvito(idUtenteRegistrato : Long, invito : Iterazione2.Class Diagram.Class Diagram di Progetto.Core.Model.Invito) : String
    public String inviaInvito(Long idUtenteRegistrato, Invito invito){
        String prova = "provca";
        return prova;
    }
}
