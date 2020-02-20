package Packages;

//Librerie java.io
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//Librerie java.util
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
        System.out.println(var+":");
        String nome;
        int j = 0;

        do {
            exitMethods = true;
            nome = input.nextLine();
            for (int i = 0; i < nome.length() && exitMethods; i++) {
                if (!Character.isLetter(nome.charAt(i))) {
                    exitMethods = false;
                    System.out.println("Non ci possono essere numeri.");
                }
            }
        } while (exitMethods);

        nome = nome.substring(0,1).toUpperCase() + nome.substring(1,nome.length()).toLowerCase();

        exitMethods=true;
        return nome;
    }

    public static String verificaCartaID() {
        String cartaID;
        String tempCartaID;

        do {
            exitMethods = true;
            cartaID = input.nextLine();
            cartaID = cartaID.toUpperCase();
            tempCartaID=""+cartaID.charAt(0)+cartaID.charAt(1);

            if(cartaID.length()>8) {
                exitMethods = false;
                System.out.println("L' ID della carta inserita è troppo lungo.");
            }

            for (int i = 0; i < cartaID.length() && exitMethods; i++) {
                if(!tempCartaID.equals("CA"))
                    exitMethods=false;
                if (i>1 && i<8 && Character.isLetter(cartaID.charAt(i))) {
                    exitMethods = false;
                    System.out.println("Non ci possono essere lettere nel mezzo.");
                }
                if (i>7 && i<10 && !Character.isLetter(cartaID.charAt(i))) {
                    exitMethods = false;
                    System.out.println("Non ci possono essere numeri nella fine.");
                }
            }
        } while (!exitMethods);

        exitMethods=true;
        return cartaID;
    }

    public static String verificaData() {
        String cartaScadenza;
        System.out.println("Formato:DD.MM.YYYY");
        do {
            exitMethods = true;
            cartaScadenza = input.nextLine();

            if(cartaScadenza.length()!=10) {
                exitMethods = false;
                System.out.println("Il valore inserito non rispetta la lunghezza");
            }

            for (int i = 0; i < cartaScadenza.length() && exitMethods; i++) {

                if (i==2 && i==5 && cartaScadenza.charAt(i)=='.') {
                    exitMethods = false;
                    System.out.println("Hai dimenticato i punti.");
                }
                else if (Character.isLetter(cartaScadenza.charAt(i))) {
                    exitMethods = false;
                    System.out.println("Non ci possono essere lettere.");
                }
            }
        } while (!exitMethods);

        exitMethods=true;
        return cartaScadenza;
    }

    public static String verificaSesso() {
        String cartaSesso="";
        int posizione=0;
        do {
            System.out.println("1-Maschio;");
            System.out.println("2-Femmina;");

            System.out.println("Inserisci un valore:");
            try {
                posizione = input.nextInt();
            }catch(Exception e){
                System.out.println("Il valore inserito non è valido;");
            }
            if(posizione==1 || posizione==2)
                switch(posizione){
                    case 1:
                        cartaSesso="M";
                        break;
                    case 2:
                        cartaSesso="F";
                        break;
                    default:
                        System.out.println("Il valore inserito non è valido;");
                }
            else System.out.println("Il valore inserito non è valido;");


        } while (!exitMethods);

        exitMethods=true;
        return cartaSesso;
    }

    public static String verificaCodiceFiscale(String nome, String cognome, String dataDiNascita) {
        String codiceFiscale;
        do {
            exitMethods = true;
            codiceFiscale = input.nextLine();

            if(codiceFiscale.length()!=16) {
                exitMethods = false;
                System.out.println("Il valore inserito non ha una lunghezza valida");
            }

            for (int i = 0; i < codiceFiscale.length() && exitMethods; i++) {

                if (i==2 && i==5 && codiceFiscale.charAt(i)=='.') {
                    exitMethods = false;
                    System.out.println("Hai dimenticato i punti.");
                }
                else if (Character.isLetter(codiceFiscale.charAt(i))) {
                    exitMethods = false;
                    System.out.println("Non ci possono essere lettere.");
                }
            }
        } while (!exitMethods);

        exitMethods=true;
        return codiceFiscale;
    }




    public static String verificaCittadinanza() {
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

    public static String verificaIndirizzoResidenza() {
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

    public static String verificaNumeroCivico() {
        String numeroCivico = null;
        while (exitMethods) {
            System.out.print("Numero Civico: ");
            try {
                numeroCivico = input.nextLine();
                if(numeroCivico.length() <= 4) {
                    exitMethods = false;
                }
            } catch (Exception e) {
                System.out.print("Indirizzo Civico non valido!");
            }
        }
        exitMethods = true;
        return numeroCivico;
    }

    public static String verificaComuneResidenza() {
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

    public static String verificaStatoResidenza() {
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

    public static int verificaCapResidenza() {
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
            }
            catch (Exception ignored) {}

        }
        //Gestione di eventuali errori per la lettura del File
        catch (IOException ex) {
            ex.printStackTrace();
        }

        /*
         * Il finally racchiude un pezzo di codice che dovra essere eseguito a
         * prescindere dal fatto che il codice nel try abbia generato o meno errori
         */

        finally {
            if (input != null) {
                try {
                    //Bisogna chiudere la lettura del File
                    input.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Numero Conto Corrente (12 Cifre)

        String IBAN = "IT60X";
        IBAN += 10000 + (int)(Math.random() * ((19999 - 10000) + 1)); //Codice ABI
        IBAN += 10000 + (int)(Math.random() * ((19999 - 10000) + 1)); //Codice CAB

        //noinspection SingleStatementInBlock,ConstantConditions
        if(contoNumeroIBAN < 1000000000000L) {
            IBAN += contoNumeroIBAN++; //Numero Conto Corrente
        }
        else
            System.out.print("Errore Interno: 14x0"); //Limite massimo IBAN raggiunto


        //Scrittura su File del numero corrente di IBAN
        try {

            //Si scrive il valore sul File
            prop.setProperty("contoNumeroIBAN", String.valueOf(contoNumeroIBAN));


            //save properties to project root folder
            prop.store(new FileOutputStream("resources/config.properties"), null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.print(IBAN);
        return IBAN;
    }
}
