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
