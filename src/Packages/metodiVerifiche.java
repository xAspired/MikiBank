package Packages;

//Librerie java.io

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

//Librerie java.util

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


    public static void verificaDatiInseritiFinale(String nome, String cognome) {
        boolean conferma=true;
        int scelta=1;
        while (exitMethods) {
            System.out.println("Confermi i dati inseriti:\n1-SI\n2-NO");
            try {
                scelta = input.nextInt();
                if(scelta==1) {
                    conferma=true;
                    exitMethods = false;
                }
                else if(scelta==2) {
                    conferma=false;
                    exitMethods = false;
                }
                else
                    System.out.println("valore non valido!");
            } catch (Exception e) {
                System.out.print("valore non valido!");
            }
        }
        exitMethods = true;
        if(!conferma) {
            do {
                System.out.println("Inserisci il numero dei dati errati e 0 per terminare\n1-nome:"+nome+"\n2-cognome");
                while (exitMethods) {
                    System.out.println("Confermi il dato inserito:\n1-SI\n2-NO");
                    try {
                        scelta = input.nextInt();
                        if(scelta>0 && scelta<3){
                        exitMethods = false;
                        }
                        else
                            System.out.println("Valore non valido!");
                    } catch (Exception e) {
                        System.out.print("Valore non valido!");
                    }
                }
                exitMethods = true;
                switch (scelta) {
                    //esci
                    case 0:
                        scelta=0;
                        break;

                    //nome
                    case 1:
                        nome = verificaNomeCognome("Nome");
                }
            }while(scelta!=0);
        }
    }

        public static String verificaNomeCognome(String var) {
        System.out.print(var + ": ");
        String nome;
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

        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();

        exitMethods = true;
        return nome;
    }

    public static String verificaCartaID() {
        //System.out.println("cartaID: (CA00000AA)");

        String cartaID;

        do {
            System.out.print("CartaID (CA00000AA o XA0000000):");
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
        System.out.print(var + "(DD.MM.YYYY): ");

        String data;

        int annoReale;
        int meseReale;
        int giornoReale;
        
        do {
            exitMethods = true;
            data = input.nextLine();

            if (data.length() != 10) {
                exitMethods = false;
                System.out.println("Il valore inserito non rispetta la lunghezza!");
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
            String anno = "" + data.charAt(data.length() - 4) + data.charAt(data.length() - 3) + data.charAt(data.length() - 2) + data.charAt(data.length() - 1);
            annoReale = Integer.parseInt(anno);
            //System.out.println("anno:"+annoReale);
            String mese = "" + data.charAt(data.length() - 7) + data.charAt(data.length() - 6);
            meseReale = Integer.parseInt(mese);
            //System.out.println("mese:"+meseReale);
            String giorno = "" + data.charAt(data.length() - 10) + data.charAt(data.length() - 9);
            giornoReale = Integer.parseInt(giorno);
            //System.out.println("giorno:"+giornoReale);

            //calcolo anno bisestile
            boolean bisestile=false;
            if(annoReale%400==0 || (annoReale%4==0 && annoReale%100!=0))
                bisestile=true;
            //calcolo mese da 30 giorni o 31 giorni
            boolean trentaGiorni = false;
            if(meseReale==4 | meseReale==6 || meseReale==9 || meseReale==11)
                trentaGiorni = true;

            if(meseReale<1 || meseReale>12 || (meseReale==2 && (giornoReale<1 || (bisestile && giornoReale>29) || (!bisestile && giornoReale>28))) || (trentaGiorni && giornoReale>30) || (!trentaGiorni && giornoReale>31)) {
                System.out.println("Sono stati inseriti valori non validi");
                exitMethods = false;
            }
        } while (!exitMethods);


        //si verifica nel caso della scadenza carta che non sia già scduta
        LocalDateTime time = LocalDateTime.now();
        if (var.equals("Data scadenza carta")) {
            //System.out.println(time.getYear()+" "+time.getMonthValue()+" "+time.getDayOfMonth());
            if (annoReale < time.getYear() || (annoReale==time.getYear() && meseReale < time.getMonthValue()) || (annoReale==time.getYear() && meseReale==time.getMonthValue() && giornoReale < time.getDayOfYear())) {
                data = "0";
            }
        }

        //si verifica che il cliente sia maggiorenne
        else{
            if (time.getYear()-annoReale<18 || (time.getYear()-annoReale==18 && meseReale > time.getMonthValue()) || (time.getYear()-annoReale==18 && meseReale==time.getMonthValue() && giornoReale > time.getDayOfYear())) {
                data = "0";
            }
        }

        exitMethods = true;
        return data;
        }

        public static String verificaSesso () {
            System.out.println("Sesso:");

            String cartaSesso = "";
            int posizione = 0;
            do {
                System.out.println("[1] Maschio;");
                System.out.println("[2] Femmina;");

                System.out.print("Inserisci un valore:");
                do {
                    try {
                        posizione = Integer.parseInt(input.nextLine());
                        if (posizione == 1 || posizione == 2) {
                            exitMethods = false;
                        } else System.out.println("Il valore inserito non è valido - numero sbagliato;");
                    } catch (Exception e) {
                        System.out.println("Il valore inserito non è valido;");
                    }
                } while (exitMethods);
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
                            System.out.println("Il valore inserito non è valido!");
                    }
                else System.out.println("Il valore inserito non è valido!");


            } while (!exitMethods);

            exitMethods = true;
            return cartaSesso;
        }

    public static String verificaCodiceFiscale (String nome, String cognome, String dataDiNascita, String sesso, String comune){
        System.out.println("codice fiscale:");

        nome = nome.toUpperCase();
        cognome = cognome.toUpperCase();
        comune = comune.toUpperCase();

        System.out.println(nome +" "+cognome+" "+dataDiNascita+" "+sesso+" "+comune);

        String codiceFiscale = "";

        int conta = 0;
        StringBuilder temp = new StringBuilder();

        //cognome
        StringBuilder temp2 = new StringBuilder();
        cognome = cognome.replaceAll("\\s+","");
        for(int i=0; i<cognome.length(); i++){
            if(temp2.length() < 3 && cognome.charAt(i) != 'A' && cognome.charAt(i) != 'E' && cognome.charAt(i) != 'I' && cognome.charAt(i) != 'O' && cognome.charAt(i) != 'U'){
                temp2.append(cognome.charAt(i));
            }
        }

        if(temp2.length() < 3) {
            for(int i=0; i<cognome.length(); i++){
                if(temp2.length() < 3 && (cognome.charAt(i) == 'A' || cognome.charAt(i) == 'E' || cognome.charAt(i) == 'I' || cognome.charAt(i) == 'O' || cognome.charAt(i) == 'U')){
                    temp2.append(cognome.charAt(i));
                }
            }
        }

        if(temp2.length() < 3){
            while(temp2.length() < 3){
                temp2.append("X");
            }
        }
        temp.append(temp2);

        //nome
        nome = nome.replaceAll("\\s+","");
        int numCons = 0;
        for(int i=0; i<nome.length(); i++){
            if(nome.charAt(i) != 'A' && nome.charAt(i) != 'E' && nome.charAt(i) != 'I' && nome.charAt(i) != 'O' && nome.charAt(i) != 'U'){
                numCons++;
            }
        }
        temp2 = new StringBuilder();
        if(numCons >= 4){
            for(int i=0; i<nome.length(); i++){
                if(temp2.length() < 3 && nome.charAt(i) != 'A' && nome.charAt(i) != 'E' && nome.charAt(i) != 'I' && nome.charAt(i) != 'O' && nome.charAt(i) != 'U'){
                    if(conta != 1){
                        temp2.append(nome.charAt(i));
                    }
                    conta++;
                }
            }
        }else{
            for(int i=0; i<nome.length(); i++){
                if(temp2.length() < 3 && nome.charAt(i) != 'A' && nome.charAt(i) != 'E' && nome.charAt(i) != 'I' && nome.charAt(i) != 'O' && nome.charAt(i) != 'U'){
                    temp2.append(nome.charAt(i));
                }
            }
        }


        if(temp2.length() < 3) {
            for(int i=0; i<nome.length(); i++){
                if(temp2.length() < 3 && (nome.charAt(i) == 'A' || nome.charAt(i) == 'E' || nome.charAt(i) == 'I' || nome.charAt(i) == 'O' || nome.charAt(i) == 'U')){
                    temp2.append(nome.charAt(i));
                }
            }
        }

        if(temp2.length() < 3){
            while(temp2.length() < 3){
                temp2.append("X");
            }
        }
        temp.append(temp2);

        //anno
        for (int i = dataDiNascita.length() - 2; i < dataDiNascita.length() && exitMethods; i++) {
            temp.append(dataDiNascita.charAt(i));
        }
        //System.out.println("2" + temp);

        //mese
        StringBuilder mese = new StringBuilder();
        for (int i = dataDiNascita.length() - 7; i < dataDiNascita.length() - 5 && exitMethods; i++) {
            mese.append(dataDiNascita.charAt(i));
        }

        switch (mese.toString()) {
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
                temp.append("H");
                break;
            case "07":
                temp.append("L");
                break;
            case "08":
                temp.append("M");
                break;
            case "09":
                temp.append("P");
                break;
            case "10":
                temp.append("R");
                break;
            case "11":
                temp.append("S");
                break;
            case "12":
                temp.append("T");
                break;
        }
        //giorno
        String giorno = dataDiNascita.charAt(0) + "" + dataDiNascita.charAt(1);

        if(sesso.equals("M"))

            temp.append(giorno);
        else {
            int temp1 = Integer.parseInt(giorno);
            temp1 += 40;
            temp.append(temp1);
        }


        //codice comune
        boolean exist = false;
        try{
            Scanner file = new Scanner(new File("comuni.txt"));
            comune = comune.toUpperCase();
            String[] nomeComune = comune.split(" ");
            while(file.hasNext() && !exist) {
                String riga = file.nextLine();
                String[] riga2 = riga.split("\t");
                String[] riga3 = riga2[1].split(" ");

                if(Arrays.equals(nomeComune, riga3)){
                    temp.append(riga2[0]);
                    exist = true;
                }
            }
        }
        catch(Exception ignored){}

        //caratteri
        float temp3 = 0;
        //codice di controllo
        for (int i=0; i<temp.length(); i++){
            //caratteri posizioni pari
            if(i%2!=0) {
                temp3 += verificaAlfabetoPari(temp.charAt(i));
            }
            //caratteri posizioni dispari
            else {
                temp3 += verificaAlfabetoDispari(temp.charAt(i));
            }
        }
        //System.out.println(temp);
        temp3=temp3%26;
        temp.append(verificaCodiceControllo(temp3));
        temp = new StringBuilder(temp.toString().toUpperCase());


        boolean conferma=true;
        int scelta;

        while (exitMethods) {
            System.out.println("Confermi il dato generato automaticamente" + "(" + temp + ")" + ":\n1-SI\n2-NO");
            try {
                scelta = Integer.parseInt(input.nextLine());

                if(scelta==1) {
                    conferma=true;
                    exitMethods = false;
                }
                else if(scelta==2) {
                    conferma=false;
                    exitMethods = false;
                    System.out.println("Inserisci il codie fiscale personalmente:");
                }
                else
                    System.out.println("-valore non valido!-");
            } catch (Exception e) {
                System.out.println("-valore non valido!-");
            }
        }

        if(!conferma)
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

                for (int i = codiceFiscale.length() - 5; i < codiceFiscale.length() && exitMethods; i++) {

                    if (i > 11 && i < 15 && Character.isLetter(codiceFiscale.charAt(i))) {
                        exitMethods = false;
                        System.out.println("Ci sono lettere in posizioni errate.");
                    } else if ((i == 11 || i == 15) && !Character.isLetter(codiceFiscale.charAt(i))) {
                        exitMethods = false;
                        System.out.println("Ci sono numeri dove non dovrebbero essere." + codiceFiscale.charAt(i));
                    }
                }

            } while (!exitMethods);

        exitMethods = true;
        return codiceFiscale;
    }

    public static int verificaAlfabetoPari(char lettera){
        switch(lettera) {
            case '0':
            case 'A':
                return 0;
            case '1':
            case 'B':
                return 1;
            case '2':
            case 'C':
                return 2;
            case '3':
            case 'D':
                return 3;
            case '4':
            case 'E':
                return 4;
            case '5':
            case 'F':
                return 5;
            case '6':
            case 'G':
                return 6;
            case '7':
            case 'H':
                return 7;
            case '8':
            case 'I':
                return 8;
            case '9':
            case 'J':
                return 9;
            case 'K':
                return 10;
            case 'L':
                return 11;
            case 'M':
                return 12;
            case 'N':
                return 13;
            case 'O':
                return 14;
            case 'P':
                return 15;
            case 'Q':
                return 16;
            case 'R':
                return 17;
            case 'S':
                return 18;
            case 'T':
                return 19;
            case 'U':
                return 20;
            case 'V':
                return 21;
            case 'W':
                return 22;
            case 'X':
                return 23;
            case 'Y':
                return 24;
            case 'Z':
                return 25;
        }

        return 0;

    }


    public static int verificaAlfabetoDispari(char lettera){
        switch(lettera) {
            case '0':
            case 'A':
                return 1;
            case '1':
            case 'B':
                return 0;
            case '2':
            case 'C':
                return 5;
            case '3':
            case 'D':
                return 7;
            case '4':
            case 'E':
                return 9;
            case '5':
            case 'F':
                return 13;
            case '6':
            case 'G':
                return 15;
            case '7':
            case 'H':
                return 17;
            case '8':
            case 'I':
                return 19;
            case '9':
            case 'J':
                return 21;
            case 'K':
                return 2;
            case 'L':
                return 4;
            case 'M':
                return 18;
            case 'N':
                return 20;
            case 'O':
                return 11;
            case 'P':
                return 3;
            case 'Q':
                return 6;
            case 'R':
                return 8;
            case 'S':
                return 12;
            case 'T':
                return 14;
            case 'U':
                return 16;
            case 'V':
                return 10;
            case 'W':
                return 22;
            case 'X':
                return 25;
            case 'Y':
                return 24;
            case 'Z':
                return 23;
        }

        return 0;

    }

    public static String verificaCodiceControllo(float numero){
        System.out.println(numero);
        int position=(int)numero;
        System.out.println(numero);
        switch(position) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            case 9:
                return "J";
            case 10:
                return "K";
            case 11:
                return "L";
            case 12:
                return "M";
            case 13:
                return "N";
            case 14:
                return "O";
            case 15:
                return "P";
            case 16:
                return "Q";
            case 17:
                return "R";
            case 18:
                return "S";
            case 19:
                return "T";
            case 20:
                return "U";
            case 21:
                return "V";
            case 22:
                return "W";
            case 23:
                return "X";
            case 24:
                return "Y";
            case 25:
                return "Z";
        }

        return "";

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
    //rivedere
        public static String verificaIndirizzoResidenza () {
            String indirizzoResidenza = null;
            while (exitMethods) {
                System.out.print("Indirizzo Residenza: (via roma) ");
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
    //rivedere
        public static String verificaNumeroCivico () {
            String numeroCivico = null;
            while (exitMethods) {
                System.out.println("Numero Civico: ");
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
                System.out.println("Comune Residenza: (Monfalcone)");
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
            int scelta;
            String statoResidenza = "";
            while (exitMethods) {
                System.out.println("Stato Residenza: \n1-ITALIA \n2-ALTRO");
                try {
                    scelta = input.nextInt();
                    if(scelta==1) {
                        statoResidenza="Italia";
                        exitMethods = false;
                    }
                    if(scelta==2) {
                        statoResidenza="Altro";
                        exitMethods = false;
                    }
                    else
                        System.out.println("Stato Residenza non valido!");
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
