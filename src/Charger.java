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

    // On ajoute "throws Exception" pour propager l'erreur vers le Main
    public static ArrayList<Tache> chargerTaches() throws Exception {
        ArrayList<Tache> taches = new ArrayList<>();
        File fichierSauvegarde = new File(NOM_FICHIER);

        if (!fichierSauvegarde.exists()) {
            return taches; 
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichierSauvegarde))) {
            String ligne;
            int numeroLigne = 0;

            while ((ligne = br.readLine()) != null) {
                numeroLigne++;
                ligne = ligne.trim();
                if (ligne.isEmpty()) continue;

                String[] elements = ligne.split(";", -1);

                // Si le nombre de colonnes est incorrect, on lève une exception directement
                if (elements.length != 5) {
                    throw new IllegalArgumentException("Ligne " + numeroLigne + " : Nombre de colonnes incorrect (attendu 5, trouvé " + elements.length + ").");
                }

                try {
                    Tache tache = new Tache();

                    tache.id = Long.parseLong(elements[0].trim());
                    tache.titre = elements[1].trim();
                    tache.priorite = Tache.Priorite.valueOf(elements[2].trim());

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
                } catch (Exception e) {
                    // On relève l'exception pour stopper l'initialisation et indiquer la ligne
                    throw new RuntimeException("Ligne " + numeroLigne + " : Format de données invalide (" + e.getMessage() + ").");
                }
            }
        }

        return taches;
    }
}
