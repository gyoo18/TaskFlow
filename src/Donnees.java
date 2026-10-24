import java.util.ArrayList;
import java.util.List;

public class Donnees {
    private static List<Tache> listeTaches = new ArrayList<>();

    public static void ajouterTache(Tache t){
        listeTaches.add(t);
    }
}
