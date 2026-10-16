import java.util.Scanner;

public class InterfaceUtilisateur {
    
    private enum Menu {
        PRINCIPAL,
        AJOUTER_TACHE,
        AFFICHER_TACHE,
        TERMINER_TACHE,
        PRIORITE_TACHE,
        ECHEANCE_TACHE,
        SAUVEGARDER,
        QUITTER
    }
    private static Menu menu = Menu.PRINCIPAL;
    private static boolean quitter = false;

    private static final Scanner scanner = new Scanner(System.in);

    public static void initier(){}

    public static void boucleBlocante(){
        while (!InterfaceUtilisateur.quitter){
            iteration();
        }
    }

    public static void iteration(){
        switch(InterfaceUtilisateur.menu){
            case Menu.PRINCIPAL:        afficherMenuPrincipal(); break;
            case Menu.AJOUTER_TACHE:    erreurNonImplemente("Ajouter une Tâche"); break;
            case Menu.AFFICHER_TACHE:   erreurNonImplemente("Afficher une Tâche"); break;
            case Menu.TERMINER_TACHE:   erreurNonImplemente("Terminer une Tâche"); break;
            case Menu.PRIORITE_TACHE:   erreurNonImplemente("Gérer la Priorité d'une Tâche"); break;
            case Menu.ECHEANCE_TACHE:   erreurNonImplemente("Gérer l'Échéance d'une Tâche"); break;
            case Menu.SAUVEGARDER:      erreurNonImplemente("Sauvegarder"); break;
            case Menu.QUITTER:          afficherMenuQuitter(); break;
        }
    }

    public static void detruire(){
        scanner.close();
    }

    public static void afficherMenuPrincipal() {
        boolean continuer = true;

        System.out.println("TASKFLOW - Gestion des tâches\n");

        //TODO: modifier lorsque les fichiers de sauvegarde existeront
        System.out.println("Aucun fichier de données trouvé."); 
        System.out.println("L'application démarre avec une liste vide\n");

        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Afficher les tâches");
        System.out.println("3. Marquer une tâche comme terminée");
        System.out.println("4. Modifier la priorité d'une tâche");
        System.out.println("5. Gérer l'échéance d'une tâche");
        System.out.println("6. Sauvegarder les données");
        System.out.println("0. Quitter");

        while (continuer){
            //afficherMenu();

            System.out.print("\nVotre choix : ");
            String choix = scanner.nextLine().trim();
            System.out.println();

            continuer = false;
            // TODO: remplacer afficherFonctionnalite par les vraies fonctions
            switch (choix) {
                case "1": InterfaceUtilisateur.menu = Menu.AJOUTER_TACHE; break;                
                case "2": InterfaceUtilisateur.menu = Menu.AFFICHER_TACHE; break;
                case "3": InterfaceUtilisateur.menu = Menu.TERMINER_TACHE; break;
                case "4": InterfaceUtilisateur.menu = Menu.PRIORITE_TACHE; break;
                case "5": InterfaceUtilisateur.menu = Menu.ECHEANCE_TACHE; break;
                case "6": InterfaceUtilisateur.menu = Menu.SAUVEGARDER; break;
                //TODO: ajouter la vraie fonctionnalité pour quitter
                case "0": InterfaceUtilisateur.menu = Menu.QUITTER; break;

                default:
                    System.out.println("Choix invalide. Veuillez chosir une option de 0 à 6.");
                    continuer = true;
                    break;

            }

            System.out.println();
        }
    }

    public static void afficherMenuQuitter() {
        InterfaceUtilisateur.quitter = true;
        System.out.println("Fermeture de TASKFLOW.");
    }

    //TODO: Pour tests. Enlever lorsque toutes les fonctionnalités seront disponibles. 
    private static void erreurNonImplemente(String nom){
        System.out.println("La fonctionnalité \"" + nom + "\" n'est pas encore disponible.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InterfaceUtilisateur.menu = Menu.PRINCIPAL;
    }
}
