import Packages.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] Args) throws Exception, ExceptionInInitializerError {
        FileInputStream fileIn = new FileInputStream("contiCorrenti");
        ObjectInputStream fileObjIn = new ObjectInputStream(fileIn);
        System.out.print(fileObjIn.readObject());

        ArrayList<contoCorrente> contiCorrentiArray = (ArrayList<contoCorrente>) fileObjIn.readObject();

        fileObjIn.close();
        fileIn.close();


//        int sceltaCliente;
//        Scanner input = new Scanner(System.in);
//        System.out.println("Buongiorno, cosa vuole fare?");
//        System.out.println("[1] Creare un conto");
//        System.out.println("[2] Visualizzare un conto");
//        sceltaCliente = input.nextInt();
//        switch (sceltaCliente) {
//            case 1:
//                //If che verifica che il tipo non abbia già un conto
//
//                creazioneCliente();
//            default:
//                System.out.print("Operazione non valida");
//        }

        contoCorrente c1 = new contoCorrente("IDNALFO", 14, 3);
        contoCorrente c2 = new contoCorrente("IDNALFO", 14, 3);

        contiCorrentiArray.add(c1);



        /*
         * =========================================================
         * Serializzazione
         * =========================================================
         */
        //Si crea il file di output e l'oggetto di output
        FileOutputStream fileOut = new FileOutputStream("contiCorrenti");
        ObjectOutputStream fileObj = new ObjectOutputStream(fileOut);

        //Scriviamo nel file oggetto l'arraylist
        fileObj.writeObject(contiCorrentiArray);
        fileObj.close();
        fileOut.close();




    }


    /*
     * =========================================================
     * Metodi Esterni al Main
     * =========================================================
     */
    private static void creazioneCliente() {
        int numBeneficiari = 1;

        Scanner input = new Scanner(System.in);
        System.out.println("Vuoi creare da solo un conto?");
        System.out.println("[1] Sì  [2] No");
        if(input.nextInt() == 1) {
            System.out.print("Quanti beneficiari Le servono?");
            numBeneficiari = input.nextInt();

        }
    }
}
