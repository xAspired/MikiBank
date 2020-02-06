package Packages;

import java.io.Serializable;

public class contiCorrenti implements Serializable {
    String nome;
    String cognome;

    public contiCorrenti(String nome, String cognome) {
        this.nome=nome;
        this.cognome=cognome;
    }

    public contiCorrenti() {}

    @Override
    public String toString() {
        return "Conto {" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }
}
