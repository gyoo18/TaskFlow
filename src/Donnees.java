import java.util.ArrayList;
import java.util.List;

public class Donnees {
    private static List<Tache> listeTaches = new ArrayList<>();

    public static void ajouterTache(Tache t){
        listeTaches.add(t);
    }

    public static void avoirListeTaches(Tache[] liste){
        listeTaches.toArray(liste);
    }

    public static Tache[] avoirListeTaches(){
        Tache[] liste = new Tache[listeTaches.size()];
        listeTaches.toArray(liste);
        return liste;
    }
}
