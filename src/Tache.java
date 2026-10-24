import java.util.Calendar;

public class Tache {
        public enum Priorite {
            BASSE, NORMALE, ELEVEE
        }

        public enum Statut {
            A_FAIRE, TERMINE
        }

        public long id = System.currentTimeMillis();
        public String titre = "Nouvelle Tâche";
        public Priorite priorite = Priorite.NORMALE;
        public Calendar echeance = Calendar.getInstance();
        public Statut statut = Statut.A_FAIRE;
    }
