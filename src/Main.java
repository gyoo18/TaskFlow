import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList; // Import the ArrayList class

public class Main {

    public static void main(String[] args) {
        try {
            initialisation();
            boucle();
        } catch (Exception e) {
            System.err.println("\n[ERREUR CRITIQUE] Arrêt de l'initialisation du programme.");
            System.err.println("Cause : " + e.getMessage());
        } finally {
            destruction();
        }
    }

    private static void initialisation() throws Exception {
        // Si Charger rencontre une anomalie, elle bloque tout et remonte ici
        ArrayList<Tache> tachesChargees = Charger.chargerTaches();

        for (Tache t : tachesChargees) {
            Donnees.ajouterTache(t);
        }

        InterfaceUtilisateur.initier();
    }

    private static void boucle() {
        InterfaceUtilisateur.boucleBlocante();
    }

    private static void destruction() {
        InterfaceUtilisateur.detruire();
    }
}
