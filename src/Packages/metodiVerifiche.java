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

    @SuppressWarnings("unused")
    public static float verificaIBAN() {
        return (float) 0;
    }

    public static float verificaSaldo() {
        return (float) 0;
    }

    public static float verificaInteresse() {
        return (float) 0;
    }

    public static String verificaTipoConto() {
        String tipoConto="";
        int scelta=0;
        do {
            System.out.println("Che tipo di conto vuole creare?");
            System.out.println("Conto Deposito:\n  [1] Vincolato;");
            System.out.println("  [2] Non Vincolato;");
            System.out.println("Conto Corrente:\n  [3] a Canone Fisso [Work in Progress];");
            System.out.println("  [4] senza Canone [Work in Progress]");
            System.out.print("➡ ");
            try{
                scelta=Integer.parseInt(input.nextLine());

                if(scelta==1)
                    tipoConto="Conto Deposito Vincolato";

                else if(scelta==2) {
                    tipoConto = "Conto Deposito non Vincolato";
                }
                /*
                else if(scelta==3)
                    tipoConto="Conto Corrente a Canone Fisso";
                else if(scelta==4)
                    tipoConto="Conto Corrente senza Canone";
                */
                else if(scelta==3 || scelta==4)
                    System.out.println("Opzione non disponibile. Ci scusiamo per il disagio! \uD83D\uDE1E\n");

                else System.out.println("Inserisci valore valido");


            }catch (Exception e){
                System.out.println("Valore inserito non valido");
            }
        }while(scelta!=1 &&  scelta!=2 /*&& scelta!=3 && scelta!=4*/);

        return tipoConto;
    }


    public static String verificaNomeCognome(String var) {
        StringBuilder nome;
        exitMethods = false;

        do {
            System.out.print(var + ": ");

            nome = new StringBuilder(input.nextLine());
            if (nome.length() != 0)
                exitMethods = true;
            for (int i = 0; i < nome.length() && exitMethods; i++) {
                if (!Character.isLetter(nome.charAt(i)) && nome.charAt(i) != ' ') {
                    exitMethods = false;
                    System.out.println("Non ci possono essere numeri. Reinserisca...");
                }
            }

        } while (!exitMethods);

        String[] nomeCognome = nome.toString().split(" ");

        nome = new StringBuilder();
        for (int i = 0; i < nomeCognome.length; i++) {
            //di rimane sempre minuscolo
            if(!nomeCognome[i].equals("di"))
                nomeCognome[i] = nomeCognome[i].substring(0,1).toUpperCase() + nomeCognome[i].substring(1).toLowerCase();
            nome.append(nomeCognome[i]).append(" ");
        }

        exitMethods = true;
        return nome.toString();
    }

    public static String verificaCartaID() {
        //System.out.println("cartaID: (CA00000AA)");

        String cartaID;

        do {
            System.out.print("CartaID (CA00000AA o XA0000000): ");
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
        String data;

        int annoReale;
        int meseReale;
        int giornoReale;
        
        do {
            System.out.print(var + " (DD.MM.YYYY): ");

            exitMethods = true;
            data = input.nextLine();

            if(data.length()<10) {
                String giorno;
                try {
                    giorno = "" + data.charAt(data.length() - 9) + data.charAt(data.length() - 8);
                    //noinspection UnusedAssignment
                    meseReale = Integer.parseInt(giorno);
                } catch (Exception e) {
                    data = "0" + data;
                }

                String mese = "";
                try {
                    mese = "" + data.charAt(data.length() - 7) + data.charAt(data.length() - 6);
                    //noinspection UnusedAssignment
                    meseReale = Integer.parseInt(mese);
                } catch (Exception e) {
                    mese = mese.replace(".", ".0");
                    data = data.substring(0, 2) + data.substring(2, 3).replace(".", mese) + data.substring(4);
                }
            }
            if (data.length() != 10) {
                exitMethods = false;
                System.out.println("Il valore inserito non rispetta la lunghezza!");
            }

            for (int i = 0; i < data.length() && exitMethods; i++) {

                //noinspection ConstantConditions
                if ((i == 2) && (i == 5) && (data.charAt(i) == '.')) {
                    exitMethods = false;
                    System.out.println("Ha dimenticato i punti.");
                } else if (Character.isLetter(data.charAt(i))) {
                    exitMethods = false;
                    System.out.println("Non ci possono essere lettere.");
                }
            }
        } while (!exitMethods);

        String anno = "" + data.charAt(data.length() - 4) + data.charAt(data.length() - 3) + data.charAt(data.length() - 2) + data.charAt(data.length() - 1);

            annoReale = Integer.parseInt(anno);
            //System.out.println("anno:"+annoReale);
            String mese = "" + data.charAt(data.length() - 7) + data.charAt(data.length() - 6);
            meseReale = Integer.parseInt(mese);
            //System.out.println("mese:"+meseReale);
            String giorno = "" + data.charAt(data.length() - 10) + data.charAt(data.length() - 9);
            giornoReale = Integer.parseInt(giorno);
            //System.out.println("giorno:"+giornoReale);

            //Calcolo anno bisestile
            boolean bisestile=false;
            if(annoReale%400==0 || (annoReale%4==0 && annoReale%100!=0))
                bisestile=true;
            //Calcolo mese da 30 giorni o 31 giorni
            boolean trentaGiorni = false;
            if(meseReale==4 | meseReale==6 || meseReale==9 || meseReale==11)
                trentaGiorni = true;

            if(meseReale<1 || meseReale>12 || (meseReale==2 && (giornoReale<1 || (bisestile && giornoReale>29) || (!bisestile && giornoReale>28))) || (trentaGiorni && giornoReale>30) || (!trentaGiorni && giornoReale>31)) {
                System.out.println("Sono stati inseriti valori non validi");
                exitMethods = false;
            }

        //Si verifica nel caso della scadenza carta che non sia già scduta
        LocalDateTime time = LocalDateTime.now();
        if (var.equals("Data scadenza della Carta d'Identità")) {
            //System.out.println(time.getYear()+" "+time.getMonthValue()+" "+time.getDayOfMonth());
            if (annoReale < time.getYear() || (annoReale==time.getYear() && meseReale < time.getMonthValue()) || (annoReale==time.getYear() && meseReale==time.getMonthValue() && giornoReale < time.getDayOfYear())) {
                data = "0";
            }
        }

        //Si verifica che il cliente sia maggiorenne
        else{
            if (time.getYear()-annoReale<18 || (time.getYear()-annoReale==18 && meseReale > time.getMonthValue()) || (time.getYear()-annoReale==18 && meseReale==time.getMonthValue() && giornoReale > time.getDayOfYear())) {
                data = "0";
            }

        }

        exitMethods = true;
        return data;
        }

    public static boolean verificaDatiInseriti() {
        boolean conferma=true;
        int scelta;

        while (exitMethods) {
            System.out.println("È sicuro/a? \n[1] Sì\n[2] No");
            System.out.print(" ➡ ");
            try {
                scelta = Integer.parseInt(input.nextLine());

                if(scelta==1) {
                    exitMethods = false;
                }
                else if(scelta==2) {
                    conferma=false;
                    exitMethods = false;
                }
                else
                    System.out.println("-valore non valido!-");
            } catch (Exception e) {
                System.out.println("-valore non valido!-");
            }
        }
        exitMethods = true;
        return !conferma;
    }

    public static String verificaSesso () {
        System.out.println("Sesso:");

        String cartaSesso = "";
        int posizione = 0;
        do {
            System.out.println("[1] Maschio");
            System.out.println("[2] Femmina");
            System.out.print(" ➡ ");
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
        nome = nome.toUpperCase();
        cognome = cognome.toUpperCase();
        comune = comune.toUpperCase();

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
            System.out.println("Conferma il codice fiscale generato automaticamente? " + "➡ " + temp + "" + ":\n[1] Sì\n[2] No");
            System.out.print(" ➡ ");
            try {
                scelta = Integer.parseInt(input.nextLine());

                if(scelta==1) {
                    conferma=true;
                    exitMethods = false;
                    codiceFiscale = String.valueOf(temp);
                }
                else if(scelta==2) {
                    conferma=false;
                    exitMethods = false;
                    System.out.println("Inserisca il codie fiscale personalmente:");
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
        int position=(int)numero;
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
            System.out.print("Cittadinanza (italiana): ");
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

    //Via 0, Viale 0, Rotonda 0, Piazza 0, Vicolo 0
    public static String verificaIndirizzoResidenza () {
        String indirizzoResidenza = "";
        while (exitMethods) {
            try {
                System.out.print("Indirizzo Residenza (Via/Viale/Rotonda/Piazza/Vicolo Roma): ");
                indirizzoResidenza = input.nextLine();
                if(indirizzoResidenza.charAt(0) == 'V') {
                    if(indirizzoResidenza.charAt(1) == 'i') {
                        if(indirizzoResidenza.charAt(2) == 'a') {
                            if(indirizzoResidenza.charAt(3) == ' ') {
                                exitMethods = false;
                                break;
                            }
                            else if(indirizzoResidenza.charAt(3) == 'l') {
                                if(indirizzoResidenza.charAt(4) == 'e') {
                                    exitMethods = false;
                                    break;
                                }
                            }
                        }
                        else if(indirizzoResidenza.charAt(2) == 'c') {
                            if(indirizzoResidenza.charAt(3) == 'o') {
                                if(indirizzoResidenza.charAt(4) == 'l') {
                                    if(indirizzoResidenza.charAt(5) == 'o') {
                                        exitMethods = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }
                else if(indirizzoResidenza.charAt(0) == 'R') {
                    if(indirizzoResidenza.charAt(1) == 'o') {
                        if(indirizzoResidenza.charAt(2) == 't') {
                            if(indirizzoResidenza.charAt(3) == 'o') {
                                if(indirizzoResidenza.charAt(4) == 'n') {
                                    if(indirizzoResidenza.charAt(5) == 'd') {
                                        if(indirizzoResidenza.charAt(6) == 'a') {
                                            exitMethods = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if(indirizzoResidenza.charAt(0) == 'P') {
                    if(indirizzoResidenza.charAt(1) == 'i') {
                        if(indirizzoResidenza.charAt(2) == 'a') {
                            if(indirizzoResidenza.charAt(3) == 'z') {
                                if(indirizzoResidenza.charAt(4) == 'z') {
                                    if(indirizzoResidenza.charAt(5) == 'a') {
                                        exitMethods = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                else if(exitMethods) {
                    System.out.println("Errore Interno: 12x4 | Verificare le maiuscole!"); //L'utente non ha inserito bene i dati
                }
            } catch (Exception e) {
                indirizzoResidenza = " ";
                System.out.print("Indirizzo non valido!");
            }
        }
        exitMethods = true;
        return indirizzoResidenza;
    }


    public static int verificaNumeroCivico () {
        int numeroCivico = 0;
        while (exitMethods) {
            System.out.print("Numero Civico: ");
            try {
                numeroCivico = Integer.parseInt(input.nextLine());
                if (numeroCivico >= 0 && numeroCivico <=100) {
                    exitMethods = false;
                }
                else
                    System.out.println("Numero non valido");
            } catch (Exception e) {
                System.out.print("Indirizzo Civico non valido!");
            }
        }
        exitMethods = true;
        return numeroCivico;
    }

    public static String verificaComune (String var) {

        String comune = null;
        while (exitMethods) {
            System.out.print("Comune " + var + " (Es. Monfalcone): ");
            try {
                comune = input.nextLine();
                exitMethods = false;
            } catch (Exception e) {
                System.out.print("Comune Residenza non valido!");
            }

            boolean exist = false;
            try {
                Scanner file = new Scanner(new File("comuni.txt"));
                assert comune != null;
                comune = comune.toUpperCase();
                String[] nomeComune = comune.split(" ");
                while (file.hasNext() && !exist) {
                    String riga = file.nextLine();
                    String[] riga2 = riga.split("\t");
                    String[] riga3 = riga2[1].split(" ");

                    if (Arrays.equals(nomeComune, riga3)) {
                        exist = true;
                    }
                }
            } catch (Exception ignored) {}

            if(!exist)
                exitMethods=true;
        }
        exitMethods = true;
        return comune;
    }

    public static String verificaStatoResidenza () {
        int scelta;
        String statoResidenza = "";
        while (exitMethods) {
            System.out.println("Stato Residenza: \n[1] Italia \n[2] Altro ");
            System.out.print(" ➡ ");
            try {
                scelta = Integer.parseInt(input.nextLine());
                if(scelta==1) {
                    statoResidenza="Italia";
                    exitMethods = false;
                }
                else if(scelta==2) {
                    statoResidenza="Altro";
                    exitMethods = false;
                }
                else
                    System.out.println("Scelta non valida!");
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
                CAP = Integer.parseInt(input.nextLine());
                exitMethods = false;
                if(CAP<10000 || CAP>99999) {
                    exitMethods = true;
                    System.out.println("CAP non valido!");
                }
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
            } catch (Exception ignored) {}

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
            for(int i = IBANchar.length(); i<11; i++)
                zero.append("0");


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

        System.out.println(" e il suo IBAN è: " + IBAN + "\n");
        return IBAN;
    }


    public static void verificaDatiInseritiFinale(infoCliente cliente) {
        int scelta = 0;
        menu:
        do {
            boolean conferma = true;
            System.out.println("\n|| Inserisca il numero corrispondente ai possibili dati errati e/o 0 per terminare\n||");
            System.out.println("|| [0] Conferma Dati e Salva");
            System.out.println("|| [1] Nome: " + cliente.getNome());
            System.out.println("|| [2] Cognome: " + cliente.getCognome());
            System.out.println("|| [3] CartaID: " + cliente.getCartaID());
            System.out.println("|| [4] Scadenza carta: " + cliente.getCartaScadenza());
            System.out.println("|| [5] Data di nascita: " + cliente.getDataDiNascita());
            System.out.println("|| [6] Sesso: " + cliente.getSesso());
            System.out.println("|| [7] Comune di nascita: " + cliente.getComuneNascita());
            System.out.println("|| [8] Codice fiscale: " + cliente.getCodiceFiscale());
            System.out.println("|| [9] Cittadinanza: " + cliente.getCittadinanza());
            System.out.println("|| [10] Stato di residenza: " + cliente.getStatoResidenza());
            System.out.println("|| [11] Comune di residenza: " + cliente.getComuneResidenza());
            System.out.println("|| [12] Indirizzo di residenza: " + cliente.getIndirizzoResidenza());
            System.out.println("|| [13] Numero civico: " + cliente.getNumeroCivico());
            System.out.println("|| [14] CAP di residenza: " + cliente.getCapResidenza());
            System.out.print(" ➡ ");

            while (conferma) {
                try {
                    scelta = Integer.parseInt(input.nextLine());

                    if (scelta >= 0 && scelta <= 14) {
                        conferma=false;
                    } else
                        System.out.println("-valore non valido-");

                } catch (Exception e) {
                    System.out.println("Valore non valido");
                }
            }

            if(verificaDatiInseriti()) {
                scelta=333;
                //noinspection UnnecessaryLabelOnContinueStatement
                continue menu;
            }

                switch (scelta) {
                    //Esci
                    case 0:
                        scelta = 0;
                        System.out.print("I suoi dati sono stati registrati");
                        break;

                    //Nome
                    case 1:
                        cliente.setNome(verificaNomeCognome("Nome"));
                        break;

                    //Cognome
                    case 2:
                        cliente.setCognome(verificaNomeCognome("Cognome"));
                        break;

                    //ID Carta
                    case 3:
                        cliente.setCartaID(verificaCartaID());
                        break;

                    //Data Scadenza Carta
                    case 4:
                        cliente.setCartaScadenza(verificaData("Data scadenza della Carta d'Identità"));
                        break;

                    //Data di Nascita
                    case 5:
                        cliente.setDataDiNascita(verificaData("Data di nascita"));
                        break;

                    //Sesso
                    case 6:
                        cliente.setSesso(verificaSesso());
                        break;

                    case 7:

                        //Codice Fiscale
                    case 8:
                        cliente.setCodiceFiscale(verificaCodiceFiscale(cliente.getNome(), cliente.getCognome(), cliente.getDataDiNascita(), cliente.getSesso(), cliente.getComuneResidenza()));
                        break;

                    //Cittadinanza
                    case 9:
                        cliente.setCittadinanza(verificaCittadinanza());
                        break;

                    //Stato di Residenza
                    case 10:
                        cliente.setStatoResidenza(verificaStatoResidenza());
                        break;

                    //Comune di Residenza
                    case 11:
                        cliente.setComuneResidenza(verificaComune("Residenza"));
                        break;

                    //Indirizzo di Residenza
                    case 12:
                        cliente.setIndirizzoResidenza(verificaIndirizzoResidenza());
                        break;

                    //Numero Civico
                    case 13:
                        cliente.setNumeroCivico(verificaNumeroCivico());
                        break;

                    //CAP di Residenza
                    case 14:
                        cliente.setCapResidenza(verificaCapResidenza());
                        break;

                    case 333:
                        break;

                }
        }while(scelta!=0);
    }
}
