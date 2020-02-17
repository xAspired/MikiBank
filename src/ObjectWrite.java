import Packages.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static Packages.metodiVerifiche.*;

/*
    Main Class
 */
public class ObjectWrite {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        ArrayList<contoCorrente> contiCorrentiArray;
        File file = new File("contiCorrenti");
        if (file.exists()) {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream fileObjIn = new ObjectInputStream(fileIn);

            //noinspection unchecked
            contiCorrentiArray = (ArrayList<contoCorrente>) fileObjIn.readObject();

            fileObjIn.close();
            fileIn.close();
        }
        else {
            contiCorrentiArray = new ArrayList<>();
        }

        System.out.println("Dimensione dell'ArrayList: " + contiCorrentiArray.size());

        //contoCorrente c1 = new contoCorrente("IDNALFO", 14, 1);

        //contiCorrentiArray.add(c1);

        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream fileObj = new ObjectOutputStream(fileOut);

        fileObj.writeObject(contiCorrentiArray);
        fileObj.close();
        fileOut.close();

        //Fine Serializzazione e Deserializzazione

        int scelta = 0;
        boolean whileStatement = true;


        while (whileStatement) {
            System.out.println("MIKIBANK - Oltre le Taglie");
            System.out.println("[0] Uscita dal programma");
            System.out.println("[1] Creazione di un conto corrente");
            System.out.println("[2] Visualizzazione informazione conto corrente");
            System.out.println("[3] Visualizzazione utente");
            System.out.println("[4] Chiedere un prestito");
            System.out.print("➡ ");

            try {
                scelta = Integer.parseInt(input.nextLine());
                whileStatement = false;
            }
            catch (Exception ignored) {
            }

        }

        /*
            Switch - Scelta Azione
         */
        switch (scelta) {
            case 0:
                //Il Garbage Collector pulisce
                System.out.print("Torni a Trovarci");
                System.gc();
                System.exit(0);
                break;
            case 1:
                //Creazione Conto Corrente

                contiCorrentiArray.add(creaConto());
                break;
            case 2:
                //Visualizzare Info Conto Corrente
                visualizzaInfoConto(contiCorrentiArray);
                break;
            case 3:
                //Visualizza Info Utente
                visualizzaInfoUtente(contiCorrentiArray);
                break;
            case 4:
                //Chiedere un prestito
                chiederePresito(contiCorrentiArray);
                break;


            default:
                System.out.print("Azione non valida");
                break;

        }
    }

    private static void chiederePresito(ArrayList<contoCorrente> contiCorrentiArray) {
    }

    private static void visualizzaInfoUtente(ArrayList<contoCorrente> contiCorrentiArray) {
    }

    private static void visualizzaInfoConto(ArrayList<contoCorrente> contiCorrentiArray) {
    }

    private static contoCorrente creaConto() {
        String dataContabile;
        String dataValuta;
        float importo;

        int intestatari = 0;
        System.out.println("inserisci il numero di intestatari 1-3");
        intestatari = input.nextInt();

        //infoCliente cliente = new infoCliente;

        infoCliente[] cointestatari = new infoCliente[intestatari];
        for (int i = 0; i < intestatari; i++) {

            //informazioni cliente
            String nome = "";


            String cognome = "";
            String cartaID = ""; //ID riconoscitivo carta d'identità (CA00000AA) Numero Unico Nazionale
            String cartaScadenza = ""; //Scadenza carta d'identità
            String dataDiNascita = "";
            String sesso = "";
            String codiceFiscale = "";

            String cittadinanza = "";
            String indirizzoResidenza = "";
            int numeroCivico = 0;
            String comuneResidenza = "";
            String statoResidenza = "";
            String capResidenza = "";
            infoCliente cliente = new infoCliente(nome, cognome, cartaID, cartaScadenza, dataDiNascita, sesso, codiceFiscale, cittadinanza, indirizzoResidenza, numeroCivico, comuneResidenza, statoResidenza, capResidenza);
            cointestatari[i] = cliente;
        }
        //informazioni conto
        String IBAN = verificaIBAN();
        float saldo = verificaSaldo();
        float interesse = verificaInteresse();
        String tipoConto = verificaTipoConto();

        contoCorrente conto;
        conto = new contoCorrente(IBAN, saldo, interesse, cointestatari, tipoConto);

        return conto;
    }

}

