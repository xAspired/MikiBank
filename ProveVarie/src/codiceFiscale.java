import java.io.Serializable;

public class codiceFiscale implements Serializable {
    String codiceFiscale;

    public codiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String toString() {
        return codiceFiscale;
    }
}
