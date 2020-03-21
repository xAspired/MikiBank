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
    private float importo;
    public listaMovimenti [] listaMovimenti = new listaMovimenti[10];
    /*
    Conto Corrente
     */
    private String IBAN;
    private float saldo;
    private float interesse;
    private String tipoConto;
    private float saldoDisponibile=0;
    private float saldoContabile=0;

    /*
     * =========================================================
     * Costruttori
     * =========================================================
     */
    public contoCorrente(String IBAN, float saldoDisponibile, float saldoContabile, listaMovimenti []listaMovimenti, float interesse, infoCliente[] cointestatari, String tipoConto) {
        this.IBAN = IBAN;
        this.saldoDisponibile = saldoDisponibile;
        this.saldoContabile = saldoContabile;
        this.listaMovimenti = listaMovimenti;
        this.interesse = interesse;
        this.cointestatari = cointestatari;
        this.tipoConto = tipoConto;
    }

    public contoCorrente() {}

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

    public float getSaldoDisponibile() {
        return saldoDisponibile;
    }

    public void setSaldoDisponibile(float saldoDisponibile) {
        this.saldoDisponibile += saldoDisponibile;
    }

    public void setSaldoContabile(float saldoContabile) {
        this.saldoContabile += saldoContabile;
    }

    public float getSaldoContabile() {
        return saldoContabile;
    }

    public infoCliente[] getCointestatari() {
        return cointestatari;
    }


    public listaMovimenti[] getListaMovimenti(){
        for(int i=listaMovimenti.length-1; i>0; i--) {
            listaMovimenti[i] = new listaMovimenti();
        }
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

    public static class listaMovimenti implements Serializable {

        private float importoDisponibile=0;
        private String dataDisponibile="";
        private float importoContabile=0;
        private String dataContabile="";

        public listaMovimenti(){}

        public listaMovimenti (float importoDisponibile, String dataDisponibile, float importoContabile, String dataContabile){
            this.importoDisponibile=importoDisponibile;
            this.dataDisponibile=dataDisponibile;
            this.importoContabile=importoContabile;
            this.dataContabile=dataContabile;
        }

        public float getImportoDisponibile() {
            return importoDisponibile;
        }

        public void setImportoDisponibile(float saldoDisponibile) {
            this.importoDisponibile = saldoDisponibile;
        }

        public float getImportoContabile() {
            return importoContabile;
        }

        public void setImportoContabile(float importoContabile) {
            this.importoContabile = importoContabile;
        }

        public void setDataContabile(String dataContabile) {
            this.dataContabile = dataContabile;
        }

        public String getDataContabile() {
            return dataContabile;
        }

        public void setDataDisponibile(String dataDisponibile) {
            this.dataDisponibile = dataDisponibile;
        }

        public String getDataDisponibile() {
            return dataDisponibile;
        }

    }

    public void toStringListaMovimenti(){
        System.out.println("Lista Movimenti");
        boolean verifica = false;
        for(int i=listaMovimenti.length-1; i>0 && !verifica; i--) {
            listaMovimenti temp = listaMovimenti[i];
            if (temp!=null){
                verifica=true;
                System.out.println("importo disponibile - data disponibile || importo contabile - data contabile");
                System.out.println(listaMovimenti[i].getImportoDisponibile() + "€\t" + listaMovimenti[i].getDataDisponibile() + "\t" + listaMovimenti[i].getImportoContabile() + "€\t" + listaMovimenti[i].getDataContabile());
            }
        }


    }
}


