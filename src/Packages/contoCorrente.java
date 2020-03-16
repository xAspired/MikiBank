package Packages;
import java.io.Serializable;

@SuppressWarnings("unused")
public class contoCorrente extends infoCliente implements Serializable {
    private static final long serialVersionUID = -1662022300670224623L;

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
    private float saldoValuta;
    private float saldoContabile;
    String [] listaMovimenti = new String [10];
    private float saldo;
    private float interesse;
    private String tipoConto;

    /*
     * =========================================================
     * Costruttori
     * =========================================================
     */
    public contoCorrente(String IBAN, float saldoValuta, float saldoContabile, String []listaMovimenti, float interesse, infoCliente[] cointestatari, String tipoConto) {
        this.IBAN = IBAN;
        this.saldoValuta = saldoValuta;
        this.saldoContabile = saldoContabile;
        this.listaMovimenti = listaMovimenti;
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

    public void setInteresse(float interesse) {
        this.interesse = interesse;
    }

    public float getInteresse() {
        return interesse;
    }

    public float getSaldoValuta() {
        return saldoValuta;
    }

    public void setSaldoValuta(float saldoValuta) {
        this.saldoValuta = saldoValuta;
    }

    public void setSaldoContabile(float saldoContabile) {
        this.saldoContabile = saldoContabile;
    }

    public float getSaldoContabile() {
        return saldoContabile;
    }

    public infoCliente[] getCointestatari() {
        return cointestatari;
    }


    public String[] getListaMovimenti(){
        return listaMovimenti;
    }

    @Override
    public String toString() {
        return "Conto [" +
                "IBAN='" + IBAN + '\'' +
                ", saldo=" + saldo +
                ", interesse=" + interesse + cointestatari +
                ']';
    }
}
