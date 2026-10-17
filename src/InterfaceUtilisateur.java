import java.io.IOException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Callable;

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

            public enum Statut{
                A_FAIRE,
                TERMINE
            }

            public long id = System.currentTimeMillis();
            public String titre = "Nouvelle Tâche";
            public Priorite priorite = Priorite.NORMALE;
            public Calendar echeance = Calendar.getInstance();
            public Statut statut = Statut.A_FAIRE;
        }

        Tache tache = new Tache();

        // Préparer les données statiques
        int annee = tache.echeance.get(Calendar.YEAR);
        String mois = tache.echeance.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
        int jour = tache.echeance.get(Calendar.DAY_OF_MONTH);
        int heure = tache.echeance.get(Calendar.HOUR_OF_DAY);
        int minute = tache.echeance.get(Calendar.MINUTE);

        Calendar caltmp = Calendar.getInstance();
        String[] moisLocale = new String[12];{
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
        }

        SousRoutine positionnerCurseurTitre =     () -> {System.out.print(curPos(10,6));};
        SousRoutine positionnerCurseurPriorite =  () -> {System.out.print(curPos(46,7));};
        SousRoutine positionnerCurseurEcheance =  () -> {System.out.print(curPos(13,8));};
        SousRoutine positionnerCurseurErreur =    () -> {System.out.print(curPos(1,9));};
        SousRoutine positionnerCurseurConfirmer = () -> {System.out.print(curPos(1,10));};
        SousRoutine effacerErreur =               () -> {positionnerCurseurErreur.appeler(); System.out.print(EFFACER_LIGNE);};

        // Afficher et recceuillir les données
        while(true){
            effacerEcran();

            //TODO réusiner pour faire un bon système de menu.
            System.out.print(
                "TASKFLOW\n"+
                "\n"+
                "Ajouter une tâche\n"+
                "=================\n"+
                "\n"+
                "Titre : ["+GRIS+ITAL+ tache.titre+"_".repeat(33-tache.titre.length()) +NTRE+"]\n"+
                "Priorité ["+GRIS+ITAL+ (
                    switch (tache.priorite){
                        case BASSE   -> "(1=Basse), 2=Normale , 3=Élevée ";
                        case NORMALE -> " 1=Basse ,(2=Normale), 3=Élevée ";
                        case ELEVEE  -> " 1=Basse , 2=Normale ,(3=Élevée)";
                    }) +NTRE+"] : \n"+
                "Échéance : ["+GRIS+ITAL+ annee +NTRE+"] ["+GRIS+ITAL+ mois +NTRE+"] ["+GRIS+ITAL+ jour +ITAL+"] à ["+GRIS+ITAL+ heure+"h" +NTRE+"] ["+GRIS+ITAL+ minute+"m" +NTRE+"]"
            );
       
            // Lire le titre
            {
                positionnerCurseurTitre.appeler();
                String titreRep = scanner.nextLine();
                if (titreRep.length() != 0){
                    tache.titre = titreRep;
                }
                positionnerCurseurTitre.appeler();
                System.out.print(EFFACER_LIGNE);
                System.out.print(" "+tache.titre+" ]");
            }

            // Lire la priorité
            while(true){
                positionnerCurseurPriorite.appeler();
                System.out.print(EFFACER_LIGNE_RESTE);

                String rep = scanner.nextLine().strip();

                effacerErreur.appeler();
                positionnerCurseurPriorite.appeler();
                System.out.print(EFFACER_LIGNE);

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
                        positionnerCurseurErreur.appeler();
                        System.out.print(ROUGE+"[ERREUR] Veuillez indiquer un nombre entre 1 et 3"+NTRE); // Affiche un message d'erreur sur la dernière ligne
                        continue;
                }
                break;
            }
            
            // Lire la date
            // Année
            while(true){
                positionnerCurseurEcheance.appeler();

                String anneeRep = scanner.nextLine().strip();

                effacerErreur.appeler();
                positionnerCurseurEcheance.appeler();
                System.out.print(EFFACER_LIGNE);

                if (anneeRep.length() != 0){
                    try{
                        annee = Integer.parseInt(anneeRep);
                    }catch(NumberFormatException e){
                        System.out.print("Échéance : ["+GRIS+ITAL+annee+NTRE+"] ["+GRIS+ITAL+mois+NTRE+"] ["+GRIS+ITAL+jour+NTRE+"] à ["+GRIS+ITAL+heure+"h"+NTRE+"] ["+GRIS+ITAL+minute+"m"+NTRE+"]");
                        positionnerCurseurErreur.appeler();
                        System.out.print(ROUGE+"[ERREUR] Veuillez indiquer un nombre entier."+NTRE); // Affiche un message d'erreur sur la dernière ligne
                        continue;
                    }
                }
                // Sauvegarde la position du curseur     v--v
                System.out.print("Échéance : "+annee+" "+CURS+"["+GRIS+ITAL+mois+NTRE+"] ["+GRIS+ITAL+jour+NTRE+"] à ["+GRIS+ITAL+heure+"h"+NTRE+"] ["+GRIS+ITAL+minute+"m"+NTRE+"]");
                tache.echeance.set(Calendar.YEAR, annee);
                break;
            }

            // Lire la date
            // Mois
            while(true){
                System.out.print(CURA+curDX(1));
                String moisRep = scanner.nextLine().strip();
                effacerErreur.appeler();
                System.out.print(CURA+EFFACER_LIGNE);

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
                        System.out.print("["+GRIS+ITAL+mois+NTRE+"] ["+GRIS+ITAL+jour+NTRE+"] à ["+GRIS+ITAL+heure+"h"+NTRE+"] ["+GRIS+ITAL+minute+"m"+NTRE+"]");
                        positionnerCurseurErreur.appeler();
                        System.out.print(ROUGE+"[ERREUR] Veuillez indiquer un mois parmis : [ "); // Affiche un message d'erreur sur la dernière ligne
                        for (int i = 0; i < moisLocale.length; i++) {
                            System.out.print(moisLocale[i] + (i<moisLocale.length-1? ", " : " ]"+NTRE));
                        }
                        continue;
                    }
                }
                // Sauvegarde la position du curseur
                //                        v--v
                System.out.print(mois+" "+CURS+"["+GRIS+ITAL+jour+NTRE+"] à ["+GRIS+ITAL+heure+"h"+NTRE+"] ["+GRIS+ITAL+minute+"m"+NTRE+"]");
                tache.echeance.set(Calendar.MONTH, moisInt);
                break;
            }

            // Lire la date
            // Jour
            // IMPORTANT : Il est nécessaire de lire le mois avant le jour pour connaître le maximum de jours.
            int maxJour = tache.echeance.getActualMaximum(Calendar.DAY_OF_MONTH);
            while(true){
                System.out.print(CURA+curDX(1));
                String jourRep = scanner.nextLine().strip();
                effacerErreur.appeler();
                System.out.print(CURA+EFFACER_LIGNE);

                if (jourRep.length() != 0){
                    try{
                        int jourInt = Integer.parseInt(jourRep);
                        if (jourInt < 0 || jourInt > maxJour){
                            throw new NumberFormatException();
                        }
                        jour = jourInt;
                    }catch(NumberFormatException e){
                        System.out.print("["+GRIS+ITAL+jour+NTRE+"] à ["+GRIS+ITAL+heure+"h"+NTRE+"] ["+GRIS+ITAL+minute+"m"+NTRE+"]");
                        positionnerCurseurErreur.appeler();
                        System.out.print(ROUGE+"[ERREUR] Veuillez indiquer un nombre entier entre 0 et "+maxJour+NTRE); // Affiche un message d'erreur sur la dernière ligne
                        continue;
                    }
                }
                // Sauvegarde la position du curseur
                //                          v--v
                System.out.print(jour+" à "+CURS+"["+GRIS+ITAL+heure+"h"+NTRE+"] ["+GRIS+ITAL+minute+"m"+NTRE+"]");
                tache.echeance.set(Calendar.DAY_OF_MONTH, jour);
                break;
            }

            // Lire la date
            // Heure
            // IMPORTANT : Il est nécessaire de lire l'année/mois/jour pour connaître le maximum d'heures
            int maxHeure = tache.echeance.getActualMaximum(Calendar.HOUR_OF_DAY); // Changement d'heure et autres corrections à tous les cents ans
            while(true){
                System.out.print(CURA+curDX(1));
                String heureRep = scanner.nextLine().strip();
                effacerErreur.appeler();
                System.out.print(CURA+EFFACER_LIGNE);

                if (heureRep.length() != 0){
                    try{
                        int heureInt = Integer.parseInt(heureRep);
                        if (heureInt < 0 || heureInt > maxHeure){
                            throw new NumberFormatException();
                        }
                        heure = heureInt;
                    }catch(NumberFormatException e){
                        System.out.print("["+GRIS+ITAL+heure+"h"+NTRE+"] ["+GRIS+ITAL+minute+"m"+NTRE+"]");
                        positionnerCurseurErreur.appeler();
                        System.out.print(ROUGE+"[ERREUR] Veuillez indiquer un nombre entier entre 0 et "+maxHeure+NTRE); // Affiche un message d'erreur sur la dernière ligne
                        continue;
                    }
                }
                // Sauvegarde la position du curseur
                //                         v--v
                System.out.print(heure+" "+CURS+"["+GRIS+ITAL+minute+"m"+NTRE+"]");
                tache.echeance.set(Calendar.HOUR_OF_DAY, heure);
                break;
            }

            // Lire la date
            // Minute
            while(true){
                System.out.print(CURA+curDX(1));
                String minuteRep = scanner.nextLine().strip();
                effacerErreur.appeler();
                System.out.print(CURA+EFFACER_LIGNE);

                if (minuteRep.length() != 0){
                    try{
                        int minuteInt = Integer.parseInt(minuteRep);
                        if (minuteInt < 0 || minuteInt > 59){
                            throw new NumberFormatException();
                        }
                        minute = minuteInt;
                    }catch(NumberFormatException e){
                        System.out.print("["+GRIS+ITAL+minute+"m"+NTRE+"]");
                        positionnerCurseurErreur.appeler();
                        System.out.print(ROUGE+"[ERREUR] Veuillez indiquer un nombre entier entre 0 et 59"+NTRE); // Affiche un message d'erreur sur la dernière ligne
                        continue;
                    }
                }
                System.out.print(minute);
                tache.echeance.set(Calendar.MINUTE, minute);
                break;
            }

            // Confirmer
            {
                positionnerCurseurConfirmer.appeler();
                System.out.print("Enregistrer la nouvelle tâche ? ["+GRIS+ITAL+"(o=Oui)| n=Non | a=Annuler"+NTRE+" ] : ");
                String confirmation = scanner.nextLine().strip().toLowerCase();
                positionnerCurseurConfirmer.appeler();
                System.out.print(EFFACER_LIGNE);

                if (confirmation.equals("o")){
                    System.out.println();
                    System.out.println(JAUNE+"La tâche sera enregistrée"+NTRE);
                    System.out.println(GRIS+ITAL+"Version de test, la tâche ne sera pas enregistrée."+NTRE);
                    scanner.nextLine();
                    InterfaceUtilisateur.menu = Menu.PRINCIPAL;
                    break;
                }

                if (confirmation.equals("a")){
                    System.out.println();
                    System.out.println(JAUNE+"Abandon. La tâche ne sera pas enregistrée"+NTRE);
                    System.out.println(GRIS+ITAL+"Version de test, la tâche ne sera pas enregistrée."+NTRE);
                    scanner.nextLine();
                    InterfaceUtilisateur.menu = Menu.PRINCIPAL;
                    break;
                }
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

    /**
     * Appelle `cls` sur Windows et `clear` sur *UNIX/MacOS/*BSD
     */
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

    // ===================================
    // Fonctions et constantes d'affichage
    // ===================================

    /**Séquence d'échappement ANSI */
    private static final String ESC="\033[";
    /**Sauvegarde la position du curseur. Utilisez CURA pour l'appliquer.*/
    private static final String CURS=ESC+"s";
    /**Applique la position du curseur sauvegardée avec CURS. */
    private static final String CURA=ESC+"u";
    /**Réinitalise le style d'affichage. */
    private static final String NTRE=ESC+"0m";
    /**Met la police en gras */
    private static final String GRAS=ESC+"1m";
    /**Met la police en fin, à l'opposé de gras */
    private static final String FIN=ESC+"2m";
    /**Met la police en italique */
    private static final String ITAL=ESC+"3m";
    /**Met la police en souligné */
    private static final String SOUL=ESC+"4m";
    /**Fait lentement clignoter le texte */
    private static final String CLGN_LNT=ESC+"5m";
    /**Fait rapidement clignoter le texte */
    private static final String CLGN_RPD=ESC+"6m";
    /**Inverse les couleurs du terminal */
    private static final String INVRS=ESC+"7m";
    /**Met la police en barré */
    private static final String BARRE=ESC+"9m";
    /**Met la couleur de la police en noir */
    private static final String NOIR=ESC+"30m";
    /**Met la couleur de la police en rouge */
    private static final String ROUGE=ESC+"31m";
    /**Met la couleur de la police en vert */
    private static final String VERT=ESC+"32m";
    /**Met la couleur de la police en jaune */
    private static final String JAUNE=ESC+"33m";
    /**Met la couleur de la police en bleu */
    private static final String BLEU=ESC+"34m";
    /**Met la couleur de la police en magenta */
    private static final String MAGENTA=ESC+"35m";
    /**Met la couleur de la police en cyan */
    private static final String CYAN=ESC+"36m";
    /**Met la couleur de la police en gris clair */
    private static final String GRIS_CLAIR=ESC+"37m";
    /**Met la couleur de la police en gris */
    private static final String GRIS=ESC+"38m";
    /**Met la couleur de la police en rouge clai */
    private static final String ROUGE_CLAIR=ESC+"90m";
    /**Met la couleur de la police en vert clair */
    private static final String VERT_CLAIR=ESC+"91m";
    /**Met la couleur de la police en jaune clair */
    private static final String JAUNE_CLAIR=ESC+"92m";
    /**Met la couleur de la police en bleu clair */
    private static final String BLEU_CLAIR=ESC+"93m";
    /**Met la couleur de la police en magenta clair */
    private static final String MAGENTA_CLAIR=ESC+"94m";
    /**Met la couleur de la police en cyan clair */
    private static final String CYAN_CLAIR=ESC+"95m";
    /**Met la couleur de la police en blanc */
    private static final String BLANC=ESC+"96m";
    /**Met la couleur du surlignage en noir */
    private static final String SURL_NOIR=ESC+"40m";
    /**Met la couleur du surlignage en rouge */
    private static final String SURL_ROUGE=ESC+"41m";
    /**Met la couleur du surlignage en vert */
    private static final String SURL_VERT=ESC+"42m";
    /**Met la couleur du surlignage en jaune */
    private static final String SURL_JAUNE=ESC+"43m";
    /**Met la couleur du surlignage en bleu */
    private static final String SURL_BLEU=ESC+"44m";
    /**Met la couleur du surlignage en magenta */
    private static final String SURL_MAGENTA=ESC+"45m";
    /**Met la couleur du surlignage en cyan */
    private static final String SURL_CYAN=ESC+"46m";
    /**Met la couleur du surlignage en gris clair */
    private static final String SURL_GRIS_CLAIR=ESC+"47m";
    /**Met la couleur du surlignage en gris */
    private static final String SURL_GRIS=ESC+"48m";
    /**Met la couleur du surlignage en rouge clair */
    private static final String SURL_ROUGE_CLAIR=ESC+"100m";
    /**Met la couleur du surlignage en vert clair */
    private static final String SURL_VERT_CLAIR=ESC+"101m";
    /**Met la couleur du surlignage en jaune clair */
    private static final String SURL_JAUNE_CLAIR=ESC+"102m";
    /**Met la couleur du surlignage en bleu clair */
    private static final String SURL_BLEU_CLAIR=ESC+"103m";
    /**Met la couleur du surlignage en magenta clair */
    private static final String SURL_MAGENTA_CLAIR=ESC+"104m";
    /**Met la couleur du surlignage en cyan clair */
    private static final String SURL_CYAN_CLAIR=ESC+"105m";
    /**Met la couleur du surlignage en blanc */
    private static final String SURL_BLANC=ESC+"106m";
    /**Efface depuis le curseur jusqu'à la fin de l'écran */
    private static final String EFFACER_ECRAN_RESTE=ESC+"0J";
    /**Efface depuis le début de l'écran jusqu'au curseur */
    private static final String EFFACER_ECRAN_PRECEDANT=ESC+"1J";
    /**Efface l'entièreté de l'écran. Ne déplace pas le curseur */
    private static final String EFFACER_ECRAN=ESC+"2J";
    /**Efface depuis le curseur jusqu'à la fin de la ligne */
    private static final String EFFACER_LIGNE_RESTE=ESC+"0K";
    /**Efface depuis le début de la ligne jusqu'au curseur */
    private static final String EFFACER_LIGNE_PRECEDANT=ESC+"1K";
    /**Efface l'entièreté de la ligne sur laquelle le curseur se trouve */
    private static final String EFFACER_LIGNE=ESC+"2K";

    /**
     * Positionne le curseur à X, Y, commençant à 1,1, y vers le bas.
     * @param x
     * @param y
     * @return \033[y;xH
     */
    private static String curPos(int x, int y){
        return ESC+y+';'+x+'H';
    }

    /**
     * Déplace le curseur de X vers la droite
     * @param x
     * @return \033[xC
     */
    private static String curDX(int x){
        if (x < 0){
            return ESC+x+'D';
        }else{
            return ESC+x+'C';
        }
    }

    /**
     * Déplace le curseur de Y vers le bas
     * @param y
     * @return \033[yA ou \033[yB dépendant du signe de y
     */
    private static String curDY(int y){
        if (y < 0){
            return ESC+y+'A';
        }else{
            return ESC+y+'B';
        }
    }
    /**
     * Déplace le curseur au début de n lignes plus bas
     * @param n
     * @return \033[nF ou \033[nE, dépendant du signe de n
     */
    private static String curLigne(int n){
        if (n < 0){
            return ESC+n+'F';
        }else{
            return ESC+n+'E';
        }
    }

    /**
     * Positionne le curseur à la position horizontale absolue X
     * @param x
     * @return \033[xG
     */
    private static String curX(int x){
        return ESC+x+'G';
    }

    /**
     * Fait défiler l'écran de n lignes vers le bas
     * @param n
     * @return \033[nT ou \033[nS dépendant du signe de n
     */
    private static String defiler(int n){
        if (n < 0){
            return ESC+n+"S";
        }else{
            return ESC+n+"T";
        }
    }
}

@FunctionalInterface 
abstract interface SousRoutine {
    void appeler();
}