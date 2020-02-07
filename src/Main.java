import Packages.*;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] Args) throws Exception, ExceptionInInitializerError {
        ArrayList<contoCorrente> contiCorrentiArray = null;
        File file = new File("contiCorrenti");
        if (file.exists()) {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream fileObjIn = new ObjectInputStream(fileIn);

            contiCorrentiArray = (ArrayList<contoCorrente>) fileObjIn.readObject();

            fileObjIn.close();
            fileIn.close();
        } else {
            contiCorrentiArray = new ArrayList<contoCorrente>();
        }

        System.out.println("Size of List at start: "+contiCorrentiArray.size());

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
