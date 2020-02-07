package Packages;

import java.io.Serializable;

public class contoCorrente implements Serializable {

    /*
    Intestatari
     */
    private String nome1;
    private String cognome1;

    private String nome2;
    private String cognome2;

    private String nome3;
    private String cognome3;

    /*
    Carta d'identità
     */
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



    public contoCorrente() {

    }
}
