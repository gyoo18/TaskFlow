import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Charger {

    private static final String NOM_FICHIER = "fichier_sauvegarde.csv";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static ArrayList<Tache> chargerTaches() {
        ArrayList<Tache> taches = new ArrayList<>();
        File fichierSauvegarde = new File(NOM_FICHIER);

        // Vérifie si le fichier de sauvegarde existe
        if (!fichierSauvegarde.exists()) {
            return taches; // Return liste vide
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichierSauvegarde))) {
            String ligne;

            while ((ligne = br.readLine()) != null) {
                ligne = ligne.trim();
                if (ligne.isEmpty()) continue;

                String[] elements = ligne.split(";", -1);

                if (elements.length >= 5) {
                    Tache tache = new Tache();

                    // Remplissage des attributs de classe Tache
                    tache.id = Long.parseLong(elements[0].trim());
                    tache.titre = elements[1].trim();
                    tache.priorite = Tache.Priorite.valueOf(elements[2].trim());

                    // Conversion de la date String vers Calendar
                    String dateStr = elements[3].trim();
                    if (!dateStr.isEmpty()) {
                        Date date = DATE_FORMAT.parse(dateStr);
                        tache.echeance = Calendar.getInstance();
                        tache.echeance.setTime(date);
                    } else {
                        tache.echeance = null;
                    }

                    tache.statut = Tache.Statut.valueOf(elements[4].trim());

                    taches.add(tache);
                }
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors du chargement des tâches.");
            e.printStackTrace();
        }

        return taches;
    }
}