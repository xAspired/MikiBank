import java.io.Serializable;

public class Cliente implements Serializable {
    String nome;
    String cognome;
    String codiceFiscale;

    public Cliente(String nome, String cognome, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
    }
}
