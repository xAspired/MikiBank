import Packages.contoCorrente;
import Packages.infoCliente;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
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
            } else {
                contiCorrentiArray = new ArrayList<>();
            }

        System.out.println("Numero Conti Presenti: " + contiCorrentiArray.size() + "\n");

        //Fine Serializzazione e Deserializzazione

        int scelta;
        login();

        scelta = 1;
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
                    //Creazione Conto Corrente
                    contiCorrentiArray.add(creaConto(contiCorrentiArray));

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
        System.out.println("Inserisci il codice fiscale:");

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
                        totaleConti.append("\n|| IBAN: ").append(verCodice.getIBAN()).append("    ").append("Saldo Contabile: ").append(verCodice.getSaldoContabile()).append("\n");

                    }
                }
            }
            if(totaleConti.length() != 0) {
                System.out.println("\n\n|| Attualmente " + nome + " " + cognome + " possiede i seguenti conti: \n||" + totaleConti);
                System.out.print("Stai per essere reindirizzato al menù");
                TimeUnit.SECONDS.sleep(1);
                System.out.print(".");
                TimeUnit.SECONDS.sleep(1);
                System.out.print(".");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(".\n");
            }
            else {
                System.out.print("\n\n|| il codice fiscale " + codiceFiscale + " non è associato alcun conto. ");
                int scelta;
                boolean exitMethods = true;
                while (exitMethods) {

                    System.out.print("Vuoi creare un nuovo conto\n|| [1] SI \n|| [2] NO\n||\n|| ➡ ");
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
        System.out.print("Inserisci l'IBAN: ");

        String iban = input.nextLine();
        contoCorrente verCodice = new contoCorrente();
        int posizione = 0;
        //try {
            for (int i : numbers) {
                //Si crea un oggetto temporaneo per ogni indice [i]
                verCodice = contiCorrentiArray.get(i);
                infoCliente[] verCodiceCliente = verCodice.getCointestatari();

                    if(verCodice.getIBAN().equalsIgnoreCase(iban)) {
                        posizione=i;
                        for (int h = 0; h < verCodiceCliente.length; h++) {
                            resocontoConto.append("\n|| Cointestatario ").append(h + 1).append(": ");
                            if(verCodiceCliente[h].getSesso().equalsIgnoreCase("F"))
                                resocontoConto.append("\uD83D\uDE4E\uD83C\uDFFC\u200D♀ ");
                            else
                                resocontoConto.append("\uD83D\uDE4E\uD83C\uDFFD\u200D♂ ");
                            resocontoConto.append(verCodiceCliente[h].getNome()).append(verCodiceCliente[h].getCognome()).append("    [").append(verCodiceCliente[h].getDataDiNascita()).append("]");
                        }
                        resocontoConto.append("\n||\n|| Valuta: ").append(verCodice.getSaldoDisponibile()).append("€    ").append("Saldo contabile: ").append(verCodice.getSaldoContabile());
                    }

            }

            if(resocontoConto.length() != 0) {
                System.out.println("\n===============================================================\n                  " + iban + "\n===============================================================\n" + resocontoConto);
                System.out.println("\n===============================================================\n               " + "       Lista Movimenti" + "\n===============================================================\n");
                verCodice.toStringListaMovimenti();
                System.out.println("\n\n** Cosa vuole fare? **\n[1] Depositare\n[2] Prelevare\n[3] Tornare al menù");
                System.out.print(" ➡ ");
                int scelta = Integer.parseInt(input.nextLine());

                    if(scelta == 1) {
                        float importo = deposita();
                        String descrizione = aggiuntaDescrizione();
                        System.out.println("Ha appena depositato " + importo + "€ con causale: " + descrizione +  "\n");
                        verCodice.setSaldoContabile(importo);
                        verCodice.setSaldoDisponibile(importo);
                        contoCorrente.listaMovimenti[] listaMovimentiTemp = verCodice.getListaMovimenti();
                        //for(int i=listaMovimentiTemp.length-1; i>0 && !verifica; i--) {
                            if (verCodice.getMovimentoAttuale()>=0) {
                                int movimentoAttuale = verCodice.getMovimentoAttuale();
                                listaMovimentiTemp[movimentoAttuale].setImportoDisponibile(importo);
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

                                listaMovimentiTemp[verCodice.getMovimentoAttuale()].setDataDisponibile(tempo);
                                listaMovimentiTemp[verCodice.getMovimentoAttuale()].setImportoContabile(importo);
                                listaMovimentiTemp[verCodice.getMovimentoAttuale()].setDataContabile(tempo);
                                listaMovimentiTemp[verCodice.getMovimentoAttuale()].setDescrizioneOperazione(descrizione);
                                verCodice.setMovimentoAttuale(verCodice.getMovimentoAttuale() - 1);
                            }
                            if(verCodice.getMovimentoAttuale()==-1)
                                verCodice.setMovimentoAttuale(9);

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
                if(scelta == 2) {
                    float importo = preleva();
                    String descrizione = aggiuntaDescrizione();
                    System.out.println("Ha appena prelevato " + importo + "€ con causale: " + descrizione +  "\n");
                    verCodice.setSaldoContabile(importo);
                    verCodice.setSaldoDisponibile(importo);
                    contoCorrente.listaMovimenti[] listaMovimentiTemp = verCodice.getListaMovimenti();
                    //for(int i=listaMovimentiTemp.length-1; i>0 && !verifica; i--) {
                    if (verCodice.getMovimentoAttuale()>=0) {
                        int movimentoAttuale = verCodice.getMovimentoAttuale();
                        listaMovimentiTemp[movimentoAttuale].setImportoDisponibile(importo);
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

                        listaMovimentiTemp[verCodice.getMovimentoAttuale()].setDataDisponibile(tempo);
                        listaMovimentiTemp[verCodice.getMovimentoAttuale()].setImportoContabile(importo);
                        listaMovimentiTemp[verCodice.getMovimentoAttuale()].setDataContabile(tempo);
                        listaMovimentiTemp[verCodice.getMovimentoAttuale()].setDescrizioneOperazione(descrizione);
                        verCodice.setMovimentoAttuale(verCodice.getMovimentoAttuale() - 1);
                    }
                    if(verCodice.getMovimentoAttuale()==-1)
                        verCodice.setMovimentoAttuale(9);

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
                if(scelta == 3) {
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
    private static float preleva(){
        float denaro;
        System.out.print("\nInserisca la cifra da prelevare: €");
        denaro = Integer.parseInt("-" + input.nextLine());
        System.out.println("Ha appena prelevato " + denaro + "€\n");
        return denaro;
    }

    private static String aggiuntaDescrizione() {
        String causale = "";
        String lunghezzaMax = "Descrizione Operazione";
        boolean exitMethods = true;
        while(exitMethods) {
            System.out.print("Inserisca la causale del deposito (Max. " + lunghezzaMax.length() + " Caratteri): ");
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
        boolean conferma = true;

        while (conferma) {
            System.out.print("\nQuanti intestatari? (1-3): ");
            try {
                intestatari = Integer.parseInt(input.nextLine());

                if (intestatari == 1 || intestatari == 2 || intestatari == 3) {
                    conferma= verificaDatiInseriti();
                } else
                    System.out.println("-valore non valido-");

            } catch (Exception e) {
                System.out.println("-valore non valido!-");
            }
        }

        //infoCliente cliente = new infoCliente;

        infoCliente[] cointestatari = new infoCliente[intestatari];
        @SuppressWarnings("UnusedAssignment") contoCorrente conto = new contoCorrente();
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
            if (cartaScadenza.equals("0")) {
                System.out.println("Errore - la carta d'identità è scaduta. \nStai per essere reindirizzato al menu...\n");
                TimeUnit.SECONDS.sleep(1);
            }
            else {
                String dataDiNascita = verificaData("Data di nascita");
                //Bisogna avere almeno 18 anni
                if (dataDiNascita.equals("0")) {
                    System.out.println("Errore - l'età del cliente non soddisfa l'età minima \nStai per essere reindirizzato al menu...");
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
        //informazioni conto
        String IBAN = creaIBAN();
        float saldo = verificaSaldo();
        float interesse = verificaInteresse();
        String tipoConto = verificaTipoConto();
        float saldoDisponibile = 0;
        float saldoContabile = 0;
        contoCorrente.listaMovimenti[] listaMovimenti = new contoCorrente.listaMovimenti[10];
        int movimentoAttuale = 9;
        conto = new contoCorrente(IBAN, saldoDisponibile, saldoContabile, listaMovimenti, interesse, cointestatari, tipoConto, movimentoAttuale);
        return conto;

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

