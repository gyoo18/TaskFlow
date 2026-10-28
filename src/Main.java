import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList; // Import the ArrayList class

/*main qui run 
public class Main{

    public static void main(String[] args) {
        initialisation();
        boucle();
        destruction();
    }

    private static void initialisation(){
        
        ArrayList<Tache> tachesChargees = Charger.chargerTaches();

        for (Tache t : tachesChargees) {
            Donnees.ajouterTache(t);
        }

        InterfaceUtilisateur.initier();
    }

    private static void boucle(){
        InterfaceUtilisateur.boucleBlocante();
    }

    private static void destruction(){
        InterfaceUtilisateur.detruire();
    }
}
*/

    private static void initialisation(){
        
        ArrayList<Tache> tachesChargees = Charger.chargerTaches();

        for (Tache t : tachesChargees) {
            Donnees.ajouterTache(t);
        }

        InterfaceUtilisateur.initier();
    }

public class Main{

    private final static Sauvegarder sauvegarde = new Sauvegarder();

    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<String>();
        test.add("a");
        test.add("1");
        test.add("F");
        test.add("M");
        
        sauvegarde.ecrire(test);
        test.clear();
 
    }

}
