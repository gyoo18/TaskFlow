import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

  
            switch (choix) {
                case "1":
                    ArrayList<String> lignesASauvegarder = new ArrayList<>();
                    Tache[] toutesLesTaches = Donnees.avoirListeTaches();
                    
                    for (Tache t : toutesLesTaches) {
                        String dateStr = (t.echeance != null) ? new SimpleDateFormat("yyyy-MM-dd").format(t.echeance.getTime()) : "";
                        String ligne = t.id + ";" + t.titre + ";" + t.priorite + ";" + dateStr + ";" + t.statut;
                        lignesASauvegarder.add(ligne);
                    }
                    //Sauvegarder.ecrire(lignesASauvegarder);
                    
                    continuer = false; 
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

}
