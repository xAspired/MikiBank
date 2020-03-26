import Packages.contoCorrente;
import Packages.infoCliente;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

        System.out.println("Numero Conti Presenti: " + contiCorrentiArray.size() + "\n");

        //Fine Serializzazione e Deserializzazione


        int scelta;
        login();

        scelta = 1;
        menu:
        do {
            boolean whileStatement = true;
            while (whileStatement) {
                System.out.println("\t\tMIKIBANK");
                System.out.println("[0] Uscita dal programma");
                System.out.println("[1] Creazione di un conto corrente");
                System.out.println("[2] Visualizzazione informazione conto corrente");
                System.out.println("[3] Visualizzazione utente - aggiornare dati");
                System.out.println("[4] Chiedere un prestito");
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
                    contoCorrente contoVuoto = new contoCorrente();

                    if(verificaConto.equals(contoVuoto)) {
                        continue menu;
                    }

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
                    chiederePresito(contiCorrentiArray);
                    break;


                default:
                    System.out.print("Azione non valida");
                    break;

            }
        } while (true);
    }

    @SuppressWarnings("unused")
    private static void chiederePresito(ArrayList<contoCorrente> contiCorrentiArray) {
    }

    /*
     * =========================================================
     * Visualizza Info Utente
     * =========================================================
     */
    @SuppressWarnings("unused")
    private static void visualizzaInfoUtente(ArrayList<contoCorrente> contiCorrentiArray) {
        int[] numbers = IntStream.rangeClosed(0, contiCorrentiArray.size() - 1).toArray();
        boolean presenzaCodice = false;

        System.out.println("\n- Informazioni Cliente: - ");
        System.out.println("Inserisca il codice fiscale:");

        String codiceFiscale = input.nextLine();
        codiceFiscale=codiceFiscale.toUpperCase();
        String nome = "";
        String cognome = "";
        //Informazioni ciente

        StringBuilder totaleConti = new StringBuilder();

        try {
            for(int i : numbers) {
                //Si crea un oggetto temporaneo per ogni indice [i]
                contoCorrente verCodice = contiCorrentiArray.get(i);
                infoCliente[] verCodiceCliente = verCodice.getCointestatari();

                //noinspection ForLoopReplaceableByForEach
                for(int h = 0; h<verCodiceCliente.length; h++) { /* for(int h = 0; h<verCodiceCliente.length; h++) { */
                    //System.out.println(verCodiceCliente[h].getCodiceFiscale());
                    if (verCodiceCliente[h]!=null && verCodiceCliente[h].getCodiceFiscale().equals(codiceFiscale)) {
                        nome = verCodiceCliente[h].getNome();
                        cognome = verCodiceCliente[h].getCognome();
                        totaleConti.append("\n|| IBAN: ").append(verCodice.getIBAN()).append("    ").append("Tipo Conto: ").append(verCodice.getTipoConto()).append("\n");

                    }
                }
            }
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
                            exitMethods = false;
                            //creaConto();
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
        } catch (Exception ignored) {}
    }

    /*
     * =========================================================
     * Visualizza Info Conto
     * =========================================================
     */
    @SuppressWarnings("unused")
    private static void visualizzaInfoConto(ArrayList<contoCorrente> contiCorrentiArray) throws InterruptedException {
        int[] numbers = IntStream.rangeClosed(0, contiCorrentiArray.size() - 1).toArray();
        StringBuilder resocontoConto = new StringBuilder();

        System.out.println("\n- Informazioni Conto: -");
        System.out.print("Inserisca l'IBAN: ");

        String iban = input.nextLine();
        contoCorrente verCodice = new contoCorrente();
        int posizione = 0;
        //try {
            for (int i : numbers) {
                //Si crea un oggetto temporaneo per ogni indice [i]
                verCodice = contiCorrentiArray.get(i);
                infoCliente[] verCodiceCliente = verCodice.getCointestatari();
                    if(verCodice.getIBAN()!=null && verCodice.getIBAN().equalsIgnoreCase(iban)) {

                        //verifica i depositi ormai fruttati
                        for(int b = verCodice.listaMovimenti.size()-1; b>=0; b--){
                            if(verCodice.listaMovimenti.get(b)!=null && verCodice.listaMovimenti.get(b).getDescrizioneOperazione().charAt(0)=='#'){
                                LocalDate time = LocalDate.now();
                                String tempo = "";
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
                                NumberFormat format = new DecimalFormat("#.##");
                                String temp = verCodice.listaMovimenti.get(b).getDescrizioneOperazione();
                                temp=temp.substring(1);
                                //Calcolo numero giorni
                                long noOfDaysBetween = Integer.parseInt(temp);
                                String importoFruttato = format.format((float) ((verCodice.listaMovimenti.get(b).getImportoContabile() * 1.48 * noOfDaysBetween) / 36500));

                                if(verCodice.listaMovimenti.get(b).getDataContabile().equals(tempo) && verCodice.listaMovimenti.get(b).getImportoContabile()!=0){
                                    //importo DAVVERO totale
                                    float importoTotale = (float) (verCodice.listaMovimenti.get(b).getImportoContabile() + (verCodice.listaMovimenti.get(b).getImportoContabile() * 1.48 * noOfDaysBetween) / 36500);
                                    verCodice.listaMovimenti.get(b).setImportoContabile(importoTotale);
                                    verCodice.setSaldoDisponibile(importoTotale);
                                    System.out.println("Sono stati accreditati " + verCodice.listaMovimenti.get(b).getImportoContabile() + " sul tuo conto");
                                    //Azzerato il movimento
                                    verCodice.listaMovimenti.get(b).setImportoContabile(0);

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
                                    verCodice.listaMovimenti.add(movimentoBanca);
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
             * Visualizza Info Conto - prelievo,deposito
             * =========================================================
             */
            if(resocontoConto.length() != 0) {
                System.out.println("\n===================================================================\n                    " + iban + "\n===================================================================\n" + resocontoConto);
                System.out.println("\n===================================================================\n                         " + "Lista Movimenti" + "\n===================================================================\n");
                verCodice.toStringListaMovimenti();
                System.out.println("\n\n** Cosa vuole fare? **\n[1] Depositare\n[2] Prelevare");
                if(verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato"))
                    System.out.println("[3] Deposito Cauzionale\n[4] Tornare al menù");
                else
                    System.out.println("[3] Tornare al menù");
                boolean exitMethods = true;
                int scelta = 0;

                while (exitMethods) {
                    try {
                        System.out.print(" ➡ ");
                        scelta = Integer.parseInt(input.nextLine());
                        if(!(scelta!=1 && scelta!=2 && scelta!=3) && !(verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato")))
                            exitMethods = false;
                        else if (!(scelta!=1 && scelta!=2 && scelta!=3 && scelta!=4) && (verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato")))
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

                if(scelta == 1) {
                    float importo = deposita();
                    String descrizione = aggiuntaDescrizione();
                    System.out.println("Ha appena depositato " + importo + "€ con causale: " + descrizione);
                    verCodice.setSaldoContabile(importo);
                    verCodice.setSaldoDisponibile(importo);
                    ArrayList<contoCorrente.listaMovimenti> listaMovimentiTemp = verCodice.getListaMovimenti();
                    contoCorrente.listaMovimenti oggettoMovimenti = new contoCorrente.listaMovimenti();
                    listaMovimentiTemp.add(oggettoMovimenti);
                    //if (verCodice.getMovimentoAttuale()<= 9) {
                        int movimentoAttuale = verCodice.getMovimentoAttuale();
                        listaMovimentiTemp.get(movimentoAttuale).setImportoDisponibile(importo);
                        LocalDateTime time = LocalDateTime.now();
                        String tempo = "";
                         /*
                            Si gestisce il caso in cui il numero sia unico e quindi non vada
                            a rovinare la tabulazione della Lista Movimenti
                         */
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

                          listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDataDisponibile(tempo);
                          listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setImportoContabile(importo);
                          listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDataContabile(tempo);
                          listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDescrizioneOperazione(descrizione);
                          listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDate(LocalDate.now());
                          verCodice.setMovimentoAttuale(verCodice.getMovimentoAttuale() + 1);
                    //}
                    if(verCodice.getMovimentoAttuale()==10)
                        verCodice.setMovimentoAttuale(0);

                    //System.out.print(verCodice.getListaMovimenti());
                    //System.out.print(verCodice.getListaMovimenti().size());

                    verCodice.listaMovimenti=listaMovimentiTemp;
                    contiCorrentiArray.set(posizione,verCodice);

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

                if(scelta == 2) {
                    ArrayList<contoCorrente.listaMovimenti> listaMovimentiTemp = verCodice.getListaMovimenti();
                    float importo = preleva(verCodice);
                    if (importo != 0) {
                            String descrizione = aggiuntaDescrizione();

                        //Si verifica che la somma prelevabile rientri nel saldo complessivo
                        if (verCodice.getSaldoDisponibile() + importo < 0 && importo > 0) {
                            System.out.println("\uD83D\uDE1E Mi spiace ma tale somma supera il suo attuale saldo disponibile, pertanto non può prelevare.");
                        }
                        else {
                            System.out.println("Ha appena prelevato " + importo + "€ con causale: " + descrizione);
                            verCodice.setSaldoContabile(importo);

                            //if (verCodice.getMovimentoAttuale()<= 9) {
                            contoCorrente.listaMovimenti oggettoMovimenti = new contoCorrente.listaMovimenti();
                            listaMovimentiTemp.add(oggettoMovimenti);
                            int movimentoAttuale = verCodice.getMovimentoAttuale();
                            listaMovimentiTemp.get(movimentoAttuale).setImportoDisponibile(importo);
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

                            listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDataDisponibile(tempo);
                            listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setImportoContabile(importo);
                            listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDataContabile(tempo);
                            listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDescrizioneOperazione(descrizione);
                            listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDate(LocalDate.now());
                            verCodice.setMovimentoAttuale(verCodice.getMovimentoAttuale() + 1);
                            //}
                            if (verCodice.getMovimentoAttuale() == 10)
                                verCodice.setMovimentoAttuale(0);

                            //System.out.print(verCodice.getListaMovimenti());
                            //System.out.print(verCodice.getListaMovimenti().size());

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

                if(scelta == 3 && ((verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato")))) {
                    float importo = deposita();

                    exitMethods = true;
                    int periodoFrutto = 0;
                    while (exitMethods)
                        try {
                            System.out.print("Quanti mesi si intende fruttare tale importo? (3/6/9/12/60): ");
                            periodoFrutto = Integer.parseInt(input.nextLine());
                            if (!(periodoFrutto != 3 && periodoFrutto != 6 && periodoFrutto != 9 && periodoFrutto != 12 && periodoFrutto != 60)) {
                                exitMethods = false;
                            } else
                                System.out.println("Inserire valore valido");
                        } catch (Exception e) {
                            System.out.println("Inserire valore valido");
                        }

                    //verCodice.setSaldoDisponibile(importo);
                    verCodice.setSaldoContabile(importo);
                    ArrayList<contoCorrente.listaMovimenti> listaMovimentiTemp = verCodice.getListaMovimenti();

                    //if (verCodice.getMovimentoAttuale()<= 9) {
                    int movimentoAttuale = verCodice.getMovimentoAttuale();
                    contoCorrente.listaMovimenti oggettoMovimenti = new contoCorrente.listaMovimenti();
                    listaMovimentiTemp.add(oggettoMovimenti);
                    listaMovimentiTemp.get(movimentoAttuale).setImportoDisponibile(importo);

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
                    } else if (noOfDaysBetween >= 100 && noOfDaysBetween < 1000){
                        descrizione += "00";
                    }
                    else {
                        descrizione += "0";
                    }
                    descrizione += noOfDaysBetween;

                    descrizione += Integer.parseInt(String.valueOf(verCodice.getListaMovimenti().size()));

                    System.out.println("Ha appena depositato " + importo + "€ con codice: " + descrizione);

                    //calcolo importo disponibile
                    float importoFruttato = ((float) ((importo * 1.48 * noOfDaysBetween) / 36500));
                    System.out.println("Se attenderà " + noOfDaysBetween + " giorni, i suoi interessi corrisponderanno a €" + Math.round((importoFruttato)*100)/100.00 + ".\nPer un totale di €" + Math.round((importo + importoFruttato)*100)/100.00);


                    listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDataDisponibile(tempo);
                    listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setImportoContabile(importo);
                    listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDataContabile(tempoDisponibile);
                    listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDescrizioneOperazione(descrizione);
                    listaMovimentiTemp.get(verCodice.getMovimentoAttuale()).setDate(LocalDate.now());
                    verCodice.setMovimentoAttuale(verCodice.getMovimentoAttuale() + 1);
                    //}
                    if(verCodice.getMovimentoAttuale()==10)
                        verCodice.setMovimentoAttuale(0);

                    //System.out.print(verCodice.getListaMovimenti());
                    //System.out.print(verCodice.getListaMovimenti().size());

                    verCodice.listaMovimenti=listaMovimentiTemp;
                    contiCorrentiArray.set(posizione,verCodice);

                    System.out.print("\n\nSta per essere reindirizzato al menù");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(".\n");
                }

                if(scelta == 3 && !((verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato")))) {
                    System.out.print("\n\nSta per essere reindirizzato al menù");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(".\n");
                }

                if(scelta == 4 && (verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato"))) {
                    System.out.print("\n\nSta per essere reindirizzato al menù");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print(".");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(".\n");
                }
            }
            else {
                System.out.print("\n\n|| L'IBAN " + iban + " non è stato trovato. Cosa desidera fare? \n||\n|| [1] Creare un nuovo conto\n|| [2] Tornare al menù\n||\n|| ➡ ");
                try {
                    if (input.nextLine().equals("1")) {
                        creaConto(contiCorrentiArray);
                    }
                } catch (Exception ignored) {}
            }


        //} catch (Exception ignored){}

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
        int scelta = 0;
        boolean exitMethods = true;

        while(exitMethods) {
            try {
                if((verCodice.getTipoConto().equals("Conto Deposito non Vincolato") || verCodice.getTipoConto().equals("Conto Deposito Vincolato"))) {
                    System.out.println("\nCosa vuole prelevare?\n[1] Saldo Disponibile\n[2] Deposito Cauzionale (Saldo Contabile)");
                    System.out.print(" ➡ ");
                    scelta = Integer.parseInt(input.nextLine());
                }
                else
                    scelta = 1;

                if(scelta == 1 || scelta == 2)
                    exitMethods = false;
            }
            catch (Exception e) {
                System.out.print("Errore! Valore non valido.");
            }
        }

        /*
        Prelievo normale
        */
        if(scelta == 1) {
            exitMethods = true;
            while(exitMethods) {
                System.out.print("\nInserisca la cifra da prelevare: €");
                try {
                    denaro = Integer.parseInt("-" + Integer.parseInt(input.nextLine()));
                    exitMethods = false;
                }
                catch (Exception e) {
                    System.out.print("Errore! Il valore da prelevare non può essere uguale a 0€");
                }
            }
            verCodice.setSaldoDisponibile(denaro);
        }

        /*
        Prelievo fruttato
        */
        else {
            boolean conferma = true;
            if(verCodice.listaMovimenti.size()!=0){
                System.out.print("Inserisca il codice della transazione (Es. #12345): ");
                String codiceTransazione = input.nextLine();
                codiceTransazione = codiceTransazione.replaceAll(" ","");
                for(int i = verCodice.listaMovimenti.size()-1; i>=0; i--) {
                    //esiste il codice di transazione?
                    if (verCodice.listaMovimenti.get(i) != null && verCodice.getListaMovimenti().get(i).getDescrizioneOperazione().equals(codiceTransazione)) {
                        conferma=false;
                        //////interesse
                        //data di inizio
                        String dataInizio = verCodice.listaMovimenti.get(i).getDataDisponibile();
                        //data attuale
                        LocalDate dataAttuale= LocalDate.now();
                        LocalDate dataPrima = verCodice.listaMovimenti.get(i).getDate();;
                        long noOfDaysBetween = ChronoUnit.DAYS.between(dataAttuale, dataPrima);

                        //Calcolo numero giorni
                        if (noOfDaysBetween == 0)
                            noOfDaysBetween = 1;

                        //importo totale
                        denaro = (float) (Math.round((verCodice.listaMovimenti.get(i).getImportoContabile() + (verCodice.listaMovimenti.get(i).getImportoContabile() * 1.48 * noOfDaysBetween) / 36500) * 100) / 100.00);
                        denaro=denaro*-1;
                        verCodice.listaMovimenti.get(i).setImportoContabile(denaro);
                        //verCodice.setSaldoDisponibile(denaro);
                        //System.out.println(denaro + "dchcbdshb" + denaro*-1);
                        //Azzerato il movimento
                        verCodice.listaMovimenti.get(i).setImportoContabile(0);

                    } else if (conferma)
                        System.out.println("Non esiste nessun movimento con tale codice");
                }
            }
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
            String cartaScadenza = verificaData("Data scadenza Carta d'Identità"); //Scadenza carta d'identità

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
            //contoCorrente.listaMovimenti listaMovimenti = new contoCorrente.listaMovimenti[10];
            int movimentoAttuale = 0;
            return new contoCorrente(IBAN, saldoDisponibile, saldoContabile, listaMovimenti, interesse, cointestatari, tipoConto, movimentoAttuale);
        }

        return new contoCorrente();

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

    public static void login() {
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
    }


}

