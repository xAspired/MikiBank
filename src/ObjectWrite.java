import Packages.contoCorrente;
import Packages.infoCliente;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static Packages.metodiVerifiche.*;

/*
    Main Class
 */
public class ObjectWrite extends JPasswordField {
    static Scanner input = new Scanner(System.in);
    static contoCorrente verCodice = new contoCorrente();


    public static void main(String[] args) throws Exception {

        //Si inizializza un ArrayList chiamato contiCorrentiArray
        ArrayList<contoCorrente> contiCorrentiArray;
        File file = new File("contiCorrenti");

        //Si verifica se il file esiste già, quindi si dovranno solo leggere
        //i valori dell'ArrayList già scritti sul file stesso
        if (file.exists()) {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream fileObjIn = new ObjectInputStream(fileIn);

            //noinspection unchecked
            contiCorrentiArray = (ArrayList<contoCorrente>) fileObjIn.readObject();


            fileObjIn.close();
            fileIn.close();
        }

        //Altrimenti si crea un ArrayList vuoto
        else
            contiCorrentiArray = new ArrayList<>();

        System.out.println("Numero Conti Presenti: " + contiCorrentiArray.size() + "\n");

        //Fine Serializzazione e Deserializzazione


        int scelta;

        //Chiamata al login
        boolean amministratore = login();
        scelta = 1;
        menu:
        do {
            boolean whileStatement = true;
            while (whileStatement) {
                System.out.println("Numero Conti Presenti: " + contiCorrentiArray.size() + "\n");

                System.out.println("\t\tMIKIBANK");
                System.out.println("[0] Uscita dal programma");
                System.out.println("[1] Creazione di un conto corrente");
                System.out.println("[2] Visualizzazione informazione conto corrente");

                if(amministratore)
                    System.out.println("[3] Visualizzazione utente - aggiornare dati");
                else
                    System.out.println("[3] Visualizzazione utente");

                System.out.println("[4] Chiedere un prestito");

                if(amministratore)
                    System.out.println("[5] Chiusura conti");

                System.out.print("➡ ");

                try {
                    scelta = Integer.parseInt(input.nextLine());
                    whileStatement = false;
                }
                catch (Exception ignored) {}

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
                    contoCorrente verificaConto = creaConto(contiCorrentiArray);

                    //Se il conto è vuoto, si richiama di nuovo il menù
                    if(verificaConto==null)
                        continue menu;

                    //Creazione Conto Corrente
                    contiCorrentiArray.add(verificaConto);

                    //Serializzazione
                    FileOutputStream fileOut = new FileOutputStream(file);
                    ObjectOutputStream fileObj = new ObjectOutputStream(fileOut);

                    fileObj.writeObject(contiCorrentiArray);
                    fileObj.close();
                    fileOut.close();
                    //Fine Serializzazione

                    break;
                case 2:
                    //Visualizzare Info Conto Corrente
                    visualizzaInfoConto(contiCorrentiArray);

                    //Serializzazione
                    fileOut = new FileOutputStream(file);
                    fileObj = new ObjectOutputStream(fileOut);

                    fileObj.writeObject(contiCorrentiArray);
                    fileObj.close();
                    fileOut.close();
                    //Fine Serializzazione
                    break;
                case 3:
                    //Visualizza Info Utente
                    visualizzaInfoUtente(contiCorrentiArray);

                    //Serializzazione
                    fileOut = new FileOutputStream(file);
                    fileObj = new ObjectOutputStream(fileOut);

                    fileObj.writeObject(contiCorrentiArray);
                    fileObj.close();
                    fileOut.close();
                    //Fine Serializzazione
                    break;
                case 4:
                    //Chiedere un prestito
                    chiederePresito();
                    break;

                case 5:
                    if(amministratore)
                    //Chiudere un conto
                        chiudereConto(contiCorrentiArray);
                    else
                        System.out.print("Azione non valida");

                    break;


                default:
                    System.out.print("Azione non valida");
                    break;

            }
        } while (true);
    }

    /*
     * =========================================================
     * Chiedere Prestiti
     * =========================================================
     */
    private static void chiederePresito() {
    }

    /*
     * =========================================================
     * Visualizza Info Utente
     * =========================================================
     */
    private static void visualizzaInfoUtente(ArrayList<contoCorrente> contiCorrentiArray) {
        int[] numbers = IntStream.rangeClosed(0, contiCorrentiArray.size() - 1).toArray();

        //Informazioni Cliente
        System.out.println("\n- Informazioni Cliente: - ");
        System.out.println("Inserisca il codice fiscale:");

        String codiceFiscale = input.nextLine();
        codiceFiscale=codiceFiscale.toUpperCase();
        String nome = "";
        String cognome = "";

        //StringBuilder che conterrà le informazioni del Cliente
        StringBuilder totaleConti = new StringBuilder();

        try {
            for(int i : numbers) {
                //Si crea un oggetto temporaneo per ogni indice [i]
                contoCorrente verCodice = contiCorrentiArray.get(i);
                infoCliente[] verCodiceCliente = verCodice.getCointestatari();

                //For che si ripete per il numero di cointestatari presenti in un conto
                //noinspection ForLoopReplaceableByForEach
                for(int h = 0; h<verCodiceCliente.length; h++) {

                    //Si verifica che il primo intestatario sia presente
                    //Successivamente si mettono a confronto i codici fiscali
                    if (verCodiceCliente[h]!=null && verCodiceCliente[h].getCodiceFiscale().equals(codiceFiscale)) {
                        nome = verCodiceCliente[h].getNome();
                        cognome = verCodiceCliente[h].getCognome();

                        //Si vanno ad aggiungere allo StringBuilder i vari valori
                        totaleConti.append("\n|| IBAN: ").append(verCodice.getIBAN()).append("    ").append("Tipo Conto: ").append(verCodice.getTipoConto()).append("    ").append("Stato Conto: ").append(verCodice.getStatoConto()).append("\n");

                    }
                }
            }

            //Si verifica che il StringBuilder non abbia lunghezza 0 e che quindi non sia vuoto
            if(totaleConti.length() != 0) {
                System.out.println("\n\n|| Attualmente " + nome + " " + cognome + " possiede i seguenti conti: \n||" + totaleConti);
                System.out.print("Sta per essere reindirizzato al menù");
                TimeUnit.SECONDS.sleep(1);
                System.out.print(".");
                TimeUnit.SECONDS.sleep(1);
                System.out.print(".");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(".\n");
            }

            else {
                System.out.print("\n\n|| Il codice fiscale " + codiceFiscale + " non è associato alcun conto. ");
                int scelta;
                boolean exitMethods = true;
                while (exitMethods) {

                    System.out.print("Vuole creare un nuovo conto\n|| [1] SI \n|| [2] NO\n||\n|| ➡ ");
                    try {
                        scelta = Integer.parseInt(input.nextLine());

                        if(scelta==1) {
                            System.out.println("Per creare un conto ritorni al menù");
                            exitMethods = false;
                        }
                        else if(scelta==2)
                            exitMethods = false;

                        else
                            System.out.println("-valore non valido!-");

                    }
                    catch (Exception e) {
                        System.out.println("-valore non valido!-");
                    }
                }
            }
        }
        catch (Exception ignored) {}
    }

