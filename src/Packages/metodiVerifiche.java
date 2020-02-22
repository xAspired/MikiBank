package Packages;

//Librerie java.io
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//Librerie java.util
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class metodiVerifiche {
    static Scanner input = new Scanner(System.in);
    static boolean exitMethods = true;
    static int contoNumeroIBAN = 0;


    public static float verificaIBAN() {
        float saldo = 0;
        return saldo;
    }

    public static float verificaSaldo() {
        float saldo = 0;
        return saldo;
    }

    public static float verificaInteresse() {
        float interesse = 0;
        return interesse;
    }

    public static String verificaTipoConto() {
        String tipoConto = "";
        return tipoConto;
    }

    public static String verificaNomeCognome(String var) {
        System.out.println(var + ":");
        String nome;
        int j = 0;

        exitMethods = false;
        do {
            nome = input.nextLine();
            if (nome.length() != 0)
                exitMethods = true;
            for (int i = 0; i < nome.length() && exitMethods; i++) {
                if (!Character.isLetter(nome.charAt(i)) && nome.charAt(i) != ' ') {
                    exitMethods = false;
                    System.out.println("Non ci possono essere numeri. Reinserisci...");
                }
            }

        } while (!exitMethods);

        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1, nome.length()).toLowerCase();

        exitMethods = true;
        return nome;
    }

    public static String verificaCartaID() {
        //System.out.println("cartaID: (CA00000AA)");

        String cartaID = "";
        String tempCartaID;

        do {
            System.out.println("cartaID: (CA00000AA o XA0000000)");
            exitMethods = true;
            cartaID = input.nextLine();
            cartaID = cartaID.toUpperCase();

            cartaID = cartaID.replace(" ", "");

            //evitare errore dovuto a premere troppe volte invio
            if (cartaID.length() == 0 || cartaID.length() == 1)
                exitMethods = false;

            if (Objects.equals(cartaID.charAt(0), 'C') && Objects.equals(cartaID.charAt(1), 'A') && cartaID.length() == 9 && exitMethods) {

                for (int i = 2; i < cartaID.length() && exitMethods; i++) {

                    if (i < 7 && Character.isLetter(cartaID.charAt(i))) {
                        exitMethods = false;
                        System.out.println("L' ID della carta inserita non è valida.");
                    } else if (i > 6 && !Character.isLetter(cartaID.charAt(i))) {
                        exitMethods = false;
                        System.out.println("L' ID della carta inserita non è valida.");
                    }
                }
            } else if (Objects.equals(cartaID.charAt(0), 'X') && Objects.equals(cartaID.charAt(1), 'A') && cartaID.length() == 9 && exitMethods) {


                for (int i = 2; i < cartaID.length() && exitMethods; i++) {

                    if (i < 7 && Character.isLetter(cartaID.charAt(i))) {
                        exitMethods = false;
                        System.out.println("L' ID della carta inserita non è valida.");
                    } else if (i > 6 && !Character.isLetter(cartaID.charAt(i))) {
                        exitMethods = false;
                        System.out.println("L' ID della carta inserita non è valida.");
                    }
                }
            } else
                exitMethods = false;

        } while (!exitMethods);

        exitMethods = true;
        return cartaID;
    }

    public static String verificaData(String var) {
        System.out.println(var + ": (DD.MM.YYYY)");

        String data = "";
        String cartaScadenza;
        System.out.println("Formato:DD.MM.YYYY");
        do {
            exitMethods = true;
            data = input.nextLine();

            if (data.length() != 10) {
                exitMethods = false;
                System.out.println("Il valore inserito non rispetta la lunghezza");
            }

            for (int i = 0; i < data.length() && exitMethods; i++) {

                if (i == 2 && i == 5 && data.charAt(i) == '.') {
                    exitMethods = false;
                    System.out.println("Hai dimenticato i punti.");
                } else if (Character.isLetter(data.charAt(i))) {
                    exitMethods = false;
                    System.out.println("Non ci possono essere lettere.");
                }
            }
        } while (!exitMethods);

        //si verifica nel cso della scadenza carta che non sia già scduta
        String verifica = "";
        if (var.equals("data scadenza carta")) {
            verifica = "" + data.charAt(data.length() - 4) + data.charAt(data.length() - 3) + data.charAt(data.length() - 2) + data.charAt(data.length() - 1);
            int verifica1 = Integer.parseInt(verifica);
            if (verifica1 < 2020) {
                data = "0";
                System.out.println("La carta è scaduta.");
            }
        }

        exitMethods = true;
        return data;
        }

        public static String verificaSesso () {
            System.out.println("sesso:");

            String cartaSesso = "";
            int posizione = 0;
            do {
                System.out.println("1-Maschio;");
                System.out.println("2-Femmina;");

                System.out.println("Inserisci un valore:");
                do {
                    try {
                        posizione = Integer.parseInt(input.nextLine());
                        if (posizione == 1 || posizione == 2) {
                            exitMethods = false;
                        } else System.out.println("Il valore inserito non è valido - numero sbagliato;");
                    } catch (Exception e) {
                        System.out.println("Il valore inserito non è valido;");
                    }
                } while (exitMethods && (posizione != 1 || posizione != 2));
                exitMethods = true;

                if (posizione == 1 || posizione == 2)
                    switch (posizione) {
                        case 1:
                            cartaSesso = "M";
                            exitMethods = true;
                            break;
                        case 2:
                            cartaSesso = "F";
                            exitMethods = true;
                            break;
                        default:
                            System.out.println("il valore inserito non è validogxgbhx;");
                    }
                else System.out.println("Il valore inserito non è valido;");


            } while (!exitMethods);

            exitMethods = true;
            return cartaSesso;
        }

        public static String verificaCodiceFiscale (String nome, String cognome, String dataDiNascita){
            System.out.println("codice fiscale:");

            nome = nome.toUpperCase();
            cognome = cognome.toUpperCase();

            String codiceFiscale = "";
            do {
                exitMethods = true;
                codiceFiscale = input.nextLine();
                codiceFiscale = codiceFiscale.toUpperCase();
                System.out.println(codiceFiscale);
                //lunghezza
                if (codiceFiscale.length() != 16) {
                    exitMethods = false;
                    System.out.println("Il valore inserito non ha una lunghezza valida");
                }

                int conta = 0;
                StringBuilder temp = new StringBuilder();
                System.out.println("5");

                //cognome
                for (int i = 0; i < cognome.length() && exitMethods; i++) {
                    if (cognome.charAt(i) != 'A' && cognome.charAt(i) != 'E' && cognome.charAt(i) != 'I' && cognome.charAt(i) != 'O' && cognome.charAt(i) != 'U' && cognome.charAt(i) != ' ') {
                        conta++;
                        if (conta < 4)
                            temp.append(cognome.charAt(i));
                    }
                }
                System.out.println("4" + temp);

                //nome
                for (int i = 0; i < nome.length() && exitMethods; i++) {
                    if (nome.charAt(i) != 'A' && nome.charAt(i) != 'E' && nome.charAt(i) != 'I' && nome.charAt(i) != 'O' && cognome.charAt(i) != 'U' && nome.charAt(i) != ' ') {
                        conta++;
                        if (conta < 8)
                            temp.append(nome.charAt(i));
                    }
                }
                System.out.println("3" + temp);

                //anno
                for (int i = dataDiNascita.length() - 2; i < dataDiNascita.length() && exitMethods; i++) {
                    temp.append(dataDiNascita.charAt(i));
                }
                System.out.println("2" + temp);

                //mese
                String mese = "";
                for (int i = dataDiNascita.length() - 7; i < dataDiNascita.length() - 5 && exitMethods; i++) {
                    mese += dataDiNascita.charAt(i);
                }

                switch (mese) {
                    case "01":
                        temp.append("A");
                        break;

                    case "02":
                        temp.append("B");
                        break;

                    case "03":
                        temp.append("C");
                        break;
                    case "04":
                        temp.append("D");
                        break;
                    case "05":
                        temp.append("E");
                        break;
                    case "06":
                        temp.append("F");
                        break;
                    case "07":
                        temp.append("G");
                        break;
                    case "08":
                        temp.append("H");
                        break;
                    case "09":
                        temp.append("I");
                        break;
                    case "10":
                        temp.append("L");
                        break;
                    case "11":
                        temp.append("M");
                        break;
                    case "12":
                        temp.append("N");
                        break;
                }
                System.out.println("1" + temp);

                //giorno
                for (int i = 0; i < 2 && exitMethods; i++) {
                    temp.append(dataDiNascita.charAt(i));
                }
                System.out.println("1" + temp);

                temp = new StringBuilder(temp.toString().toUpperCase());
                System.out.println(temp);
                for (int i = 0; i < temp.length() && exitMethods; i++) {
                    if (temp.charAt(i) != codiceFiscale.charAt(i)) {
                        exitMethods = false;
                        System.out.println("Il codice fiscale non è completamente valido" + temp);
                    }
                }

                for (int i = codiceFiscale.length() - 5; i < codiceFiscale.length() && exitMethods; i++) {

                    if (i > 11 && i < 15 && Character.isLetter(codiceFiscale.charAt(i))) {
                        exitMethods = false;
                        System.out.println("Ci sono lettere in posizioni errate.");
                    } else if ((i == 11 || i == 15) && !Character.isLetter(codiceFiscale.charAt(i))) {
                        exitMethods = false;
                        System.out.println("Ci sono numeri dove non dovrebbero essere." + codiceFiscale.charAt(i));
                    }
                }
                System.out.println("1" + temp);

            } while (!exitMethods);

            exitMethods = true;
            return codiceFiscale;
        }


        public static String verificaCittadinanza () {
            String cittadinanza = null;
            while (exitMethods) {
                System.out.print("Cittadinanza: ");
                try {
                    cittadinanza = input.nextLine();
                    exitMethods = !cittadinanza.equalsIgnoreCase("Italiano") && !cittadinanza.equalsIgnoreCase("Italiana");

                } catch (Exception e) {
                    System.out.print("Cittadinanza non valida!");
                }
            }
            exitMethods = true;
            return cittadinanza;
        }

        public static String verificaIndirizzoResidenza () {
            String indirizzoResidenza = null;
            while (exitMethods) {
                System.out.print("Indirizzo Residenza: ");
                try {
                    indirizzoResidenza = input.nextLine();
                    exitMethods = false;
                } catch (Exception e) {
                    System.out.print("Indirizzo non valido!");
                }
            }
            exitMethods = true;
            return indirizzoResidenza;
        }

        public static String verificaNumeroCivico () {
            String numeroCivico = null;
            while (exitMethods) {
                System.out.print("Numero Civico: ");
                try {
                    numeroCivico = input.nextLine();
                    if (numeroCivico.length() <= 4) {
                        exitMethods = false;
                    }
                } catch (Exception e) {
                    System.out.print("Indirizzo Civico non valido!");
                }
            }
            exitMethods = true;
            return numeroCivico;
        }

        public static String verificaComuneResidenza () {
            String comuneResidenza = null;
            while (exitMethods) {
                System.out.print("Comune Residenza: ");
                try {
                    comuneResidenza = input.nextLine();
                    exitMethods = false;
                } catch (Exception e) {
                    System.out.print("Comune Residenza non valido!");
                }
            }
            exitMethods = true;
            return comuneResidenza;
        }

        public static String verificaStatoResidenza () {
            String statoResidenza = null;
            while (exitMethods) {
                System.out.print("Stato Residenza: ");
                try {
                    statoResidenza = input.nextLine();
                    exitMethods = false;
                } catch (Exception e) {
                    System.out.print("Stato Residenza non valido!");
                }
            }
            exitMethods = true;
            return statoResidenza;
        }

        public static int verificaCapResidenza () {
            int CAP = 0;
            while (exitMethods) {
                System.out.print("CAP: ");
                try {
                    CAP = input.nextInt();
                    exitMethods = false;
                } catch (Exception e) {
                    System.out.print("CAP non valido!");
                }
            }
            exitMethods = true;
            return CAP;
        }

        public static String creaIBAN() {

            //Inizializzazione
            Properties prop = new Properties(System.getProperties());
            InputStream input = null;

            try {
                //Assegno alla variabile Input il contenuto di ciò che vi è all'interno del File Config
                input = new FileInputStream("resources/config.properties");

                //Carico il file
                prop.load(input);

                //Prendo in considerazione il dato all'interno del File
                try {

                    contoNumeroIBAN = Integer.parseInt(prop.getProperty("contoNumeroIBAN"));
                } catch (Exception ignored) {
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

            //Numero Conto Corrente (12 Cifre)

            String IBAN = "IT60X";

            //Limite minimo e massimo (5 Cifre)
            IBAN += 10000 + (int) (Math.random() * ((19999 - 10000) + 1)); //Codice ABI > 5 Cifre
            IBAN += 10000 + (int) (Math.random() * ((19999 - 10000) + 1)); //Codice CAB > 5 Cifre

            //noinspection ConstantConditions (Serve a togliere solamente la sottolineatura fastidiosa)
            if (contoNumeroIBAN < 1000000000000L) {

                String IBANchar = "" + contoNumeroIBAN;
                StringBuilder zero = new StringBuilder("0");

                //Numero Conto Corrente
                contoNumeroIBAN++;


                /*
                 *  Aggiungo uno zero fino a formare una stringa di 12 cifre
                 *  (compreso il numero del conto salvato sul file)
                 *
                 *
                 *  for(int i = IBANchar.length(); i<11; i++)
                 *      zero.append("0");
                 */
                zero.append("0".repeat(Math.max(0, 11 - IBANchar.length())));


                //Aggiunta all'IBAN degli 0 + il numero effettivo del conto
                IBAN += zero;
                IBAN += contoNumeroIBAN;
            } else
                System.out.print("Errore Interno: 14x0"); //Limite massimo IBAN raggiunto


            //Scrittura su File del numero corrente di IBAN
            try {

                //Si scrive il valore sul File
                prop.setProperty("contoNumeroIBAN", String.valueOf(contoNumeroIBAN));


                //save properties to project root folder
                prop.store(new FileOutputStream("resources/config.properties"), "NON MODIFICARE PER NESSUN MOTIVO\nIn caso di " +
                        "modifica, l'azienda produttrice non si assume alcuna responsabilita'.\nPer ulteriori informazioni consultare il manuale " +
                        "fornito insieme al software.\n");

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.print(IBAN);
            return IBAN;
        }
    }
