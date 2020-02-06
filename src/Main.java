import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] Args) {
        List<String> contiBancari = new ArrayList<>();

        /*
            [Gestione file da parte dell'impiegato]

            (Possibile rimozione dell'output o aggiunta di
            eventuali metodi di sicurezza per gli impiegati)

            Il file sarà creato solo alla prima esecuzione
            del programma. Dunque, le rimanenti volte basta verificarne
            la presenza.
         */

        try {
            File contiBancariFile = new File("contiBancari.txt");
            if (contiBancariFile.createNewFile()) {
                System.out.println("File creato: " + contiBancariFile.getName());
            } else {
                System.out.println("Il File esiste già.");
            }
        } catch (IOException e) {
            System.out.println("Errore riscontrato.");
            e.printStackTrace();
        }
    }
}
