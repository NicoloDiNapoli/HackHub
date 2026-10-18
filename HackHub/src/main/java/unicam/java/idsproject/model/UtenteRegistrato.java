package unicam.java.idsproject.model;

public class UtenteRegistrato {
    private long idUtente;
    private final String nome;
    private final String cognome;
    private final String email;

    public UtenteRegistrato(String nome, String cognome, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public long getIdUtente() { return idUtente; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getEmail() { return email; }
}
