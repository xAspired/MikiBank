package Packages;

public class infoCliente {
    String nome;
    String cognome;
    int dataDiNascita;
    String codiceFiscale;

    //Inserire il Documento di Residenza

    public infoCliente() {

    }

    public infoCliente(String nome, String cognome, int dataDiNascita, String codiceFiscale){
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome () {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setDataDiNascita(int dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public int getDataDiNascita() {
        return dataDiNascita;
    }

    @Override
    public String toString() {
        return "Cliente [" +
                "Nome:'" + nome + '\'' +
                ", Cognome='" + cognome + '\'' +
                ", Data di Nascita=" + dataDiNascita +
                ", Codice Fiscale='" + codiceFiscale + '\'' +
                ']';
    }
}
