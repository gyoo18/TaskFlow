import java.util.Scanner;

public class MenuPrincipal {
    
    private final Scanner scanner = new Scanner(System.in);

    public void demarrer(){
        boolean continuer = true;

        System.out.println("TASKFLOW - Gestion des tâches\n");

        //TODO: modifier lorsque les fichiers de sauvegarde existeront
        System.out.println("Aucun fichier de données trouvé."); 
        System.out.println("L'application démarre avec une liste vide\n");

        while (continuer){
            afficherMenu();

            System.out.print("\nVotre choix : ");
            String choix = scanner.nextLine().trim();
            System.out.println();

            // TODO: remplacer afficherFonctionnalite par les vraies fonctions
            switch (choix) {
                case "1":
                    afficherFonctionnalite("Ajouter une tâche");
                    break;
                
                case "2":
                    afficherFonctionnalite("Afficher les tâches");
                    break;

                case "3":
                    afficherFonctionnalite("Marquer une tâche comme terminée");
                    break;

                case "4":
                    afficherFonctionnalite("Modifier la priorité d'une tâche");
                    break;

                case "5":
                    afficherFonctionnalite("Gérer l'échéance d'une tâche");
                    break;

                case "6":
                    afficherFonctionnalite("Sauvegarder les données");
                    break;

                //TODO: ajouter la vraie fonctionnalité pour quitter
                case "0":
                    continuer = false;
                    System.out.println("Fermeture de TASKFLOW.");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez chosir une option de 0 à 6.");
                    break;

            }

            System.out.println();
        }

        scanner.close();
    }

    public void afficherMenu() {
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Afficher les tâches");
        System.out.println("3. Marquer une tâche comme terminée");
        System.out.println("4. Modifier la priorité d'une tâche");
        System.out.println("5. Gérer l'échéance d'une tâche");
        System.out.println("6. Sauvegarder les données");
        System.out.println("0. Quitter");
    }

    //TODO: Pour tests. Enlever lorsque toutes les fonctionnalités seront disponibles. 
    private void afficherFonctionnalite(String nom){
        System.out.println("La fonctionnalité \"" + nom + "\" n'est pas encore disponible.");
    }
}
