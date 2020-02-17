package Packages;
import java.io.Serializable;

public class infoCliente implements Serializable {
    /*
    Carta d'identità
     */
    private String nome;
    private String cognome;
    private String cartaID; //ID riconoscitivo carta d'identità (CA00000AA) Numero Unico Nazionale
    private String cartaScadenza; //Scadenza carta d'identità
    private String dataDiNascita;
    private String sesso;
    private String codiceFiscale;


    /*
    Certificato di Residenza
     */
    private String cittadinanza;
    private String indirizzoResidenza;
    private int numeroCivico;
    private String comuneResidenza;
    private String statoResidenza;
    private String capResidenza;

    public infoCliente() {}

    public infoCliente(String nome, String cognome ,String cartaID, String cartaScadenza, String dataDiNascita, String sesso, String codiceFiscale, String cittadinanza, String indirizzoResidenza, int numeroCivico, String comuneResidenza, String statoResidenza, String capResidenza) {
         this.nome=nome;
         this.cognome=cognome;
         this.cartaID=cartaID;
         this.cartaScadenza=cartaScadenza;
         this.dataDiNascita=dataDiNascita;
         this.sesso=sesso;
         this.codiceFiscale=codiceFiscale;
         this.cittadinanza=cittadinanza;
         this.indirizzoResidenza=indirizzoResidenza;
         this.numeroCivico=numeroCivico;
         this.comuneResidenza=comuneResidenza;
         this.statoResidenza= statoResidenza;
         this.capResidenza=capResidenza;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCartaID(String cartaID) {
        this.cartaID = cartaID;
    }

    public String getCartaID() {
        return cartaID;
    }

    public void setCartaScadenza(String cartaScadenza) {
        this.cartaScadenza = cartaScadenza;
    }

    public String getCartaScadenza() {
        return cartaScadenza;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getSesso() {
        return sesso;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCittadinanza(String cittadinanza) {
        this.cittadinanza = cittadinanza;
    }

    public String getCittadinanza() {
        return cittadinanza;
    }

    public void setIndirizzoResidenza(String indirizzoResidenza) {
        this.indirizzoResidenza = indirizzoResidenza;
    }

    public String getIndirizzoResidenza() {
        return indirizzoResidenza;
    }

    public void setNumeroCivico(int numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public int getNumeroCivico() {
        return numeroCivico;
    }

    public void setComuneResidenza(String comuneResidenza) {
        this.comuneResidenza = comuneResidenza;
    }

    public String getComuneResidenza() {
        return comuneResidenza;
    }

    public void setStatoResidenza(String statoResidenza) {
        this.statoResidenza = statoResidenza;
    }

    public String getStatoResidenza() {
        return statoResidenza;
    }

    public void setCapResidenza(String capResidenza) {
        this.capResidenza = capResidenza;
    }

    public String getCapResidenza() {
        return capResidenza;
    }

    @Override
    public String toString() {
        return "Cliente [" +
                "Nome:'" + nome + '\'' +
                ", Cognome:'" + cognome + '\'' +
                ", ID='" + cartaID + '\'' +
                ", Data Scadenza CDI:'" + cartaScadenza + '\'' + //Carta di Identità
                ", Data di Nascita:'" + dataDiNascita + '\'' +
                ", Sesso:'" + sesso + '\'' +
                ", Codice Fiscale:'" + codiceFiscale + '\'' +
                ", Cittadinanza:'" + cittadinanza + '\'' +
                ", Indirizzo:'" + indirizzoResidenza + '\'' +
                ", Numero Civico:" + numeroCivico +
                ", Comune:'" + comuneResidenza + '\'' +
                ", Stato:'" + statoResidenza + '\'' +
                ", CAP:'" + capResidenza + '\'' +
                ']';
    }
}
