package unicam.java.idsproject.model;

public abstract class MembroStaff {
    private final UtenteRegistrato utente;
    private final Hackathon hackathon;

    protected MembroStaff(UtenteRegistrato utente, Hackathon hackathon) {
        this.utente = utente;
        this.hackathon = hackathon;
    }

    public UtenteRegistrato getUtente() { return utente; }
    public Hackathon getHackathon() { return hackathon; }
}



