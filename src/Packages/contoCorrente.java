package Packages;
import java.io.Serializable;

public class contoCorrente extends infoCliente implements Serializable {
    //vettore di massimo 3 cointestatari
    infoCliente[] cointestatari = new infoCliente[3];
    /*
    Movimento
     */
    private String dataContabile;
    private String dataValuta;
    private float importo;

    /*
    Conto Corrente
     */
    private String IBAN;
    private float saldo;
    private float interesse;
    private String tipoConto;

    /*
     * =========================================================
     * Costruttori
     * =========================================================
     */
    public contoCorrente(String IBAN, float saldo, float interesse, infoCliente[] cointestatari, String tipoConto) {
        this.IBAN = IBAN;
        this.saldo = saldo;
        this.interesse = interesse;
        this.cointestatari = cointestatari;
        this.tipoConto = tipoConto;
    }

    public contoCorrente() {}

    public void setDataContabile(String dataContabile) {
        this.dataContabile = dataContabile;
    }

    public String getDataContabile() {
        return dataContabile;
    }

    public void setDataValuta(String dataValuta) {
        this.dataValuta = dataValuta;
    }

    public String getDataValuta() {
        return dataValuta;
    }

    public void setImporto(float importo) {
        this.importo = importo;
    }

    public float getImporto() {
        return importo;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setInteresse(float interesse) {
        this.interesse = interesse;
    }

    public float getInteresse() {
        return interesse;
    }

    @Override
    public String toString() {
        return "Conto [" +
                "IBAN='" + IBAN + '\'' +
                ", saldo=" + saldo +
                ", interesse=" + interesse +
                ']';
    }
}