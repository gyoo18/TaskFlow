import java.io.IOException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Locale;
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

    private static final boolean osEstWindows = System.getProperty("os.name").contains("Windows");

    public static void initier(){}

    public static void boucleBlocante(){
        while (!InterfaceUtilisateur.quitter){
            iteration();
        }
    }

    public static void iteration(){
        effacerEcran();
        switch(InterfaceUtilisateur.menu){
            case Menu.PRINCIPAL:        afficherMenuPrincipal(); break;
            case Menu.AJOUTER_TACHE:    afficherAjouterUneTâche(); break;
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

    public static void afficherAjouterUneTâche() {
        //TODO éventuellement en faire sa propre classe
        //Essentiellement un struct
        class Tache{
            public enum Priorite{
                BASSE,
                NORMALE,
                ELEVEE
            }

            public String titre = "Nouvelle Tâche";
            public Priorite priorite = Priorite.NORMALE;
            public Calendar echeance = Calendar.getInstance();
        }

        Tache tache = new Tache();

        int annee = tache.echeance.get(Calendar.YEAR);
        String mois = tache.echeance.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
        int jour = tache.echeance.get(Calendar.DAY_OF_MONTH);
        int heure = tache.echeance.get(Calendar.HOUR_OF_DAY);
        int minute = tache.echeance.get(Calendar.MINUTE);

        while(true){
            effacerEcran();

            System.out.print(
                "TASKFLOW\n"+
                "\n"+
                "Ajouter une tâche\n"+
                "=================\n"+
                "\n"+
                "Titre : [\033[90m\033[3m"+tache.titre+"\033[0m"+"_".repeat(33-tache.titre.length())+"]\n"+
                "Priorité ["+(tache.priorite==Tache.Priorite.BASSE? "(1=Basse),": " 1=Basse ,")+(tache.priorite==Tache.Priorite.NORMALE? "(2=Normale),": " 2=Normale ,")+(tache.priorite==Tache.Priorite.ELEVEE? "(3=Élevée)": " 3=Élevée ")+"] : \n"+
                "Échéance : [\033[90m\033[3m"+annee+"\033[0m] [\033[90m\033[3m"+mois+"\033[0m] [\033[90m\033[3m"+jour+"\033[0m] à [\033[90m\033[3m"+heure+"h\033[0m] [\033[90m\033[3m"+minute+"m\033[0m]\033[6;10H"
            );
        
            String titreRep = scanner.nextLine();
            if (titreRep.length() != 0){
                tache.titre = titreRep;
            }
            System.out.print("\033[6;10H\033[0K"); // Repositionner le curseur et effacer la ligne
            System.out.print(" "+tache.titre+" ]");

            // Lire la priorité
            while(true){
                System.out.print("\033[7;47H"); // Positionne le curseur
                String rep = scanner.nextLine().strip();
                System.out.print("\033[9;1H\033[0K"); //Efface le message d'erreur
                System.out.print("\033[7;1H\033[0K"); // Repositionne le curseur au début de la ligne d'entrée et efface la ligne
                switch(rep){
                    case "1": 
                        System.out.print("Priorité : Basse");
                        tache.priorite = Tache.Priorite.BASSE;
                        break;
                    case "":
                    case "2":
                        System.out.print("Priorité : Normale");
                        tache.priorite = Tache.Priorite.NORMALE;
                        break;
                    case "3":
                        System.out.print("Priorité : Élevée");
                        tache.priorite = Tache.Priorite.ELEVEE;
                        break;
                    default:
                        System.out.print("\033[9;1H\033[31m[ERREUR] Veuillez indiquer un nombre entre 1 et 3\033[0m"); // Affiche un message d'erreur sur la dernière ligne
                        System.out.print("\033[7;47H\033[0K"); //Repositionne le curseur et efface la donnée entrée
                        continue;
                }
                break;
            }
            
            // Lire la date
            // Année
            while(true){
                System.out.print("\033[8;13H"); // Positionne le curseur
                String anneeRep = scanner.nextLine().strip();
                System.out.print("\033[9;1H\033[0K"); //Efface le message d'erreur
                System.out.print("\033[8;1H\033[0K"); // Repositionne le curseur au début de la ligne d'entrée et efface la ligne

                if (anneeRep.length() != 0){
                    try{
                        annee = Integer.parseInt(anneeRep);
                    }catch(NumberFormatException e){
                        System.out.print("Échéance : [\033[90m\033[3m"+annee+"\033[0m] [\033[90m\033[3m"+mois+"\033[0m] [\033[90m\033[3m"+jour+"\033[0m] à [\033[90m\033[3m"+heure+"h\033[0m] [\033[90m\033[3m"+minute+"m\033[0m]");
                        System.out.print("\033[9;1H\033[31m[ERREUR] Veuillez indiquer un nombre entier.\033[0m"); // Affiche un message d'erreur sur la dernière ligne
                        continue;
                    }
                }
                // Sauvegarde la position du curseur   v----v
                System.out.print("Échéance : "+annee+" \033[s[\033[90m\033[3m"+mois+"\033[0m] [\033[90m\033[3m"+jour+"\033[0m] à [\033[90m\033[3m"+heure+"h\033[0m] [\033[90m\033[3m"+minute+"m\033[0m]");
                tache.echeance.set(Calendar.YEAR, annee);
                break;
            }

            // Lire la date
            // Mois
            Calendar caltmp = Calendar.getInstance();
            String[] moisLocale = new String[12];
            caltmp.set(Calendar.MONTH, Calendar.JANUARY   ); moisLocale[0] = caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.FEBRUARY  ); moisLocale[1] = caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.MARCH     ); moisLocale[2] = caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.APRIL     ); moisLocale[3] = caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.MAY       ); moisLocale[4] = caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.JUNE      ); moisLocale[5] = caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.JULY      ); moisLocale[6] = caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.AUGUST    ); moisLocale[7] = caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.SEPTEMBER ); moisLocale[8] = caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.OCTOBER   ); moisLocale[9] = caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.NOVEMBER  ); moisLocale[10]= caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            caltmp.set(Calendar.MONTH, Calendar.DECEMBER  ); moisLocale[11]= caltmp.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
            while(true){
                System.out.print("\033[u\033[1C"); // Repositionne le curseur à la position de sauvegarde et le déplace de 1 à droite
                String moisRep = scanner.nextLine().strip();
                System.out.print("\033[9;1H\033[0K"); //Efface le message d'erreur
                System.out.print("\033[u\033[0K"); // Repositionne le curseur à la position de sauvegarde et efface la ligne

                int moisInt = tache.echeance.get(Calendar.MONTH);
                if (moisRep.length() != 0){
                    boolean estMois = false;
                    for (int i = 0; i < moisLocale.length; i++) {
                        if(moisLocale[i].equals(moisRep)){
                            estMois = true;
                            moisInt = i;
                            break;
                        }
                    }
                    if (estMois){
                        mois = moisRep;
                    }else{
                        System.out.print("[\033[90m\033[3m"+mois+"\033[0m] [\033[90m\033[3m"+jour+"\033[0m] à [\033[90m\033[3m"+heure+"h\033[0m] [\033[90m\033[3m"+minute+"m\033[0m]");
                        System.out.print("\033[9;1H\033[31m[ERREUR] Veuillez indiquer un mois parmis : [ "); // Affiche un message d'erreur sur la dernière ligne
                        for (int i = 0; i < moisLocale.length; i++) {
                            System.out.print(moisLocale[i] + (i<moisLocale.length-1? ", " : " ]\033[0m"));
                        }
                        continue;
                    }
                }
                // Sauvegarde la position du curseur
                //                      v----v
                System.out.print(mois+" \033[s[\033[90m\033[3m"+jour+"\033[0m] à [\033[90m\033[3m"+heure+"h\033[0m] [\033[90m\033[3m"+minute+"m\033[0m]");
                tache.echeance.set(Calendar.MONTH, moisInt);
                break;
            }

            // Lire la date
            // Jour
            int maxJour = tache.echeance.getActualMaximum(Calendar.DAY_OF_MONTH);
            while(true){
                System.out.print("\033[u\033[1C"); // Repositionne le curseur à la position de sauvegarde et le déplace de 1 à droite
                String jourRep = scanner.nextLine().strip();
                System.out.print("\033[9;1H\033[0K"); //Efface le message d'erreur
                System.out.print("\033[u\033[0K"); // Repositionne le curseur à la position de sauvegarde et efface la ligne

                if (jourRep.length() != 0){
                    try{
                        int jourInt = Integer.parseInt(jourRep);
                        if (jourInt < 0 || jourInt > maxJour){
                            throw new NumberFormatException();
                        }
                        jour = jourInt;
                    }catch(NumberFormatException e){
                        System.out.print("[\033[90m\033[3m"+jour+"\033[0m] à [\033[90m\033[3m"+heure+"h\033[0m] [\033[90m\033[3m"+minute+"m\033[0m]");
                        System.out.print("\033[9;1H\033[31m[ERREUR] Veuillez indiquer un nombre entier entre 0 et "+maxJour+"\033[0m"); // Affiche un message d'erreur sur la dernière ligne
                        continue;
                    }
                }
                // Sauvegarde la position du curseur
                //                        v----v
                System.out.print(jour+" à \033[s[\033[90m\033[3m"+heure+"h\033[0m] [\033[90m\033[3m"+minute+"m\033[0m]");
                tache.echeance.set(Calendar.DAY_OF_MONTH, jour);
                break;
            }

            // Lire la date
            // Heure
            int maxHeure = tache.echeance.getActualMaximum(Calendar.HOUR_OF_DAY); // Changement d'heure et autres corrections à tous les cents ans
            while(true){
                System.out.print("\033[u\033[1C"); // Repositionne le curseur à la position de sauvegarde et le déplace de 1 à droite
                String heureRep = scanner.nextLine().strip();
                System.out.print("\033[9;1H\033[0K"); //Efface le message d'erreur
                System.out.print("\033[u\033[0K"); // Repositionne le curseur à la position de sauvegarde et efface la ligne

                if (heureRep.length() != 0){
                    try{
                        int heureInt = Integer.parseInt(heureRep);
                        if (heureInt < 0 || heureInt > maxHeure){
                            throw new NumberFormatException();
                        }
                        heure = heureInt;
                    }catch(NumberFormatException e){
                        System.out.print("[\033[90m\033[3m"+heure+"h\033[0m] [\033[90m\033[3m"+minute+"m\033[0m]");
                        System.out.print("\033[9;1H\033[31m[ERREUR] Veuillez indiquer un nombre entier entre 0 et "+maxHeure+"\033[0m"); // Affiche un message d'erreur sur la dernière ligne
                        continue;
                    }
                }
                // Sauvegarde la position du curseur
                //                       v----v
                System.out.print(heure+" \033[s[\033[90m\033[3m"+minute+"m\033[0m]");
                tache.echeance.set(Calendar.HOUR_OF_DAY, heure);
                break;
            }

            // Lire la date
            // Minute
            while(true){
                System.out.print("\033[u\033[1C"); // Repositionne le curseur à la position de sauvegarde et le déplace de 1 à droite
                String minuteRep = scanner.nextLine().strip();
                System.out.print("\033[9;1H\033[0K"); //Efface le message d'erreur
                System.out.print("\033[u\033[0K"); // Repositionne le curseur à la position de sauvegarde et efface la ligne

                if (minuteRep.length() != 0){
                    try{
                        int minuteInt = Integer.parseInt(minuteRep);
                        if (minuteInt < 0 || minuteInt > 59){
                            throw new NumberFormatException();
                        }
                        minute = minuteInt;
                    }catch(NumberFormatException e){
                        System.out.print("[\033[90m\033[3m"+minute+"m\033[0m]");
                        System.out.print("\033[9;1H\033[31m[ERREUR] Veuillez indiquer un nombre entier entre 0 et 59\033[0m"); // Affiche un message d'erreur sur la dernière ligne
                        continue;
                    }
                }
                System.out.print(minute);
                tache.echeance.set(Calendar.MINUTE, minute);
                break;
            }

            // Confirmer
            System.out.print("\033[10;1H"); // Positionner le curseur
            System.out.print("Enregistrer la nouvelle tâche ? [(o=Oui)| n=Non | a=Annuler ] : ");
            String confirmation = scanner.nextLine().strip().toLowerCase();
            System.out.print("\033[11;1H\033[0K"); //Efface le message d'erreur
            System.out.print("\033[10;1H\033[0K"); // Repositionne le curseur à la position de sauvegarde et efface la ligne

            if (confirmation.equals("o")){
                System.out.println();
                System.out.println("\033[33mLa tâche sera enregistrée\033[0m");
                System.out.println("\033[90m\033[3mVersion de test, la tâche ne sera pas enregistrée.\033[0m");
                scanner.nextLine();
                InterfaceUtilisateur.menu = Menu.PRINCIPAL;
                break;
            }

            if (confirmation.equals("a")){
                System.out.println();
                System.out.println("\033[33mAbandon. La tâche ne sera pas enregistrée\033[0m");
                System.out.println("\033[90m\033[3mVersion de test, la tâche ne sera pas enregistrée.\033[0m");
                scanner.nextLine();
                InterfaceUtilisateur.menu = Menu.PRINCIPAL;
                break;
            }
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

    private static void effacerEcran(){
        try{
            if (InterfaceUtilisateur.osEstWindows){
                new ProcessBuilder("cmd.exe","/c","cls").inheritIO().start().waitFor();
            }else{
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