    /*
     * =========================================================
     * Visualizza Info Conto
     * =========================================================
     */
    private static void visualizzaInfoConto(ArrayList<contoCorrente> contiCorrentiArray) throws InterruptedException {

        //Variabile che prende il valore da 0 fino alla dimensione di contiCorrentiArray - 1
        int[] numbers = IntStream.rangeClosed(0, contiCorrentiArray.size() - 1).toArray();
        StringBuilder resocontoConto = new StringBuilder();

        System.out.println("\n- Informazioni Conto: -");
        System.out.print("Inserisca l'IBAN: ");

        String iban = input.nextLine();

        //Creazione di un conto vuoto
        contoCorrente verCodiceTemp;
        int posizione = 0;
        for (int i : numbers) {

            //verCodice assume il valore di un contoCorrente per ogni indice [i]
            verCodiceTemp = contiCorrentiArray.get(i);

            //Si crea un array di cointestatari, che prende il valore dei cointestatari di un conto per ogni indice [i]
            infoCliente[] verCodiceCliente = verCodiceTemp.getCointestatari();

            //Dopo aver verificato che l'IBAN non sia vuoto, si verifica che l'IBAN appena inserito
            //corrisponda con quello del cointestatario in questione
            if(verCodiceTemp.getIBAN()!=null && verCodiceTemp.getIBAN().equalsIgnoreCase(iban)) {
                verCodice = verCodiceTemp;

                if(verCodice.getTipoConto().equals("Conto Corrente a Canone Fisso") || verCodice.getTipoConto().equals("Conto Corrente Senza Canone")) {
                    if(verCodice.getListaFantasma()!=null) {
                        for(int k=0; k<verCodice.listaFantasma.size()-1; k++){
                            //DOPO 48 ORE
                            if(verCodice.listaFantasma.get(k)!=null && verCodice.listaFantasma.get(k).getTipoOperazione().equals("Bonifico Uscita")){
                                //Data scelta dal cliente sommata alle 48 ore
                                LocalDate dataFinale = stringToLocalDate(verCodice.listaFantasma.get(k).getDataFine());

                                //Se la data scelta è passata vengono caricati i soldi
                                if(dataFinale.isBefore(LocalDate.now()) || dataFinale.isEqual(LocalDate.now())){
                                    verCodice.setSaldoContabile(-verCodice.listaFantasma.get(k).getImportoContabile());
                                    verCodice.listaFantasma.set(k, null);
                                }
                            }
                        }
                    }


                }

                    //Verifica i depositi ormai fruttati
                for(int b = verCodice.listaMovimenti.size() - 1; b>=0; b--){
                    //Se il movimento in considerazione appartiene alla lista di movimenti che hanno fruttato
                    if(verCodice.listaMovimenti.get(b)!=null && verCodice.listaMovimenti.get(b).getDescrizioneOperazione().charAt(0)=='#'){
                        LocalDate time = LocalDate.now();
                        String tempo = "";

                        //Si genera una data "corretta" rispetto al resto delle dae salvate
                        if(time.getDayOfMonth() < 10)
                            tempo += "0" + time.getDayOfMonth();
                        else
                            tempo += time.getDayOfMonth();

                        tempo += "/";

                        if(time.getMonthValue() < 10)
                            tempo += "0" + time.getMonthValue();
                        else
                            tempo += time.getMonthValue();

                        tempo += "/" + time.getYear();


                        //interesse
                        String temp = verCodice.listaMovimenti.get(b).getDescrizioneOperazione();
                        temp=temp.substring(1);
                        //Calcolo numero giorni
                        long noOfDaysBetween = Integer.parseInt(temp);

                        if(verCodice.listaMovimenti.get(b).getDataContabile().equals(tempo) && verCodice.listaMovimenti.get(b).getImportoContabile()!=0){
                            //importo DAVVERO totale
                            float importoTotale = (float) (verCodice.listaMovimenti.get(b).getImportoContabile() + (verCodice.listaMovimenti.get(b).getImportoContabile() * 1.48 * noOfDaysBetween) / 36500);
                            verCodice.listaMovimenti.get(b).setImportoContabile(importoTotale);
                            verCodice.setSaldoDisponibile(importoTotale);
                            System.out.println("Sono stati accreditati " + verCodice.listaMovimenti.get(b).getImportoContabile() + " sul suo conto");
                            //Azzerato il movimento così da eliminarlo
                            verCodice.listaMovimenti.get(b).setImportoContabile(0);

                            //Si genera un oggetto lista movimento così da assegnarli tutti i parametri generati
                            contoCorrente.listaMovimenti movimentoBanca = new contoCorrente.listaMovimenti();
                            String dataContabile = verCodice.listaMovimenti.get(b).getDataContabile();
                            String dataValuta = verCodice.listaMovimenti.get(b).getDataDisponibile();
                            float importo = verCodice.listaMovimenti.get(b).getImportoContabile();
                            String causale = "Fruttato " + verCodice.listaMovimenti.get(b).getDescrizioneOperazione();
                            movimentoBanca.setDataContabile(dataContabile);
                            movimentoBanca.setDataDisponibile(dataValuta);
                            movimentoBanca.setImportoContabile(importo);
                            movimentoBanca.setImportoDisponibile(importo);
                            movimentoBanca.setDescrizioneOperazione(causale);
                            //La lista movimenti viene aggiunta all' array
                            verCodice.listaMovimenti.add(movimentoBanca);
                            contiCorrentiArray.set(i, verCodice);

                        }

                    }

                    if(verCodice.getTipoConto().equals("Conto Corrente a Canone Fisso")){
                        //data apertura conto, data+anno
                        LocalDate ultimaData = verCodice.getDataUltimoPagamento();
                        LocalDate dataAttuale = LocalDate.now();
                        long DaysBetween = ChronoUnit.DAYS.between(ultimaData, dataAttuale);

                        //Si paga una somma di denaro corrispondente al tempo pattuito (365 giorni)
                        if(DaysBetween==365){

                            ArrayList<contoCorrente.listaMovimenti> listaMovimentiTemp = verCodice.getListaMovimenti();
                            contoCorrente.listaMovimenti oggettoMovimenti = new contoCorrente.listaMovimenti();

                            if(verCodice.getSaldoDisponibile()>=60) {
                                listaMovimentiTemp.add(oggettoMovimenti);

                                //date in forma corretta
                                String tempo = "";

                                //Si genera una data "corretta" rispetto al resto delle dae salvate
                                if(dataAttuale.getDayOfMonth() < 10)
                                    tempo += "0" + dataAttuale.getDayOfMonth();
                                else
                                    tempo += dataAttuale.getDayOfMonth();

                                tempo += "/";

                                if(dataAttuale.getMonthValue() < 10)
                                    tempo += "0" + dataAttuale.getMonthValue();
                                else
                                    tempo += dataAttuale.getMonthValue();

                                tempo += "/" + dataAttuale.getYear();

                                //Si genera un oggetto lista movimento così da assegnarli tutti i parametri generati
                                String causale = "Tax Annuale";
                                oggettoMovimenti.setDataContabile(tempo);
                                oggettoMovimenti.setDataDisponibile(tempo);
                                oggettoMovimenti.setImportoContabile(-60);
                                oggettoMovimenti.setImportoDisponibile(-60);
                                oggettoMovimenti.setDescrizioneOperazione(causale);
                                //La lista movimenti viene aggiunta all' array
                                verCodice.listaMovimenti.add(oggettoMovimenti);
                                contiCorrentiArray.set(i, verCodice);

                                verCodice.setSaldoDisponibile(-60);
                                verCodice.setSaldoContabile(-60);
                                System.out.println("\nÈ stata prelevata la tassa annuale [60€]");
                                verCodice.listaMovimenti = listaMovimentiTemp;
                                contiCorrentiArray.set(i, verCodice);

                            }
                            else {
                                verCodice.setStatoConto("SOSPESO");
                                System.out.println("Il suo account è stato temporaneamente sospeso.\nPer ulteriori informazioni si rivolga al nostro  ufficio clientela.\n");
                            }
                        }
                    }


                }

                posizione=i;
                for (int h = 0; h < verCodiceCliente.length; h++) {
                    resocontoConto.append("\n|| Cointestatario ").append(h + 1).append(": ");

                    if(verCodiceCliente[h].getSesso().equalsIgnoreCase("F"))
                        resocontoConto.append("\uD83D\uDE4E\uD83C\uDFFC\u200D♀ ");
                    else
                        resocontoConto.append("\uD83D\uDE4E\uD83C\uDFFD\u200D♂ ");

                    resocontoConto.append(verCodiceCliente[h].getNome()).append(verCodiceCliente[h].getCognome()).append("    [").append(verCodiceCliente[h].getDataDiNascita()).append("]");
                }
                resocontoConto.append("\n||\n|| Valuta: ").append(verCodice.getSaldoDisponibile()).append("€    ").append("Saldo contabile: ").append(verCodice.getSaldoContabile()).append("€    \n||\n|| Tipo Conto: ").append(verCodice.getTipoConto());
            }

        }

        /*
         * =========================================================
         * Visualizza Info Conto -> Prelievo/Deposito
         * =========================================================
         */

        //Se a resocontoConto sono già stati assegnati dei valori

        if(resocontoConto.length() != 0) {

            System.out.println("\n===================================================================\n                    " + iban + "\n===================================================================\n" + resocontoConto);
            System.out.println("\n===================================================================\n                         " + "Lista Movimenti" + "\n===================================================================\n");
            verCodice.toStringListaMovimenti();

            if(verCodice.getStatoConto().equals("CHIUSO")) {
                System.out.print("\n\n===================================================================\n");
                System.out.print("\t\tCi dispiace ma il conto sembrerebbe essere chiuso.");
                System.out.println("\n===================================================================\n");
            }
            else if(verCodice.getStatoConto().equals("SOSPESO")) {
                System.out.print("\n\n===================================================================\n");
                System.out.print("\t\tCi dispiace ma il conto sembrerebbe essere sospeso.");
                System.out.println("\n===================================================================\n");
                System.out.println("\nA quanto pare le tasse annuali non sono state pagate. Vuole pagarle?\n[1] Si\n[2] No");

                int scelta;
                boolean exitMethods = true;
                while (exitMethods) {
                    System.out.print(" ➡ ");
                    try {
                        scelta = Integer.parseInt(input.nextLine());

                        if(scelta==1) {
                            for (int l = 0; l <= 1; l++) {
                                ArrayList<contoCorrente.listaMovimenti> listaMovimentiTemp = verCodice.getListaMovimenti();
                                contoCorrente.listaMovimenti oggettoMovimenti = new contoCorrente.listaMovimenti();
                                exitMethods = false;

                                listaMovimentiTemp.add(oggettoMovimenti);

                                //date in forma corretta
                                String tempo = "";
                                LocalDate dataAttuale = LocalDate.now();

                                //Si genera una data "corretta" rispetto al resto delle dae salvate
                                if (dataAttuale.getDayOfMonth() < 10)
                                    tempo += "0" + dataAttuale.getDayOfMonth();
                                else
                                    tempo += dataAttuale.getDayOfMonth();

                                tempo += "/";

                                if (dataAttuale.getMonthValue() < 10)
                                    tempo += "0" + dataAttuale.getMonthValue();
                                else
                                    tempo += dataAttuale.getMonthValue();

                                tempo += "/" + dataAttuale.getYear();

                                //Si genera un oggetto lista movimento così da assegnarli tutti i parametri generati
                                String causale = "Tax Annuale";
                                oggettoMovimenti.setDataContabile(tempo);
                                oggettoMovimenti.setDataDisponibile(tempo);
                                if(l==0) {
                                    oggettoMovimenti.setImportoContabile(60);
                                    oggettoMovimenti.setImportoDisponibile(60);
                                }
                                else{
                                    oggettoMovimenti.setImportoContabile(-60);
                                    oggettoMovimenti.setImportoDisponibile(-60);
                                }
                                oggettoMovimenti.setDescrizioneOperazione(causale);
                                //La lista movimenti viene aggiunta all' array
                                verCodice.listaMovimenti = listaMovimentiTemp;
                                contiCorrentiArray.set(posizione, verCodice);
                            }
                            verCodice.setStatoConto("APERTO");
                            System.out.println("Il suo conto è stato ripristinato correttamente.\n");
                        }
                        else if(scelta==2) {
                            exitMethods = false;
                        }
                        else
                            System.out.println("-valore non valido!-");
                    } catch (Exception e) {
                        System.out.println("-valore non valido!-");
                    }
                }
            }
            else {
                System.out.println("\n\n** Cosa vuole fare? **");

                if(verCodice.getTipoConto().equals("Conto Corrente senza Canone") || verCodice.getTipoConto().equals("Conto Corrente a Canone Fisso"))
                    System.out.println("\n[1] Depositare\n[2] Prelevare\n[3] Bonifico Bancario\n[4] Tornare al menù");

                else if (verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato"))
                    System.out.println("\n[1] Deposito Cauzionale\n[2] Prelievo Cauzionale\n[3] Tornare al menù");


                boolean exitMethods = true;
                int scelta = 0;

                while (exitMethods) {
                    try {
                        System.out.print(" ➡ ");
                        scelta = Integer.parseInt(input.nextLine());
                        if (!(scelta != 1 && scelta != 2 && scelta != 3 && scelta != 4) && !(verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato")))
                            exitMethods = false;
                        else if (!(scelta != 1 && scelta != 2 && scelta != 3) && (verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato")))
                            exitMethods = false;
                        else
                            System.out.println("Valore non valido. È pregato/a di reinserirlo.");
                    } catch (Exception e) {
                        System.out.println("Valore non valido. È pregato/a di reinserirlo.");
                    }
                }

                /*
                 * =========================================================
                 * Deposita (normale)
                 * =========================================================
                 */
                if (scelta == 1 && (verCodice.getTipoConto().equals("Conto Corrente a Canone Fisso") || verCodice.getTipoConto().equals("Conto Corrente senza Canone"))) {
                    //Si genera l'importo da depositare
                    float importo = deposita();
                    //Si genera una descrizione per la lista movimenti
                    String descrizione = aggiuntaDescrizione();
                    if (verCodice.getTipoConto().equals("Conto Corrente senza Canone")) {
                        importo = importo - 2;
                        System.out.println("Ha appena depositato " + importo + "€ con causale: " + descrizione + " [+2€ Tax]");
                    } else
                        System.out.println("Ha appena depositato " + importo + "€ con causale: " + descrizione);
                    verCodice.setSaldoContabile(importo);
                    verCodice.setSaldoDisponibile(importo);
                    ArrayList<contoCorrente.listaMovimenti> listaMovimentiTemp = verCodice.getListaMovimenti();
                    contoCorrente.listaMovimenti oggettoMovimenti = new contoCorrente.listaMovimenti();


                    oggettoMovimenti.setImportoDisponibile(importo);
                    LocalDateTime time = LocalDateTime.now();
                    String tempo = "";

                /*
                   Si gestisce il caso in cui il numero sia unico e quindi non vada
                   a rovinare la tabulazione della Lista Movimenti
                 */
                    if (time.getDayOfMonth() < 10)
                        tempo += "0" + time.getDayOfMonth();
                    else
                        tempo += time.getDayOfMonth();

                    tempo += "/";

                    if (time.getMonthValue() < 10)
                        tempo += "0" + time.getMonthValue();
                    else
                        tempo += time.getMonthValue();

                    tempo += "/" + time.getYear();

                    oggettoMovimenti.setDataDisponibile(tempo);
                    oggettoMovimenti.setImportoContabile(importo);
                    oggettoMovimenti.setDataContabile(tempo);
                    oggettoMovimenti.setDescrizioneOperazione(descrizione);
                    oggettoMovimenti.setDate(LocalDate.now());

                    listaMovimentiTemp.add(oggettoMovimenti);
                    verCodice.listaMovimenti = listaMovimentiTemp;
                    contiCorrentiArray.set(posizione, verCodice);

                    System.out.print("\n\nSta per essere reindirizzato al menù");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(".\n");
                }



                if (scelta == 1 && ((verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato")))) {
                    float importo = deposita();

                    exitMethods = true;
                    int periodoFrutto = 0;
                    while (exitMethods) {
                        try {
                            System.out.print("Quanti mesi si intende fruttare tale importo? (3/6/9/12/60): ");
                            periodoFrutto = Integer.parseInt(input.nextLine());
                            if (!(periodoFrutto != 3 && periodoFrutto != 6 && periodoFrutto != 9 && periodoFrutto != 12 && periodoFrutto != 60))
                                exitMethods = false;
                            else
                                System.out.println("Inserire valore valido");
                        } catch (Exception e) {
                            System.out.println("Inserire valore valido");
                        }
                    }

                    verCodice.setSaldoContabile(importo);
                    ArrayList<contoCorrente.listaMovimenti> listaMovimentiTemp = verCodice.getListaMovimenti();

                    contoCorrente.listaMovimenti oggettoMovimenti = new contoCorrente.listaMovimenti();
                    oggettoMovimenti.setImportoDisponibile(importo);

                    LocalDateTime time = LocalDateTime.now();
                    //calcolo data contabile
                    String tempo = "";
                /*
                    Si gestisce il caso in cui il numero sia unico e quindi non vada
                    a rovinare la tabulazione della Lista Movimenti
                 */
                    if (time.getDayOfMonth() < 10)
                        tempo += "0" + time.getDayOfMonth();
                    else
                        tempo += time.getDayOfMonth();

                    tempo += "/";

                    if (time.getMonthValue() < 10)
                        tempo += "0" + time.getMonthValue();
                    else
                        tempo += time.getMonthValue();

                    tempo += "/" + time.getYear();


                    //calcolo data disponibile
                    LocalDate dateBefore = LocalDate.now();
                    LocalDate dateAfter = LocalDate.now().plusMonths(periodoFrutto);
                    long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

                    String tempoDisponibile = "";
                    if (dateAfter.getDayOfMonth() < 10)
                        tempoDisponibile += "0" + dateAfter.getDayOfMonth();
                    else
                        tempoDisponibile += dateAfter.getDayOfMonth();

                    tempoDisponibile += "/";

                    if (dateAfter.getMonthValue() < 10)
                        tempoDisponibile += "0" + dateAfter.getMonthValue();
                    else
                        tempoDisponibile += dateAfter.getMonthValue();

                    tempoDisponibile += "/" + dateAfter.getYear();

                    String descrizione = "#";
                    if (noOfDaysBetween > 9 && noOfDaysBetween < 100) {
                        descrizione += "000";
                    } else if (noOfDaysBetween >= 100 && noOfDaysBetween < 1000) {
                        descrizione += "00";
                    } else
                        descrizione += "0";

                    descrizione += noOfDaysBetween;

                    descrizione += Integer.parseInt(String.valueOf(verCodice.getListaMovimenti().size()));

                    System.out.println("Ha appena depositato " + importo + "€ con codice: " + descrizione);

                    //calcolo importo disponibile
                    float importoFruttato = ((float) ((importo * 1.48 * noOfDaysBetween) / 36500));
                    System.out.println("Se attenderà " + noOfDaysBetween + " giorni, i suoi interessi corrisponderanno a €" + Math.round((importoFruttato) * 100) / 100.00 + ".\nPer un totale di €" + Math.round((importo + importoFruttato) * 100) / 100.00);


                    oggettoMovimenti.setDataDisponibile(tempo);
                    oggettoMovimenti.setImportoContabile(importo);
                    oggettoMovimenti.setDataContabile(tempoDisponibile);
                    oggettoMovimenti.setDescrizioneOperazione(descrizione);
                    oggettoMovimenti.setDate(LocalDate.now());

                    listaMovimentiTemp.add(oggettoMovimenti);
                    verCodice.listaMovimenti = listaMovimentiTemp;
                    contiCorrentiArray.set(posizione, verCodice);

                    System.out.print("\n\nSta per essere reindirizzato al menù");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(".\n");
                }

                /*
                 * =========================================================
                 * Preleva (normale e non)
                 * =========================================================
                 */

                if (scelta == 2) {
                    ArrayList<contoCorrente.listaMovimenti> listaMovimentiTemp = verCodice.getListaMovimenti();
                    float importo = preleva(verCodice);
                    if (importo != 0) {
                        String descrizione = aggiuntaDescrizione();

                        //Si verifica che la somma prelevabile rientri nel saldo complessivo
                        if (importo != 0) {
                            switch (verCodice.getTipoConto()) {
                                case "Conto Deposito Vincolato":
                                    System.out.println("Ha appena prelevato " + importo + "€ con causale: " + descrizione + " (annesso di interessi)");
                                    break;
                                case "Conto Deposito non Vincolato":
                                    System.out.println("Ha appena prelevato " + importo + "€ con causale: " + descrizione + " (non annesso di interessi)");
                                    break;
                                case "Conto Corrente senza Canone":
                                    System.out.println("Ha appena prelevato " + importo + "€ con causale: " + descrizione + " [+2€ Tax]");
                                    break;
                            }

                            verCodice.setSaldoContabile(importo);

                            //if (verCodice.getMovimentoAttuale()<= 9) {
                            contoCorrente.listaMovimenti oggettoMovimenti = new contoCorrente.listaMovimenti();
                            oggettoMovimenti.setImportoDisponibile(importo);
                            LocalDateTime time = LocalDateTime.now();
                            String tempo = "";

                        /*
                            Si gestisce il caso in cui il numero sia unico e quindi non vada
                            a rovinare la tabulazione della Lista Movimenti
                         */
                            if (time.getDayOfMonth() < 10)
                                tempo += "0" + time.getDayOfMonth();
                            else
                                tempo += time.getDayOfMonth();

                            tempo += "/";

                            if (time.getMonthValue() < 10)
                                tempo += "0" + time.getMonthValue();
                            else
                                tempo += time.getMonthValue();

                            tempo += "/" + time.getYear();

                            oggettoMovimenti.setDataDisponibile(tempo);
                            oggettoMovimenti.setImportoContabile(importo);
                            oggettoMovimenti.setDataContabile(tempo);
                            oggettoMovimenti.setDescrizioneOperazione(descrizione);
                            oggettoMovimenti.setDate(LocalDate.now());

                            listaMovimentiTemp.add(oggettoMovimenti);
                            verCodice.listaMovimenti = listaMovimentiTemp;
                            contiCorrentiArray.set(posizione, verCodice);

                            System.out.print("\n\nSta per essere reindirizzato al menù");
                            TimeUnit.SECONDS.sleep(1);
                            System.out.print(".");
                            TimeUnit.SECONDS.sleep(1);
                            System.out.print(".");
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println(".\n");
                        }
                    }
                }

                if (scelta == 3 && (verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato"))) {
                    System.out.print("\n\nSta per essere reindirizzato al menù");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(".\n");
                }

                if (scelta == 3 && (verCodice.getTipoConto().equals("Conto Corrente senza Canone") || verCodice.getTipoConto().equals("Conto Corrente a Canone Fisso"))) {
                    System.out.print("Iban Beneficiario: ");
                    String ibanBeneficiario = input.nextLine();
                    float denaro = 0;
                    int numeroConteggi = 0;
                    for (int i : numbers) {
                        //verCodice assume il valore di un contoCorrente per ogni indice [i]
                        verCodiceTemp = contiCorrentiArray.get(i);

                        //Dopo aver verificato che l'IBAN non sia vuoto, si verifica che l'IBAN appena inserito
                        //corrisponda con quello del cointestatario in questione
                        if (verCodiceTemp.getIBAN() != null && verCodiceTemp.getIBAN().equalsIgnoreCase(ibanBeneficiario)) {
                            posizione = i;
                            numeroConteggi++;
                            exitMethods = true;
                            while(exitMethods) {
                                System.out.print("\nInserisca la cifra da inviare: €");
                                try {
                                    denaro = Integer.parseInt(input.nextLine());
                                    exitMethods = false;
                                }
                                catch (Exception e) {
                                    System.out.print("Errore! Il valore da depositare non può essere uguale a 0€");
                                }
                            }

                            String causale = aggiuntaDescrizione();

                            String dataEsecuzione = verificaData("Data esecuzione");
                            LocalDate dataReale = stringToLocalDate(dataEsecuzione);
                            //LocalDate dataRealeFinale = dataReale.plusDays(2);
                            //noinspection UnnecessaryLocalVariable
                            LocalDate dataRealeFinale = dataReale;
                            String dataFinale = localDateToString(dataRealeFinale);

                            System.out.println("Considerando le 48 ore di elaborazione, il ricevente riceverà il denaro in data " + dataFinale);

                            if(LocalDate.now().equals(dataReale)) {
                                if(verCodice.getSaldoDisponibile()>denaro) {
                                    verCodice.setSaldoDisponibile(-denaro);
                                    System.out.println("Il denaro dal suo conto è stato detratto");

                                    ArrayList<contoCorrente.listaMovimenti> listaMovimentiTemp = verCodice.getListaMovimenti();
                                    contoCorrente.listaMovimenti oggettoMovimenti = new contoCorrente.listaMovimenti();

                                    //listaMovimentiTemp.add(oggettoMovimenti);

                                    //Si genera un oggetto lista movimento così da assegnarli tutti i parametri generati
                                    oggettoMovimenti.setDataContabile(dataFinale);
                                    oggettoMovimenti.setDataDisponibile(dataEsecuzione);
                                    //RICORDATI DI FARE LA VEIRIFCA DOPO 48 ORE SULL'IMPORTO CONTABILE (Lista Fantasma)
                                    oggettoMovimenti.setImportoDisponibile(-denaro);
                                    oggettoMovimenti.setDescrizioneOperazione(causale);
                                    //La lista movimenti viene aggiunta all' array
                                    verCodice.listaMovimenti.add(oggettoMovimenti);
                                    contiCorrentiArray.set(posizione, verCodice);

                                    //GESTIONE DELLA LISTA FANTASMA
                                    ArrayList<contoCorrente.listaFantasma> listaFantasmaTemp = verCodice.getListaFantasma();
                                    contoCorrente.listaFantasma oggettoFantasmi = new contoCorrente.listaFantasma();

                                    listaFantasmaTemp.add(oggettoFantasmi);

                                    //Si genera un oggetto lista movimento così da assegnarli tutti i parametri generati
                                    oggettoFantasmi.setDataFine(dataFinale);
                                    //RICORDATI DI FARE LA VEIRIFCA DOPO 48 ORE SULL'IMPORTO CONTABILE (Lista Fantasma)
                                    oggettoFantasmi.setImportoContabile(denaro);
                                    //Si associa alla descrizione dell'operazione l'iabn del destinatario
                                    oggettoFantasmi.setDescrizioneOperazione(ibanBeneficiario);
                                    oggettoFantasmi.setTipoOperazione("Bonifico Uscita");
                                    //La lista movimenti viene aggiunta all' array
                                    verCodice.listaFantasma.add(oggettoFantasmi);
                                    contiCorrentiArray.set(posizione, verCodice);

                                }
                                else
                                    System.out.println("Il denaro presente sul suo conto non è sufficiente");

                            }
                        }

                    }
                    if(numeroConteggi != 1)
                        System.out.print("\n\n|| L'IBAN " + iban + " non è stato trovato.\n");
                }

                if(scelta == 4 && (verCodice.getTipoConto().equals("Conto Corrente senza Canone") || verCodice.getTipoConto().equals("Conto Corrente a Canone Fisso"))) {
                    System.out.print("\n\nSta per essere reindirizzato al menù");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(".\n");
                }
            }
        }
        else {
            System.out.print("\n\n|| L'IBAN " + iban + " non è stato trovato. Cosa desidera fare? \n||\n|| [1] Creare un nuovo conto\n|| [2] Tornare al menù\n||\n|| ➡ ");
            try {
                if (input.nextLine().equals("1"))
                    creaConto(contiCorrentiArray);
            }
            catch (Exception ignored) {}
        }

    }

    private static float deposita(){
        float denaro = 0;
        boolean exitMethods = true;
        while(exitMethods) {
            System.out.print("\nInserisca la cifra da aggiungere: €");
            try {
                denaro = Integer.parseInt("+" + Integer.parseInt(input.nextLine()));
                exitMethods = false;
            }
            catch (Exception e) {
                System.out.print("Errore! Il valore da depositare non può essere uguale a 0€");
            }
        }

        return denaro;
    }

    private static float preleva(contoCorrente verCodice){
        float denaro = 0;
        int scelta;
        boolean exitMethods = true;

        if((verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato")))
            scelta = 2;

        else
            scelta = 1;

        /*
          Prelievo normale
        */
        if(scelta == 1) {
            while(exitMethods) {
                if(verCodice.getTipoConto().equals("Conto Corrente senza Canone"))
                    System.out.print("\nInserisca la cifra da prelevare [+2€ Tax]: €");
                else
                    System.out.print("\nInserisca la cifra da prelevare: €");

                try {
                    denaro = Integer.parseInt("-" + Integer.parseInt(input.nextLine()));
                    exitMethods = false;
                    if(verCodice.getTipoConto().equals("Conto Corrente senza Canone"))
                        //Rimosse le 2€ di tasse
                        denaro = denaro - 2;
                }
                catch (Exception e) {
                    System.out.print("Errore! Il valore da prelevare non può essere uguale a 0€");
                }
            }
            if (verCodice.getSaldoDisponibile() + denaro < 0) {
                System.out.println("\uD83D\uDE1E Mi spiace ma tale somma supera il suo attuale saldo disponibile, pertanto non può prelevare.\n");
                denaro = 0;
            }
            else
                verCodice.setSaldoDisponibile(denaro);
        }

        /*
          Prelievo fruttato
        */
        else if(verCodice.getTipoConto().equals("Conto Deposito non Vincolato")){
            boolean conferma = true;

            //Se sono già stati messi a frutto degli interessi o comunque la lista movimenti non è vuota
            if(verCodice.listaMovimenti.size()!=0){
                System.out.print("Inserisca il codice della transazione (Es. #12345): ");
                String codiceTransazione = input.nextLine();

                //Si rimuovono spazi nell'eventualità si abbia sbagliato a scrivere
                codiceTransazione = codiceTransazione.replaceAll(" ","");

                for(int i = verCodice.listaMovimenti.size() - 1; i>=0; i--) {

                    //Dopo aver controllato che la lista movimenti non sia vuota si verifica se il codice
                    //appena inserito corrisponda ad un altro già esistente
                    if (verCodice.listaMovimenti.get(i) != null && verCodice.getListaMovimenti().get(i).getDescrizioneOperazione().equals(codiceTransazione)) {
                        conferma=false;

                        /*
                            Calcolo Interesse Attuale
                         */

                        //Data Attuale
                        LocalDate dataAttuale = LocalDate.now();

                        //Data del movimento
                        LocalDate dataPrima = verCodice.listaMovimenti.get(i).getDate();

                        //Calcolo giorni tra dataAttuale e dataPrima
                        long noOfDaysBetween = ChronoUnit.DAYS.between(dataAttuale, dataPrima);

                        //Se i giorni sono 0, si aumenta la variabile ad 1
                        if (noOfDaysBetween == 0)
                            noOfDaysBetween = 1;

                        //Denaro somma il valore prima posseduto + gli interessi maturati
                        denaro = (float) (Math.round((verCodice.listaMovimenti.get(i).getImportoContabile() + (verCodice.listaMovimenti.get(i).getImportoContabile() * 1.48 * noOfDaysBetween) / 36500) * 100) / 100.00);
                        denaro=denaro*-1;

                        verCodice.listaMovimenti.get(i).setImportoContabile(0);

                    }
                    else if (conferma)
                        System.out.println("Non esiste nessun movimento con tale codice");
                }
            }
            else
                System.out.println("Ancora nessun movimento");
        }
        else if(verCodice.getTipoConto().equals("Conto Deposito Vincolato")){
            boolean conferma = true;

            //Se sono già stati messi a frutto degli interessi o comunque la lista movimenti non è vuota
            if(verCodice.listaMovimenti.size()!=0){
                System.out.print("Inserisca il codice della transazione (Es. #12345): ");
                String codiceTransazione = input.nextLine();

                //Si rimuovono spazi nell'eventualità si abbia sbagliato a scrivere
                codiceTransazione = codiceTransazione.replaceAll(" ","");

                for(int i = verCodice.listaMovimenti.size() - 1; i>=0; i--) {

                    //Dopo aver controllato che la lista movimenti non sia vuota si verifica se il codice
                    //appena inserito corrisponda ad un altro già esistente
                    if (verCodice.listaMovimenti.get(i) != null && verCodice.getListaMovimenti().get(i).getDescrizioneOperazione().equals(codiceTransazione)) {

                        conferma=false;

                        //Denaro somma il valore prima posseduto
                        denaro = (float) (Math.round((verCodice.listaMovimenti.get(i).getImportoContabile()*100)/100.00));
                        denaro=denaro*-1;

                        verCodice.listaMovimenti.get(i).setImportoContabile(0);

                    }
                    else if (conferma)
                        System.out.println("Non esiste nessun movimento con tale codice");
                }
            }
            else
                System.out.println("Ancora nessun movimento");
        }
        return denaro;
    }

    private static String aggiuntaDescrizione() {
        String causale = "";
        String lunghezzaMax = "Descrizione Operazione";
        boolean exitMethods = true;
        while(exitMethods) {
            System.out.print("Inserisca la causale dell'operazione (Max. " + lunghezzaMax.length() + " Caratteri): ");
            causale = input.nextLine();
            if (causale.length() > lunghezzaMax.length())
                System.out.println("Errore! Inserire una causale più corta.");
            else if(causale.length() == 0)
                System.out.println("Errore! Inserire una causale più lunga.");
            else
                exitMethods = false;
        }
        causale = causale.substring(0,1).toUpperCase() + causale.substring(1).toLowerCase();

        return causale;
    }

    /*
     * =========================================================
     * Crea Conto
     * =========================================================
     */
    @SuppressWarnings("unused")
    private static contoCorrente creaConto(ArrayList<contoCorrente> contiCorrentiArray) throws InterruptedException {
        String dataContabile;
        String dataValuta;
        float importo;

        int intestatari = 0;
        boolean exitMethods = true;

        while (exitMethods) {
            System.out.print("\nQuanti intestatari? (1-3): ");
            try {
                intestatari = Integer.parseInt(input.nextLine());

                if (intestatari == 1 || intestatari == 2 || intestatari == 3) {
                    exitMethods= verificaDatiInseriti();
                } else
                    System.out.println("-valore non valido-");

            } catch (Exception e) {
                System.out.println("-valore non valido!-");
            }
        }

        exitMethods = true;
        infoCliente[] cointestatari = new infoCliente[intestatari];

        for (int i = 0; i < intestatari; i++) {
            if (intestatari == 1)
                System.out.println("\n- Informazioni Cliente: -");
            else if (i == 0)
                System.out.println("- Primo Intestatario: -");
            else if (i == 1)
                System.out.println("- Secondo Intestatario: -");
            else
                System.out.println("- Terzo Intestatario: -");

            //Informazioni cliente - Nome
            String nome;
            nome = verificaNomeCognome("Nome");

            String cognome = verificaNomeCognome("Cognome");
            String cartaID = verificaCartaID(); //ID riconoscitivo carta d'identità (CA00000AA) Numero Unico Nazionale
            String cartaScadenza = verificaData("Data scadenza della Carta d'Identità"); //Scadenza carta d'identità

            //Errore Interno: Cx00 dove C equivale a "C"arta, mentre le cifre sono in progressione
            if (cartaScadenza.equals("0")) {
                System.out.println("Errore Interno: Cx01 | Carta Scaduta! \nSta per essere reindirizzato al menu...\n");
                exitMethods = false;
                TimeUnit.SECONDS.sleep(1);
            }
            else {
                String dataDiNascita = verificaData("Data di nascita");
                //Bisogna avere almeno 18 anni
                if (dataDiNascita.equals("0")) {
                    System.out.println("Errore Interno: Cx02 | Età non valida! \nSta per essere reindirizzato al menu...\n");
                    exitMethods = false;
                    TimeUnit.SECONDS.sleep(1);
                }
                else {
                    String sesso = verificaSesso();
                    String comuneNascita = verificaComune("Nascita");
                    String comuneResidenza = verificaComune("Residenza");

                    String codiceFiscale = verificaCodiceFiscale(nome, cognome, dataDiNascita, sesso, comuneNascita);

                    String cittadinanza = verificaCittadinanza();
                    String indirizzoResidenza = verificaIndirizzoResidenza();
                    int numeroCivico = verificaNumeroCivico();
                    String statoResidenza = verificaStatoResidenza();
                    if (!statoResidenza.equals("Altro")) {
                        int capResidenza = verificaCapResidenza();
                        infoCliente cliente = new infoCliente(nome, cognome, cartaID, cartaScadenza, dataDiNascita, sesso, comuneNascita, codiceFiscale, cittadinanza, statoResidenza, comuneResidenza, indirizzoResidenza, numeroCivico, capResidenza);

                        verificaDatiInseritiFinale(cliente);
                        cointestatari[i] = cliente;

                    }
                }
            }
        }

        if(exitMethods) {
            //informazioni conto
            String IBAN = creaIBAN();
            float saldo = verificaSaldo();
            float interesse = verificaInteresse();
            String tipoConto = verificaTipoConto();
            float saldoDisponibile = 0;
            float saldoContabile = 0;
            ArrayList<contoCorrente.listaMovimenti> listaMovimenti = new ArrayList<>();
            String statoConto = "APERTO";
            ArrayList<contoCorrente.listaFantasma> listaFantasma = new ArrayList<>();
            LocalDate dataUltimoPagamento = LocalDate.now();
            //contoCorrente.listaMovimenti listaMovimenti = new contoCorrente.listaMovimenti[10];
            return new contoCorrente(IBAN, saldoDisponibile, saldoContabile, listaMovimenti, interesse, cointestatari, tipoConto, statoConto, dataUltimoPagamento, listaFantasma);
        }

        return null;

    }

    /*
     * =========================================================
     * Chiusura conto
     * =========================================================
     */

    private static void chiudereConto(ArrayList<contoCorrente> contiCorrentiArray) {
        //Variabile che prende il valore da 0 fino alla dimensione di contiCorrentiArray - 1
        int[] numbers = IntStream.rangeClosed(0, contiCorrentiArray.size() - 1).toArray();

        System.out.println("\n- Informazioni Conto: -");
        System.out.print("Inserisca l'IBAN: ");

        String iban = input.nextLine();

        //Creazione di un conto vuoto
        //noinspection UnusedAssignment
        contoCorrente verCodice = new contoCorrente();
        for (int i : numbers) {

            //verCodice assume il valore di un contoCorrente per ogni indice [i]
            verCodice = contiCorrentiArray.get(i);

            //Dopo aver verificato che l'IBAN non sia vuoto, si verifica che l'IBAN appena inserito
            //corrisponda con quello del cointestatario in questione
            if(verCodice.getIBAN()!=null && verCodice.getIBAN().equalsIgnoreCase(iban)) {
                if(verCodice.getStatoConto().equals("CHIUSO"))
                    System.out.println("Il conto con iban " + iban + " è già stato chiuso.");

                else if(verCodice.getStatoConto().equals("APERTO")){
                    System.out.println("Il conto con iban " + iban + " sta per essere chiuso. \nQuesta azione è irreversibile.");
                    boolean exitMethods= verificaDatiInseriti();

                    if(!exitMethods){
                        System.out.println("Ha prelevato i rimanenti " + verCodice.getSaldoDisponibile() + "€");
                        verCodice.setSaldoDisponibile(0);
                        verCodice.setSaldoContabile(0);
                        verCodice.setStatoConto("CHIUSO");
                    }

                }
            }
            else {
                System.out.println("|| L'IBAN " + iban + " non è stato trovato. Cosa desidera fare? \n" +
                        "||\n" +
                        "|| [1] Creare un nuovo conto\n" +
                        "|| [2] Tornare al menù\n" +
                        "||\n" +
                        "|| ➡ ");


            }

        }
    }

    public static String loginRead(int datiLogin) {
        //Inizializzazione
        Properties prop = new Properties(System.getProperties());
        InputStream input = null;
        String datoLogin = null;

        try {
            //Assegno alla variabile Input il contenuto di ciò che vi è all'interno del File Config
            input = new FileInputStream("resources/config.properties");

            //Carico il file
            prop.load(input);

            if (datiLogin == 1) {
                //Recuperiamo il nome utente dal file
                try {
                    datoLogin = prop.getProperty("utente");
                }
                catch (Exception ignored) {}
            }

            if (datiLogin == 2) {
                //Recuperiamo la password dal File
                try {
                    datoLogin = prop.getProperty("password");
                }
                catch (Exception ignored) {}
            }
        }
        //Gestione di eventuali errori per la lettura del File
        catch (IOException ex) {
            ex.printStackTrace();
        }

        /*
         * Il finally racchiude un pezzo di codice che dovrà essere eseguito a
         * prescindere dal fatto che il codice nel try abbia generato o meno errori
         */ finally {
            if (input != null) {
                try {
                    //Bisogna chiudere la lettura del File
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return datoLogin;
    }

    public static boolean login() {
        Console cn = System.console();
        int scelta;

        try {
            cn.flush();
            int datiLogin = 1;
            String utenteConfig = loginRead(datiLogin);
            datiLogin = 2;
            String passwordConfig = loginRead(datiLogin);

            System.out.println("|| Login [Work In Progress] ||\n");
            System.out.println("[1] Amministratore");
            System.out.println("[2] Utente");
            System.out.print("➡ Scelta: ");
            try {
                scelta = Integer.parseInt(input.nextLine());
                if (scelta == 1) {
                    System.out.print("\n## Nome Utente: ");
                    String username = input.nextLine();
                    if (username.equals(utenteConfig)) {
                        char[] password;
                        System.out.print("## Password: ");
                        password=cn.readPassword();

                        //Verifico se la password corrisponde con quella del Config
                        if (Arrays.equals(password, passwordConfig.toCharArray())) {
                            System.out.println("\nLogin effettuato con successo!\n");
                            return true;
                        }
                    }
                    else {
                        System.out.println("Nome utente non trovato.");
                    }
                }
                else if (scelta == 2) {
                    System.out.print("\nTi stiamo reindirizzando.");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(".\n");
                }
            } catch (Exception e) {
                System.out.println("Valore non valido. \nSei entrato dunque in modalità utente.\n");
            }
        }
        catch (Exception e) {
            System.out.println("|| Per il login eseguire il programma su CMD ||\n");
        }
        return false;
    }


}

