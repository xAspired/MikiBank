import java.io.*;
import java.util.ArrayList;

import Packages.contiCorrenti;

public class ArrayListExample {
    public static void main(String[] args) throws Exception {

        ArrayList<contiCorrenti> contiCorrentiArray = new ArrayList<>();


        /*
        Si aggiunge a contiCorrentiArray il conto corrente, compreso
        di specifiche intestatari.

        Quindi si crea l'oggetto direttamente dentro al .add
        (l'interno si gestisce con variabili temporanee)
         */
        contiCorrentiArray.add(new contiCorrenti("Mario", "Rossi"));
        contiCorrentiArray.add(new contiCorrenti("Luigi", "Verdi"));


        /*
            Serializzazione
         */
            //Si crea il file di output e l'oggetto di output
            FileOutputStream fileOut = new FileOutputStream("contiCorrenti");
            ObjectOutputStream fileObj = new ObjectOutputStream(fileOut);

            //Scriviamo nel file oggetto l'arraylist
            fileObj.writeObject(contiCorrentiArray);
            fileObj.close();
            fileOut.close();


        /*
            Deserializzazione
         */
            FileInputStream fileIn = new FileInputStream("contiCorrenti");
            ObjectInputStream fileObjIn = new ObjectInputStream(fileIn);

            fileObjIn.close();
            fileIn.close();

        //Verify list data
        for (contiCorrenti conti : contiCorrentiArray) {
            System.out.println(conti);
        }
    }
}