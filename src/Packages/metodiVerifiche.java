package Packages;

import java.util.Scanner;

public class metodiVerifiche {
    public static String verificaIBAN() {
        Scanner input = new Scanner(System.in);
        String IBAN = "";

        boolean conferma = true;

        if(cilindrata>=50 && cilindrata<=80)
            System.out.println("\u001B[32m" + "-Ricorda targhe 50cc-80cc hanno solo 6 caratteri - AA1111 " + "\u001B[30m");
        else
            System.out.println("\u001B[32m" + "-Ricorda targhe superiori a 80cc hanno 7 caratteri - AA111AA " + "\u001B[30m");

        while (conferma) {
            targa = input.nextLine();

            boolean spazio = true;
            for (int i = 0; i < targa.length(); i++) {
                if (targa.charAt(i) == ' ')
                    spazio = false;
            }
            if (!spazio) {
                targa = targa.replace(" ", "");
                System.out.println("\u001B[32m" + "La targa senza gli spazi è uguale a " + targa + "\u001B[30m");
            }


            boolean conferma1 = true;
            boolean conferma2 = true;

            //se si tratta di veicoli con cilindrata compresa tra 50 e 80
            if(cilindrata>=50 && cilindrata<=80){
                //verifico che la targa non sia troppo lunga
                if (targa.length() > 6) {
                    targa = targa.substring(0, 6);
                    System.out.println("\u001B[32m" + "-La targa è stata troncata a 6 caratteri- " + targa + "\u001B[30m");
                }

                //verifico che la targa non sia troppo corta
                else if (targa.length() < 6) {
                    System.out.println("\u001B[31m" + "-La targa inserita non era valida perchè troppo corta (con questa cilindrata), reinserisci la targa...-" + "\u001B[30m");
                    conferma1 = false;
                    conferma2 = false;
                }
            }

            //per tutti gli altri veicoli
            else {
                //verifico che la targa non sia troppo lunga
                if (targa.length() > 7) {
                    targa = targa.substring(0, 7);
                    System.out.println("\u001B[32m" + "-La targa è stata troncata a 7 caratteri- " + targa + "\u001B[30m");
                }

                //verifico che la targa non sia troppo corta
                else if (targa.length() < 7) {
                    System.out.println("\u001B[31m" + "-La targa inserita non era valida perchè troppo corta, reinserisci la targa...-" + "\u001B[30m");
                    conferma1 = false;
                    conferma2 = false;
                }
            }
            //se si tratta di un veicolo di cilindrata comprese fra 50 e 80
            if(cilindrata>=50 && cilindrata<=80 && targa.length() == 6){
                for (int i = 0; i < targa.length() && conferma1 && conferma2; i++) {
                    if (i < 2) {
                        if (!Character.isLetter(targa.charAt(i))) {
                            conferma1 = false;
                        }
                    }

                    if (i >= 2) {
                        if (Character.isLetter(targa.charAt(i))) {
                            conferma2 = false;
                        }
                    }
                }
            }

            //se è una vettura con un'altra cilindrata verifico che la targa soddisfi la giusta quantità  di lettere e numeri nelle posizioni adeguate
            else if (cilindrata>80 && targa.length() == 7) {
                for (int i = 0; i < targa.length() && conferma1 && conferma2; i++) {
                    if (i < 2 || i > 4) {
                        if (!Character.isLetter(targa.charAt(i))) {
                            conferma1 = false;
                        }
                    }

                    if (i >= 2 && i <= 4) {
                        if (Character.isLetter(targa.charAt(i))) {
                            conferma2 = false;
                        }
                    }
                }
            }

            //verifico se la targa è già stata assegnata ad un veicolo
            String confronta = targa.toUpperCase();
            if (verificaPresenzaTarga(confronta, slot)==false){
                System.out.println("\u001B[31m" + "-La targa è già presente all'interno del garage-" + "\u001B[30m");
                conferma1 = false;
                conferma2 = false;
            }

            //se le lettere e i numeri sono presenti in posizioni sbagliate
            if (!conferma1 || !conferma2)
                System.out.println("\u001B[31m" + "-La targa è incorretta (ricorda la cilindrata)-" + "\u001B[30m" +  "\n- Targa : ");

                //verifico se la targa è già scritta in maiuscolo oppure no ed eventualmente la rendo tale
            else if (conferma1 && conferma2) {
                conferma = false;

                confronta = targa;
                targa = targa.toUpperCase();
                if (!confronta.equals(targa))
                    System.out.println("\u001B[32m" + "La targa era corretta ma le lettere erano minuscole - la nuova targa è " + targa + "\u001B[30m");
            }

        }

        return IBAN;
    }

    public static float verificaSaldo() {
        float saldo=0;
        return saldo;
    }
    public static float verificaInteresse() {
        float interesse=0;
        return interesse;
    }

    public static String verificaTipoConto(){
        String tipoConto="";
        return tipoConto;
    }
}
