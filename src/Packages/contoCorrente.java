package Packages;
import java.io.Serializable;
import java.util.Arrays;

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
    private float saldoDisponibile=0;
    private float saldoContabile=0;
    private int movimentoAttuale=9;


    /*
     * =========================================================
     * Costruttori
     * =========================================================
     */
    public contoCorrente(String IBAN, float saldoDisponibile, float saldoContabile, listaMovimenti[] listaMovimenti, float interesse, infoCliente[] cointestatari, String tipoConto, int movimentoAttuale) {
        this.IBAN = IBAN;
        this.saldoDisponibile = saldoDisponibile;
        this.saldoContabile = saldoContabile;
        this.listaMovimenti = listaMovimenti;
        this.interesse = interesse;
        this.cointestatari = cointestatari;
        this.movimentoAttuale=movimentoAttuale;
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
        for(int i=listaMovimenti.length-1; i>=0; i--) {
            if(listaMovimenti[i]==null)
                listaMovimenti[i] = new listaMovimenti();
        }
        return listaMovimenti;
    }

    @Override
    public String toString() {
        return "Conto [" +
                "IBAN='" + IBAN + '\'' +
                ", saldo=" + saldo +
                ", interesse=" + interesse + Arrays.toString(cointestatari) +
                ']';
    }

    public static class listaMovimenti implements Serializable {

        private float importoDisponibile=0;
        private String dataDisponibile="";
        private float importoContabile=0;
        private String dataContabile="";
        private int movimentoAttuale=0;
        private String descrizioneOperazione;

        public listaMovimenti(){}

        public listaMovimenti (float importoDisponibile, String dataDisponibile, float importoContabile, String dataContabile, int movimentoAttuale, String descrizioneOperazione){
            this.importoDisponibile=importoDisponibile;
            this.dataDisponibile=dataDisponibile;
            this.importoContabile=importoContabile;
            this.dataContabile=dataContabile;
            this.descrizioneOperazione=descrizioneOperazione;
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

        public String getDescrizioneOperazione() {
            return descrizioneOperazione;
        }

        public void setDescrizioneOperazione(String descrizioneOperazione) {
            this.descrizioneOperazione = descrizioneOperazione;
        }
    }

    public int getMovimentoAttuale(){
        return movimentoAttuale;
    }
    public void setMovimentoAttuale(int movimentoAttuale){
        this.movimentoAttuale=movimentoAttuale;
    }
    public void toStringListaMovimenti(){
        boolean verifica = false;
        int intLunghezzaDescrizione;
        StringBuilder stringLunghezzaDescrizione = new StringBuilder();


        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("| Data Contabile - Data Valuta - Descrizione Operazione - Importo |");
        System.out.println("+-----------------------------------------------------------------+");
        for(int i=9; i>=0; i--) {
            if(listaMovimenti[i].getDescrizioneOperazione()!=null && listaMovimenti[i].getDescrizioneOperazione().length() < 25) {
                intLunghezzaDescrizione = 25 - listaMovimenti[i].getDescrizioneOperazione().length();
                stringLunghezzaDescrizione = new StringBuilder(listaMovimenti[i].getDescrizioneOperazione());
                stringLunghezzaDescrizione.append(" ".repeat(Math.max(0, intLunghezzaDescrizione))); /* for(int j = 0; j < intLunghezzaDescrizione; j++) { */
            }

            if(listaMovimenti[i]!=null && listaMovimenti[i].getImportoDisponibile()!=0)
                System.out.println("   " + listaMovimenti[i].getDataContabile() + "      " + listaMovimenti[i].getDataDisponibile() + "     " + stringLunghezzaDescrizione + listaMovimenti[i].getImportoDisponibile()+"€");
        }


    }
}


