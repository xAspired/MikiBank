import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
            throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        codiceFiscale c1 = new codiceFiscale("GRJDH8");
        ArrayList<Object> Clienti = new ArrayList<>();

        Cliente c2 = new Cliente("Mario", "Rossi", "CIAO345");
        Clienti.add(c2);

        //SCRIVE l'oggetto alunno in un FILE BIANARIO tramite la serializzazione
        ObjectOutputStream clienti = new ObjectOutputStream(new FileOutputStream("clienti.dat"));


        clienti.writeObject(c2);
        clienti.flush();
        clienti.close();

        //LEGGE l'oggetto alunno salvato nel file, tramite la deserializzazione
        ObjectInputStream fin = new ObjectInputStream(new FileInputStream("clienti.dat"));
        c2 = (Cliente) fin.readObject();

        //Visualizza l'oggetto sul monitor, sfruttando  (implicitamente) il suo metodo toString()
        System.out.println(c2);


    }
}