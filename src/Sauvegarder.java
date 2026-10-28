import java.io.File;
import java.io.FileWriter;   
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  

public class Sauvegarder {

    public static void enregistrer(Tache[] taches) throws IOException{
        ArrayList<String> lignesASauvegarder = new ArrayList<>();
        
        for (Tache t : taches) {
            String dateStr = (t.echeance != null) ? new SimpleDateFormat("yyyy-MM-dd").format(t.echeance.getTime()) : "";
            String ligne = t.id + ";" + t.titre + ";" + t.priorite + ";" + dateStr + ";" + t.statut;
            lignesASauvegarder.add(ligne);
        }
        Sauvegarder.ecrire(lignesASauvegarder);
    }

    private static void ecrire(ArrayList<String> liste_ajout) throws IOException{

        //valider que la liste d'ajout n'est pas vide
        if (liste_ajout == null || liste_ajout.isEmpty()){
            throw new IOException("La liste est vide ou nulle.");
        }
        


        File ficherSauvegarde = new File("fichier_sauvegarde.csv");

        //Regarde si le ficher de sauvegarde existe et le crée si ce n'est pas le cas
        if(!(ficherSauvegarde.exists())){
            try {
                ficherSauvegarde.createNewFile();
                
            } catch (IOException e) {
                throw new IOException("Le fichier de sauvegarde n'a pas pu être créé.");
            }
        }

        
        // ajouter les listes dans le fichier de sauvegarde
       try (FileWriter myWriter = new FileWriter(ficherSauvegarde, false)) {
            for (String ajout : liste_ajout) {
                // On écrit l'élément suivi d'un retour à la ligne
                myWriter.write(ajout + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new IOException("Une erreur est survenue la sauvegarde n'a pas été complétée");
        }
    }

}
