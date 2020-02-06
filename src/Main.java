import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] Args) throws IOException, ClassNotFoundException {
        /*
        Questa era una prova. Il codice dell'ultimo file aggiornato è nella cartella "ProveVarie"
         */

        ArrayList<Object> contiBancariArrayIn = new ArrayList<>();

        //SCRIVE l'oggetto alunno in un FILE BIANARIO tramite la serializzazione
        ObjectOutputStream contiBancari = new ObjectOutputStream(new FileOutputStream("contiBancari.dat"));

//        contiBancariArray.add( * Qua dentro aggiungiamo un nuovo conto ogni volta che si crea * );



        //Quando si vuole scrivere qualcosa dentro al file:
        contiBancari.writeObject(contiBancariArrayIn); //Si aggiunge l'arraylist dentro al file (sovrascrive)
        contiBancari.flush();
        contiBancari.close();


    }
}
