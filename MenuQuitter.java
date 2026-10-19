import java.util.Scanner;

public class MenuQuitter {
    public void quitter() {
        Scanner scannerQuitterPage = new Scanner(System.in);
        System.out.println("Êtes-vous sûr de vouloir quitter ? (o/n)");
        String reponseQuitter = scannerQuitterPage.nextLine();
        if (reponseQuitter.equalsIgnoreCase("o")) {
            System.out.println("Au revoir !");
            System.exit(0);
        } else if (reponseQuitter.equalsIgnoreCase("n")) {
            System.out.println("Retour au menu principal.");
            //TODO: ajouter code pour revenir au menu principal
        } else {
            System.out.println("Réponse invalide. Veuillez entrer 'o' pour oui ou 'n' pour non.");
        }
    }    
}
