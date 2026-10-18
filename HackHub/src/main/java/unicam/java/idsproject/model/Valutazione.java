package unicam.java.idsproject.model;

public class Valutazione {
    private long id;
    private String giudizio;
    private int punteggio;

    public Valutazione(long id, String giudizio, int punteggio) {
        this.id = id;
        this.giudizio = giudizio;
        this.punteggio = punteggio;
    }
}
