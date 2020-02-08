import java.io.*;
import java.util.ArrayList;

import Packages.*;

/*
    Main Class
 */
public class ObjectWrite {
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

        System.out.println("Dimensione dell'ArrayList: "+ contiCorrentiArray.size());

        contoCorrente c1 = new contoCorrente("IDNALFO", 14, 1);
        contoCorrente c2 = new contoCorrente("IDNALFO", 14, 3);

        contiCorrentiArray.add(c1);

        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream fileObj = new ObjectOutputStream(fileOut);

        fileObj.writeObject(contiCorrentiArray);
        fileObj.close();
        fileOut.close();
    }

}