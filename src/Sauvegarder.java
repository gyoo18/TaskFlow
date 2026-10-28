import java.io.File;
import java.io.FileWriter;   
import java.io.IOException;
import java.util.ArrayList;  

public class Sauvegarder {


    public static void ecrire(ArrayList<String> liste_ajout){

        //valider que la liste d'ajout n'est pas vide
        if (liste_ajout == null || liste_ajout.isEmpty()){
            System.out.println("La liste est vide ou nulle.");
            return;
        }
        


        File ficherSauvegarde = new File("fichier_sauvegarde.csv");

        //Regarde si le ficher de sauvegarde existe et le crée si ce n'est pas le cas
        if(!(ficherSauvegarde.exists())){
            try {
                ficherSauvegarde.createNewFile();
                
            } catch (IOException e) {
                System.out.println("Le fichier de sauvegarde n'a pas pu être créé.");
                e.printStackTrace(); // Print error details
            }
        }

        
        // ajouter les listes dans le fichier de sauvegarde
       try (FileWriter myWriter = new FileWriter(ficherSauvegarde, false)) {
            for (String ajout : liste_ajout) {
                // On écrit l'élément suivi d'un retour à la ligne
                myWriter.write(ajout + System.lineSeparator());
            }
            System.out.println("La sauvegarde des tâches a été effectuée");
        } catch (IOException e) {
            System.out.println("Une erreur est survenue la sauvegarde n'a pas été complétée");
            e.printStackTrace();
        }
    }
}
