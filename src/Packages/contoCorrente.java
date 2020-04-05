package Packages;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public ArrayList<listaMovimenti> listaMovimenti = new ArrayList<>();
    /*
    Conto Corrente
     */
    private String IBAN;
    private float saldo;
    private float interesse;
    private float saldoDisponibile=0;
    private float saldoContabile=0;
    String tipoConto;
    private String statoConto;
    private LocalDate dataUltimoPagamento;


    /*
     * =========================================================
     * Costruttori
     * =========================================================
     */
    public contoCorrente(String IBAN, float saldoDisponibile, float saldoContabile, ArrayList<listaMovimenti> listaMovimenti, float interesse, infoCliente[] cointestatari, String tipoConto, String statoConto, LocalDate dataUltimoPagamento) {
        this.IBAN = IBAN;
        this.saldoDisponibile = saldoDisponibile;
        this.saldoContabile = saldoContabile;
        this.listaMovimenti = listaMovimenti;
        this.interesse = interesse;
        this.tipoConto = tipoConto;
        this.cointestatari = cointestatari;
        this.statoConto = statoConto;
        this.dataUltimoPagamento = dataUltimoPagamento;
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

    public String getTipoConto() {
        return tipoConto;
    }

    public void setTipoConto(String tipoConto) {
        this.tipoConto = tipoConto;
    }

    public ArrayList<contoCorrente.listaMovimenti> getListaMovimenti(){
        return listaMovimenti;
    }

    public String getStatoConto(){
        return statoConto;
    }

    public void setStatoConto(String statoConto) {
        this.statoConto = statoConto;
    }

    public LocalDate getDataUltimoPagamento() {
        return dataUltimoPagamento;
    }

    public void setDataUltimoPagamento(LocalDate dataUltimoPagamento) {
        this.dataUltimoPagamento = dataUltimoPagamento;
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
        private String descrizioneOperazione;
        private LocalDate date;

        public listaMovimenti(){}

        public listaMovimenti (float importoDisponibile, String dataDisponibile, float importoContabile, String dataContabile, int movimentoAttuale, String descrizioneOperazione, LocalDate date){
            this.importoDisponibile=importoDisponibile;
            this.dataDisponibile=dataDisponibile;
            this.importoContabile=importoContabile;
            this.dataContabile=dataContabile;
            this.descrizioneOperazione=descrizioneOperazione;
            this.date = date;

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

        public LocalDate getDate(){
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }
    }

    public void toStringListaMovimenti(){
        boolean verifica = false;
        int intLunghezzaDescrizione;
        int numeroNull = 0;
        int verificaMovimenti;
        String importoDisponibile = "";
        StringBuilder stringLunghezzaDescrizione = new StringBuilder();


        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("| Data Contabile - Data Valuta - Descrizione Operazione - Importo |");
        System.out.println("+-----------------------------------------------------------------+");

        //noinspection ManualMinMaxCalculation
        if(listaMovimenti.size()<10)
            verificaMovimenti=listaMovimenti.size();
        else
            verificaMovimenti=10;

        if(verificaMovimenti == 0)
            System.out.print("\n\t\t|| Ancora nessun movimento. Deposita per iniziare! ||");
        else {
            for (int i = listaMovimenti.size()-1; i >=listaMovimenti.size()-verificaMovimenti; i--) {

                //Si gestisce lo spazio tra la descrizione e l'importo
                if (listaMovimenti.get(i).getDescrizioneOperazione() != null && listaMovimenti.get(i).getDescrizioneOperazione().length() < 24) {
                    intLunghezzaDescrizione = 24 - listaMovimenti.get(i).getDescrizioneOperazione().length();
                    stringLunghezzaDescrizione = new StringBuilder(listaMovimenti.get(i).getDescrizioneOperazione());
                    for(int j = 0; j < intLunghezzaDescrizione; j++) {
                        stringLunghezzaDescrizione.append(" ");
                    }
                    //stringLunghezzaDescrizione.append(" ".repeat(Math.max(0, intLunghezzaDescrizione))); /* for(int j = 0; j < intLunghezzaDescrizione; j++) { */
                }

                if (listaMovimenti.get(i) != null) {
                    //Viene gestito l'allineamento dell'importo in caso esso sia negativo
                    if (listaMovimenti.get(i).getImportoDisponibile() < 0)
                        importoDisponibile = "" + listaMovimenti.get(i).getImportoDisponibile();
                    else
                        importoDisponibile = " " + listaMovimenti.get(i).getImportoDisponibile();

                }


                if (listaMovimenti.get(i) != null && listaMovimenti.get(i).getImportoDisponibile() != 0)
                    System.out.println("   " + listaMovimenti.get(i).getDataContabile() + "      " + listaMovimenti.get(i).getDataDisponibile() + "     " + stringLunghezzaDescrizione + importoDisponibile + "€");
            }
        }


    }
}


