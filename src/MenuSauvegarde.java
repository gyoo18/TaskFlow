import java.util.Scanner;

public class MenuSauvegarde{
    
    private final Scanner scanner;

    //Construteur utilise le scanner de MenuPrincipal
    public MenuSauvegarde(Scanner scanner) {
        this.scanner = scanner;
    }



    public void afficher(){
        boolean continuer = true;

        System.out.println("Voulez-vous sauvegarder les changements effectués?\n");
    

        while (continuer){
            afficherMenu();

            System.out.print("\nVotre choix : ");
            String choix = scanner.nextLine().trim();
            System.out.println();

            // TODO: ajouter la fonction effectuer une sauvegarde
            switch (choix) {
                case "1":
                    afficherFonctionnalite("Effectuer la sauvegarde");
                    break;


            //Retour au menu principal
                case "0":
                    continuer = false;
                    System.out.println("Retour vers le menu principal");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez chosir une option entre 0 ou 1.");
                    break;

            }

            System.out.println();
        }

        
    }

    public void afficherMenu() {
        System.out.println("1. Oui");
        System.out.println("0. Non, retourner au menu principal");
    }

    //TODO: Pour tests. Enlever lorsque toutes les fonctionnalités seront disponibles. 
    private void afficherFonctionnalite(String nom){
        System.out.println("La fonctionnalité \"" + nom + "\" n'est pas encore disponible.");
    }
}
