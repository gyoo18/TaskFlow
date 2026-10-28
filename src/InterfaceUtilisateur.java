import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class InterfaceUtilisateur {
    
    private enum Menu {
        PRINCIPAL,
        AJOUTER_TACHE,
        AFFICHER_TACHES,
        GERER_TACHE,
        TITRE_TACHE,
        STATUT_TACHE,
        PRIORITE_TACHE,
        ECHEANCE_TACHE,
        SAUVEGARDER,
        QUITTER
    }
    private static Menu menu = Menu.PRINCIPAL;
    private static boolean quitter = false;

    private static Tache tacheSelectionnee;

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
            case Menu.AJOUTER_TACHE:    afficherAjouterUneTache(); break;
            case Menu.AFFICHER_TACHES:   afficherListeTaches(); break;
            case Menu.GERER_TACHE:      afficherGererTache(); break;
            case Menu.TITRE_TACHE:      afficherTitreTache(); break;
            case Menu.STATUT_TACHE:     afficherStatutTache(); break;
            case Menu.PRIORITE_TACHE:   afficherPrioriteTache(); break;
            case Menu.ECHEANCE_TACHE:   afficherEcheanceTache(); break;
            case Menu.SAUVEGARDER:      erreurNonImplemente("Sauvegarder"); break;
            case Menu.QUITTER:          afficherMenuQuitter(); break;
        }
    }

    public static void detruire(){
        scanner.close();
    }

    public static void afficherMenuPrincipal() {
        boolean continuer = true;

        System.out.println(BLEU+GRAS+SOUL+"TASKFLOW"+NTRE+" 🌊 - Gestion des tâches\n");

        //TODO: modifier lorsque les fichiers de sauvegarde existeront
        System.out.println("Aucun fichier de données trouvé."); 
        System.out.println("L'application démarre avec une liste vide\n");

        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Afficher les tâches");
        System.out.println("3. Sauvegarder les données");
        System.out.println("0. Quitter");

        while (continuer){
            //afficherMenu();

            System.out.println("\nVotre choix : "+CURS);
            System.out.print(GRIS+ITAL+"Version de test, aucune fonctionalité"+NTRE+CURA);
            String choix = scanner.nextLine().trim();
            System.out.println();

            continuer = false;
            // TODO: remplacer afficherFonctionnalite par les vraies fonctions
            switch (choix) {
                case "1": InterfaceUtilisateur.menu = Menu.AJOUTER_TACHE; break;                
                case "2": InterfaceUtilisateur.menu = Menu.AFFICHER_TACHES; break;
                case "3": InterfaceUtilisateur.menu = Menu.SAUVEGARDER; break;
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

    public static void afficherAjouterUneTache() {

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
                BLEU+GRAS+SOUL+"TASKFLOW"+NTRE+" 🌊\n"+
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
                System.out.print(EFFACER_LIGNE_RESTE);
                System.out.print(" "+CYAN+tache.titre+NTRE+" ]");
            }

            // Lire la priorité
            while(true){
                positionnerCurseurPriorite.appeler();
                System.out.print(EFFACER_LIGNE_RESTE);

                String rep = scanner.nextLine().strip();

                effacerErreur.appeler();
                positionnerCurseurPriorite.appeler();

                switch(rep){
                    case "1":
                        System.out.print(curX(0)+EFFACER_LIGNE); 
                        System.out.print("Priorité : "+GRIS+"Basse"+NTRE);
                        tache.priorite = Tache.Priorite.BASSE;
                        break;
                    case "":
                    case "2":
                        System.out.print(curX(0)+EFFACER_LIGNE);
                        System.out.print("Priorité : "+BLEU+"Normale"+NTRE);
                        tache.priorite = Tache.Priorite.NORMALE;
                        break;
                    case "3":
                        System.out.print(curX(0)+EFFACER_LIGNE);
                        System.out.print("Priorité : "+ROUGE+"Élevée"+NTRE);
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
                System.out.print(curX(0)+EFFACER_LIGNE);

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
                System.out.print(CURA+EFFACER_LIGNE_RESTE);

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
                System.out.print(CURA+EFFACER_LIGNE_RESTE);

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
                System.out.print(CURA+EFFACER_LIGNE_RESTE);

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
                System.out.print(CURA+EFFACER_LIGNE_RESTE);

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
                System.out.print(EFFACER_LIGNE+curX(1)+"Echeance : "+couleurEcheance(tache.echeance)+annee+" "+mois+" "+jour+" à "+heure+"h "+minute+"m"+NTRE);
                tache.echeance.set(Calendar.MINUTE, minute);
                break;
            }

            // Confirmer
            {
                positionnerCurseurConfirmer.appeler();
                System.out.print(JAUNE+"Enregistrer la nouvelle tâche ? "+NTRE+"["+GRIS+ITAL+"(o=Oui)| n=Non | a=Annuler"+NTRE+" ] : ");
                String confirmation = scanner.nextLine().strip().toLowerCase();
                positionnerCurseurConfirmer.appeler();
                System.out.print(EFFACER_LIGNE);

                if (confirmation.equals("o") || confirmation.length() == 0){
                    Donnees.ajouterTache(tache);

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

    public static void afficherListeTaches(){

        final int MAX_TACHES_PAR_PAGE=10;

        Tache[] tacheListe = Donnees.avoirListeTaches();
        // Trier les tâches selon leur échéance en mettant celles passées à la fin.
        List<Tache> tacheAL = Arrays.asList(tacheListe);
        Collections.sort(tacheAL, new Comparator<Tache>() {
            @Override
            public int compare(Tache t1, Tache t2) {
                if(!(t1.echeance.getTimeInMillis() > System.currentTimeMillis() ^ t2.echeance.getTimeInMillis() > System.currentTimeMillis())){
                    return t1.echeance.compareTo(t2.echeance);
                }else if(t1.echeance.getTimeInMillis() > System.currentTimeMillis() && t2.echeance.getTimeInMillis() < System.currentTimeMillis()){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        tacheAL.toArray(tacheListe);
        
        int page=0;
        int ligne=0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, kk:mm:ss", Locale.getDefault());

        SousRoutineParam<Integer> positionnerCurseurErreur = (n) -> {System.out.print(curPos(1, 15+Math.min(MAX_TACHES_PAR_PAGE, tacheListe.length-(n*MAX_TACHES_PAR_PAGE))));};
        SousRoutineParam<Integer> effacerErreur = (n) -> {positionnerCurseurErreur.appeler(n); System.out.print(EFFACER_LIGNE);};

        String[] nos = new String[MAX_TACHES_PAR_PAGE];
        String[] titres = new String[MAX_TACHES_PAR_PAGE];
        String[] statuts = new String[MAX_TACHES_PAR_PAGE];
        String[] priorites = new String[MAX_TACHES_PAR_PAGE];
        String[] echeances = new String[MAX_TACHES_PAR_PAGE];

        int lNo = "No".length();
        int lTitre = "Titre".length();
        int lStatut = "Statut".length();
        int lPriorite = "Priorité".length();
        int lEcheance = "Échéance".length();
        while(true){
            effacerEcran();
            
            System.out.println(
BLEU+GRAS+SOUL+"TASKFLOW"+NTRE+" 🌊\n"+"""

Liste de tâches
===============

"""+CURS        );

            System.out.print(CURA+EFFACER_ECRAN_RESTE);
            for (int i = 0; i+(page*MAX_TACHES_PAR_PAGE) < Math.min(tacheListe.length, (page+1)*MAX_TACHES_PAR_PAGE); i++) {
                nos[i] = Integer.toString(i+(page*MAX_TACHES_PAR_PAGE)+1);
                
                Tache t = tacheListe[i+(page*MAX_TACHES_PAR_PAGE)];
                titres[i] = t.titre;
                statuts[i] = t.statut.name();
                priorites[i] = t.priorite.name();
                echeances[i] = dateFormat.format(t.echeance.getTime());

                lNo = Math.max(nos[i].length(), lNo);
                lTitre = Math.max(titres[i].length(), lTitre);
                lStatut = Math.max(statuts[i].length(), lStatut);
                lPriorite = Math.max(priorites[i].length(), lPriorite);
                lEcheance = Math.max(echeances[i].length(), lEcheance);
            }

            System.out.println(
                "No"+(" ".repeat(lNo-"No".length()))+"  "+
                "Titre"+(" ".repeat(lTitre-"Titre".length()))+"  "+
                "Statut"+(" ".repeat(lStatut-"Statut".length()))+"  "+
                "Priorité"+(" ".repeat(lPriorite-"Priorité".length()))+"  "+
                "Échéance"+(" ".repeat(lEcheance-"Échéance".length()))+"  "+
                "["+CURS+"x]=choisir"
            );

            for (int i = 0; i+(page*MAX_TACHES_PAR_PAGE) < Math.min(tacheListe.length, (page+1)*MAX_TACHES_PAR_PAGE); i++) {
                Tache t = tacheListe[i+(page*MAX_TACHES_PAR_PAGE)];
                System.out.println( CYAN + 
                    nos[i]+(" ".repeat(lNo-nos[i].length()))+"  "+
                    titres[i]+(" ".repeat(lTitre-titres[i].length()))+"  "+(
                        (switch (t.statut) {case A_FAIRE -> JAUNE; case TERMINE -> VERT;})+
                        statuts[i]+"  "
                    )+(
                        (switch (t.priorite) {case BASSE -> GRIS; case NORMALE -> BLEU; case ELEVEE -> ROUGE;})+
                        priorites[i]+(" ".repeat(lPriorite-priorites[i].length()))+"  "+NTRE
                    )+
                    GRAS + couleurEcheance(t.echeance) + echeances[i]+(" ".repeat(lEcheance-echeances[i].length())) + NTRE + "  " +
                    "[ ]"
                );
            }

            System.out.println(
                "\n"+
                "w = ligne précédente\n"+
                "s ou ⮱ = prochaine ligne\n"+
                "a = page précédente\n"+
                "d = prochaine page\n"+
                "x = modifier la tâche\n"+
                "q = quitter\n"+
                "\n"+
                GRIS+ITAL+"Version de test, fausses tâches"+NTRE
            );

            ligne = 0;
            boolean quitter = false;
            System.out.print(CURA+curDY(1)+CURS);
            while(true){
                String rep = scanner.nextLine();
                effacerErreur.appeler(page);

                switch (rep.toLowerCase()) {
                    case "a": 
                        if (page != 0){
                            page--;
                        } else {
                            System.out.print(CURA+EFFACER_LIGNE_RESTE+" ]"+CURA);
                            continue;
                        }
                        break;
                    case "d": 
                        if (page != Math.floorDiv(tacheListe.length, MAX_TACHES_PAR_PAGE)){
                            page++;
                        }else{
                            System.out.print(CURA+EFFACER_LIGNE_RESTE+" ]"+CURA);
                            continue;
                        }
                        break;
                    case "x":
                        int index = page * MAX_TACHES_PAR_PAGE + ligne;
                        tacheSelectionnee = tacheListe[index];

                        InterfaceUtilisateur.menu = Menu.GERER_TACHE;
                        quitter = true;
                        break;
                    case "q":
                        InterfaceUtilisateur.menu = Menu.PRINCIPAL;
                        quitter = true;
                        break;
                    case "":
                    case "s":
                        if (ligne == tacheListe.length - page*MAX_TACHES_PAR_PAGE - 1 && page == Math.floorDiv(tacheListe.length, MAX_TACHES_PAR_PAGE)){
                            System.out.print(CURA);
                            continue;
                        }else if (ligne == MAX_TACHES_PAR_PAGE-1){
                            page++;
                        }else{
                            System.out.print(CURA+EFFACER_LIGNE_RESTE+" ]"+CURA+curDY(1)+CURS);
                            ligne++;
                            continue;
                        }
                        break;
                    case "w":
                        System.out.print(CURA+EFFACER_LIGNE_RESTE+" ]"+CURA);
                        if (ligne == 0 && page == 0){
                            System.out.print(CURA);
                            continue;
                        }else if (ligne == 0){
                            page--;
                        }else{
                            System.out.print(curDY(-1)+CURS);
                            ligne--;
                            continue;
                        }
                        break;
                    default:
                        positionnerCurseurErreur.appeler(page);
                        System.out.print(ROUGE+"[ERREUR] Veuillez indiquer une lettre parmis 'w', 'a', 's', 'd', 'x' et 'q'"+NTRE);
                        System.out.print(CURA+EFFACER_LIGNE_RESTE+" ]"+CURA);
                        continue;
                }
                break;
            }

            if (quitter) break;
        }
    }

    public static void afficherGererTache(){

        Tache t = tacheSelectionnee;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, kk:mm:ss", Locale.getDefault());
        String echeanceString = dateFormat.format(t.echeance.getTime());


        System.out.println(
BLEU+GRAS+SOUL+"TASKFLOW"+NTRE+" 🌊\n"+"""

Modifier une tâche
==================

"""         +CYAN+t.titre+NTRE+" [ ]\n"+
            "-".repeat(t.titre.length())+"\n"+
            "Statut : "+(switch (t.statut) {case A_FAIRE -> JAUNE; case TERMINE -> VERT;})+t.statut+NTRE+" [ ]\n"+
            "Priorité : "+(switch (t.priorite) {case BASSE -> GRIS; case NORMALE -> BLEU; case ELEVEE -> ROUGE;})+t.priorite+NTRE+" [ ]\n"+
            "Échéance : "+couleurEcheance(t.echeance)+echeanceString+NTRE+" [ ]\n"+
            "\n"+
            "Confirmer [ ]\n"+
            "Annuler [ ]\n"+
"""

x = Sélectionner
s ou ⮱ = Prochain
w = Précédent

"""
            );

        enum Position{
            TITRE,
            STATUT,
            PRIORITE,
            ECHEANCE,
            CONFIRMER,
            ANNULER
        }
        Position position = Position.TITRE;

        SousRoutine positionnerCurseurTitre = () -> {System.out.print(curPos(t.titre.length() + 3, 6));};
        SousRoutine positionnerCurseurStatut = () -> {System.out.print(curPos(9 + t.statut.toString().length() + 3, 8));};
        SousRoutine positionnerCurseurPriorite = () -> {System.out.print(curPos(11 + t.priorite.toString().length() + 3, 9));};
        SousRoutine positionnerCurseurEcheance = () -> {System.out.print(curPos(11 + echeanceString.length() + 3, 10));};
        SousRoutine positionnerCurseurConfirmer = () -> {System.out.print(curPos(12, 12));};
        SousRoutine positionnerCurseurAnnuler = () -> {System.out.print(curPos(10, 13));};
        SousRoutine positionnerCurseurErreur = () -> {System.out.print(curPos(1, 18));};
        SousRoutine effacerErreur = () -> {positionnerCurseurErreur.appeler(); System.out.print(EFFACER_LIGNE);};
        SousRoutineParam<Position> positionnerCurseur = (p)->{
            switch (p) {
                case TITRE:     positionnerCurseurTitre.appeler(); break;
                case STATUT:    positionnerCurseurStatut.appeler(); break;
                case PRIORITE:  positionnerCurseurPriorite.appeler(); break;
                case ECHEANCE:  positionnerCurseurEcheance.appeler(); break;
                case CONFIRMER: positionnerCurseurConfirmer.appeler(); break;
                case ANNULER:   positionnerCurseurAnnuler.appeler(); break;
            }
        };

        positionnerCurseurTitre.appeler();
        while(true){
            String rep = scanner.nextLine();
            effacerErreur.appeler();
            
            switch (rep.toLowerCase()) {
                case "x":
                    switch(position){
                        case TITRE:     menu = Menu.TITRE_TACHE; break;
                        case STATUT:    menu = Menu.STATUT_TACHE; break;
                        case PRIORITE:  menu = Menu.PRIORITE_TACHE; break;
                        case ECHEANCE:  menu = Menu.ECHEANCE_TACHE; break;
                        case CONFIRMER: menu = Menu.PRINCIPAL; break;
                        case ANNULER:
                            boolean quitter = false;
                            while (true){
                                positionnerCurseurAnnuler.appeler();
                                System.out.print(EFFACER_LIGNE+curX(1));
                                System.out.print(JAUNE+"Êtes-vous sûr de vouloir abandonner les modifications? [o/N] : "+NTRE);
                                rep = scanner.nextLine();
                                effacerErreur.appeler();

                                quitter = true;
                                switch (rep.toLowerCase()) {
                                    case "o": menu = Menu.AFFICHER_TACHES; break;
                                    case "":
                                    case "n":
                                        positionnerCurseurAnnuler.appeler();
                                        System.out.print(EFFACER_LIGNE+curX(1)+"Annuler [ ]"+curX(10));
                                        quitter = false;
                                        break;
                                    default:
                                        positionnerCurseurErreur.appeler();
                                        System.out.print(ROUGE+"[ERREUR] Veuillez indiquer une lettre parmis 'o' et 'n'."+NTRE);
                                        continue;
                                }
                                break;
                            }
                            if(!quitter){
                                continue;
                            }
                    }
                    break;
                case "":
                case "s":{
                    positionnerCurseur.appeler(position);
                    System.out.print(CURS+EFFACER_LIGNE_RESTE+" ]"+CURA);
                    // Trouver l'indexe de la position actuelle
                    int i = 0;
                    for (Position p : Position.values()) {
                        if(p == position) break;
                        i++;
                    }
                    if(i != Position.values().length -1) i++;

                    position = Position.values()[i];
                    positionnerCurseur.appeler(position);
                    continue;
                }
                case "w":{
                    positionnerCurseur.appeler(position);
                    System.out.print(CURS+EFFACER_LIGNE_RESTE+" ]"+CURA);
                    // Trouver l'indexe de la position actuelle
                    int i = 0;
                    for (Position p : Position.values()) {
                        if(p == position) break;
                        i++;
                    }
                    if(i != 0) i--;

                    position = Position.values()[i];
                    positionnerCurseur.appeler(position);
                    continue;
                }
                default:
                    positionnerCurseurErreur.appeler();
                    System.out.println(ROUGE+"[ERREUR] Veuillez indiquer une lettre parmis 'x', 's' ou 'w'."+NTRE);
                    positionnerCurseur.appeler(position);
                    System.out.print(CURS+EFFACER_LIGNE_RESTE+" ]"+CURA);
                    continue;
            }
            break;
        }
    }

    public static void afficherTitreTache(){
        SousRoutine positionnerCurseurSauvegarde = () -> {System.out.print(curPos(23, 7));};
        SousRoutine positionnerCurseurErreur = () -> {System.out.print(curPos(1, 8));};
        SousRoutine effacerErreur = () -> {positionnerCurseurErreur.appeler(); System.out.print(EFFACER_LIGNE);};

        System.out.print(
BLEU+GRAS+SOUL+"TASKFLOW"+NTRE+" 🌊\n"+"""

Renommer une Tâche
==================

("""+GRIS+ITAL+"Nouvelle Tâche"+NTRE+") : ["+CURS+GRIS+"_".repeat(30)+NTRE+"]\n"+
            "\n"+
            "\n"+
            GRIS+ITAL+"Version de test, n'est pas une vrai tâche"+NTRE+CURA
        );
        String rep = scanner.nextLine();
        System.out.print(CURA+EFFACER_LIGNE_RESTE+CYAN+rep+NTRE+"]"+curLigne(1));
        System.out.print(JAUNE+"Sauvegarder ? [O/n] : "+NTRE);
        while(true){
            positionnerCurseurSauvegarde.appeler();
            rep = scanner.nextLine();
            effacerErreur.appeler();
            positionnerCurseurSauvegarde.appeler();
            System.out.print(EFFACER_LIGNE_RESTE);

            switch(rep.toLowerCase()){
                case "":
                case "o": menu = Menu.GERER_TACHE; break;
                case "n": System.out.print(EFFACER_LIGNE+CURA+EFFACER_LIGNE_RESTE+GRIS+"_".repeat(30)+NTRE+"]"+CURA); break;
                default:
                    positionnerCurseurErreur.appeler();
                    System.out.print(ROUGE+"[ERREUR] Veuillez indiquer une lettre entre 'o' et 'n'"+NTRE);
                    continue;
            }
            break;
        }
    }

    public static void afficherStatutTache(){

        String titre = tacheSelectionnee.titre;
        Tache.Statut statut = tacheSelectionnee.statut;
        System.out.print(
BLEU+GRAS+SOUL+"TASKFLOW"+NTRE+" 🌊\n"+"""

Changer le statut d'une Tâche
=============================

"""+CYAN+titre+NTRE+"\n"+
    "-".repeat(titre.length())+"\n\n");

        System.out.println(
            (statut==Tache.Statut.A_FAIRE? "["+JAUNE+"À FAIRE"+NTRE+"]": JAUNE+" À FAIRE "+NTRE) + " : ["+CURS+" ]\n"+
            (statut==Tache.Statut.TERMINE? "["+VERT+"TERMINÉ"+NTRE+"]": VERT+" TERMINÉ "+NTRE) + " : [ ]\n"+
            "\n"+
            "Confirmer : [ ]\n"+
            "Annuler : [ ]\n"+
            "\n"+
            "x = Sélectionner\n"+
            "w = Précédant\n"+
            "s ou ⮱ = Prochain\n"+
            "\n");
        System.out.print(CURA);

        enum Position{
            A_FAIRE,
            TERMINE,
            CONFIRMER,
            ANNULER
        }
        Position position = Position.A_FAIRE;

        SousRoutine positionnerCurseurAFaire = () -> {System.out.print(curPos(14,9));};
        SousRoutine positionnerCurseurTermine = () -> {System.out.print(curPos(14,10));};
        SousRoutine positionnerCurseurConfirmer = () -> {System.out.print(curPos(14,12));};
        SousRoutine positionnerCurseurAnnuler = () -> {System.out.print(curPos(12,13));};
        SousRoutine positionnerCurseurErreur = () -> {System.out.print(curPos(1,18));};
        SousRoutine effacerErreur = () -> {positionnerCurseurErreur.appeler();System.out.print(EFFACER_LIGNE);};
        SousRoutineParam<Position> positionnerCurseur = (p) -> {
            switch (p) {
                case A_FAIRE:   positionnerCurseurAFaire.appeler(); break;
                case TERMINE:   positionnerCurseurTermine.appeler(); break;
                case CONFIRMER: positionnerCurseurConfirmer.appeler(); break;
                case ANNULER:   positionnerCurseurAnnuler.appeler(); break;
                default:
                    break;
            }
        };

        while(true){
            positionnerCurseur.appeler(position);
            String rep = scanner.nextLine();
            effacerErreur.appeler();
            positionnerCurseur.appeler(position);
            System.out.print(CURS+EFFACER_LIGNE_RESTE+" ]"+CURA);

            switch (rep.toLowerCase()) {
                case "x":
                    switch (position) {
                        case A_FAIRE: 
                        case TERMINE: 
                            // Ne pas permettre de sélectionner un statut déjà appliqué
                            if (position.toString().equals(statut.toString())) {
                                continue;
                            }

                            positionnerCurseur.appeler(Position.valueOf(statut.toString()));
                            System.out.print(curX(1)+" "+curX(statut.toString().length()+2)+" ");

                            positionnerCurseur.appeler(position);
                            System.out.print(curX(1)+"["+curX(position.toString().length()+2)+"]");

                            positionnerCurseur.appeler(position);
                            statut = Tache.Statut.valueOf(position.toString());
                            continue;

                        case CONFIRMER:
                            tacheSelectionnee.statut = statut;
                            menu = Menu.GERER_TACHE;
                            break;
                        case ANNULER: menu = Menu.GERER_TACHE; break;
                    }
                    break;
                case "w":{
                    int i = 0;
                    for (Position p : Position.values()) {
                        if (p == position) break;
                        i++;
                    }
                    if (i != 0) i--;
                    position = Position.values()[i];
                    continue;
                }
                case "":
                case "s":{
                    int i = 0;
                    for (Position p : Position.values()) {
                        if (p == position) break;
                        i++;
                    }
                    if (i != Position.values().length - 1) i++;
                    position = Position.values()[i];
                    continue;
                }
                default:
                    positionnerCurseurErreur.appeler();
                    System.out.print(ROUGE+"[ERREUR] Veuilez indiquer une Lettre parmis 'w', 's', et 'x'"+NTRE);
                    continue;
            }
            break;
        }
    }

    public static void afficherPrioriteTache(){


        String titre = tacheSelectionnee.titre;
        Tache.Priorite priorite = tacheSelectionnee.priorite;
        System.out.print(
BLEU+GRAS+SOUL+"TASKFLOW"+NTRE+" 🌊\n"+"""

Changer la Priorité d'une Tâche
===============================

"""+CYAN+titre+NTRE+"\n"+
    "-".repeat(titre.length())+"\n\n");

        System.out.println(
            (priorite==Tache.Priorite.BASSE? "["+GRIS+"BASSE"+NTRE+"]": GRIS+" BASSE "+NTRE) + " : ["+CURS+" ]\n"+
            (priorite==Tache.Priorite.NORMALE? "["+BLEU+"NORMALE"+NTRE+"]": BLEU+" NORMALE "+NTRE) + " : [ ]\n"+
            (priorite==Tache.Priorite.ELEVEE? "["+ROUGE+"ÉLEVÉE"+NTRE+"]": ROUGE+" ÉLEVÉE "+NTRE) + " : [ ]\n"+
            "\n"+
            "Confirmer : [ ]\n"+
            "Annuler : [ ]\n"+
            "\n"+
            "x = Sélectionner\n"+
            "w = Précédant\n"+
            "s ou ⮱ = Prochain\n"+
            "\n");
        System.out.print(CURA);

        enum Position{
            BASSE,
            NORMALE,
            ELEVEE,
            CONFIRMER,
            ANNULER
        }
        Position position = Position.BASSE;

        SousRoutine positionnerCurseurBasse = () -> {System.out.print(curPos(12,9));};
        SousRoutine positionnerCurseurNormale = () -> {System.out.print(curPos(14,10));};
        SousRoutine positionnerCurseurElevee = () -> {System.out.print(curPos(13,11));};
        SousRoutine positionnerCurseurConfirmer = () -> {System.out.print(curPos(14,13));};
        SousRoutine positionnerCurseurAnnuler = () -> {System.out.print(curPos(12,14));};
        SousRoutine positionnerCurseurErreur = () -> {System.out.print(curPos(1,19));};
        SousRoutine effacerErreur = () -> {positionnerCurseurErreur.appeler();System.out.print(EFFACER_LIGNE);};
        SousRoutineParam<Position> positionnerCurseur = (p) -> {
            switch (p) {
                case BASSE:   positionnerCurseurBasse.appeler(); break;
                case NORMALE:   positionnerCurseurNormale.appeler(); break;
                case ELEVEE:   positionnerCurseurElevee.appeler(); break;
                case CONFIRMER: positionnerCurseurConfirmer.appeler(); break;
                case ANNULER:   positionnerCurseurAnnuler.appeler(); break;
                default:
                    break;
            }
        };

        while(true){
            positionnerCurseur.appeler(position);
            String rep = scanner.nextLine();
            effacerErreur.appeler();
            positionnerCurseur.appeler(position);
            System.out.print(CURS+EFFACER_LIGNE_RESTE+" ]"+CURA);

            switch (rep.toLowerCase()) {
                case "x":
                    switch (position) {
                        case BASSE: 
                        case NORMALE:
                        case ELEVEE:
                            // Modifier la priorité temporairement
                            positionnerCurseur.appeler(Position.valueOf(priorite.toString()));
                            System.out.print(curX(1)+" "+curX(priorite.toString().length()+2)+" ");

                            positionnerCurseur.appeler(position);
                            System.out.print(curX(1)+"["+curX(position.toString().length()+2)+"]");

                            positionnerCurseur.appeler(position);
                            priorite = Tache.Priorite.valueOf(position.toString());
                            continue;

                        case CONFIRMER:
                            // Appliquer la nouvelle priorité à la tâche
                            tacheSelectionnee.priorite = priorite;
                            menu = Menu.GERER_TACHE;
                            break;
                        case ANNULER: menu = Menu.GERER_TACHE; break;
                    }
                    break;
                case "w":{
                    int i = 0;
                    for (Position p : Position.values()) {
                        if (p == position) break;
                        i++;
                    }
                    if (i != 0) i--;
                    position = Position.values()[i];
                    continue;
                }
                case "":
                case "s":{
                    int i = 0;
                    for (Position p : Position.values()) {
                        if (p == position) break;
                        i++;
                    }
                    if (i != Position.values().length - 1) i++;
                    position = Position.values()[i];
                    continue;
                }
                default:
                    positionnerCurseurErreur.appeler();
                    System.out.print(ROUGE+"[ERREUR] Veuilez indiquer une Lettre parmis 'w', 's', et 'x'"+NTRE);
                    continue;
            }
            break;
        }
    }

    public static void afficherEcheanceTache(){
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

        SousRoutine positionnerCurseurEcheance = () -> {System.out.print(curPos(1,9));};
        SousRoutine positionnerCurseurErreur = () -> {System.out.print(curPos(1,10));};
        SousRoutine effacerErreur =            () -> {positionnerCurseurErreur.appeler(); System.out.print(EFFACER_LIGNE);};

        String titre = "Nouvelle Tâche";
        Calendar echeance = Calendar.getInstance();
        echeance.add(Calendar.MONTH,(int)(Math.random()));
        echeance.set(Calendar.DAY_OF_MONTH, (int)(Math.random()*echeance.getActualMaximum(Calendar.DAY_OF_MONTH)));
        echeance.set(Calendar.HOUR_OF_DAY,(int)(Math.random()*(echeance.getActualMaximum(Calendar.HOUR_OF_DAY)+0.99)));
        echeance.set(Calendar.MINUTE,(int)(Math.random()*59.99));

        int annee = echeance.get(Calendar.YEAR);
        String mois = echeance.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.getDefault());
        int jour = echeance.get(Calendar.DAY_OF_MONTH);
        int heure = echeance.get(Calendar.HOUR_OF_DAY);
        int minute = echeance.get(Calendar.MINUTE);

        System.out.print(
BLEU+GRAS+SOUL+"TASKFLOW"+NTRE+" 🌊\n"+"""

Modifier l'échéance d'une Tâche
===============================

"""+titre+"\n"+
"-".repeat(titre.length())+"\n"+"""



"""+GRIS+ITAL+"Version de test, ne modifiera pas la tâche"+NTRE
            );

        while(true){
            positionnerCurseurEcheance.appeler();
            System.out.print(EFFACER_LIGNE);
            System.out.print("["+CURS+GRIS+ITAL+annee+NTRE+"] ["+GRIS+ITAL+mois+NTRE+"] ["+GRIS+ITAL+jour+NTRE+"] à ["+GRIS+ITAL+heure+"h"+NTRE+"] ["+GRIS+ITAL+minute+"m"+NTRE+"]");

            // Lire la date
            // Année
            while(true){
                System.out.print(CURA);

                String anneeRep = scanner.nextLine().strip();

                effacerErreur.appeler();
                System.out.print(CURA+curX(0)+EFFACER_LIGNE);

                if (anneeRep.length() != 0){
                    try{
                        annee = Integer.parseInt(anneeRep);
                    }catch(NumberFormatException e){
                        System.out.print("["+GRIS+ITAL+annee+NTRE+"] ["+GRIS+ITAL+mois+NTRE+"] ["+GRIS+ITAL+jour+NTRE+"] à ["+GRIS+ITAL+heure+"h"+NTRE+"] ["+GRIS+ITAL+minute+"m"+NTRE+"]");
                        positionnerCurseurErreur.appeler();
                        System.out.print(ROUGE+"[ERREUR] Veuillez indiquer un nombre entier."+NTRE); // Affiche un message d'erreur sur la dernière ligne
                        continue;
                    }
                }
                // Sauvegarde la position du curseur     v--v
                System.out.print(annee+" "+CURS+"["+GRIS+ITAL+mois+NTRE+"] ["+GRIS+ITAL+jour+NTRE+"] à ["+GRIS+ITAL+heure+"h"+NTRE+"] ["+GRIS+ITAL+minute+"m"+NTRE+"]");
                echeance.set(Calendar.YEAR, annee);
                break;
            }

            // Lire la date
            // Mois
            while(true){
                System.out.print(CURA+curDX(1));
                String moisRep = scanner.nextLine().strip();
                effacerErreur.appeler();
                System.out.print(CURA+EFFACER_LIGNE_RESTE);

                int moisInt = echeance.get(Calendar.MONTH);
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
                echeance.set(Calendar.MONTH, moisInt);
                break;
            }

            // Lire la date
            // Jour
            // IMPORTANT : Il est nécessaire de lire le mois avant le jour pour connaître le maximum de jours.
            int maxJour = echeance.getActualMaximum(Calendar.DAY_OF_MONTH);
            while(true){
                System.out.print(CURA+curDX(1));
                String jourRep = scanner.nextLine().strip();
                effacerErreur.appeler();
                System.out.print(CURA+EFFACER_LIGNE_RESTE);

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
                echeance.set(Calendar.DAY_OF_MONTH, jour);
                break;
            }

            // Lire la date
            // Heure
            // IMPORTANT : Il est nécessaire de lire l'année/mois/jour pour connaître le maximum d'heures
            int maxHeure = echeance.getActualMaximum(Calendar.HOUR_OF_DAY); // Changement d'heure et autres corrections à tous les cents ans
            while(true){
                System.out.print(CURA+curDX(1));
                String heureRep = scanner.nextLine().strip();
                effacerErreur.appeler();
                System.out.print(CURA+EFFACER_LIGNE_RESTE);

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
                System.out.print(heure+"h "+CURS+"["+GRIS+ITAL+minute+"m"+NTRE+"]");
                echeance.set(Calendar.HOUR_OF_DAY, heure);
                break;
            }

            // Lire la date
            // Minute
            while(true){
                System.out.print(CURA+curDX(1));
                String minuteRep = scanner.nextLine().strip();
                effacerErreur.appeler();
                System.out.print(CURA+EFFACER_LIGNE_RESTE);

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
                System.out.print(minute+"m");
                echeance.set(Calendar.MINUTE, minute);
                break;
            }

            // Confirmer
            {
                System.out.print(curLigne(1)+CURS+JAUNE+"Confirmer ?"+NTRE+" ["+GRIS+ITAL+"(o=Oui)| n=Non | a=Annuler"+NTRE+" ] : ");
                String confirmation = scanner.nextLine().strip().toLowerCase();
                System.out.print(CURA+EFFACER_LIGNE);

                if (confirmation.equals("o") || confirmation.length() == 0){
                    InterfaceUtilisateur.menu = Menu.GERER_TACHE;
                    break;
                }

                if (confirmation.equals("a")){
                    System.out.print(JAUNE+"Abandon. La tâche ne sera pas enregistrée"+NTRE);
                    scanner.nextLine();
                    InterfaceUtilisateur.menu = Menu.GERER_TACHE;
                    break;
                }
            }
        }
    }

    public static void afficherMenuSauvegarde(){

        boolean continuer = true;

        System.out.println("Voulez-vous sauvegarder les changements effectués?\n");
        System.out.println("1. Oui");
        System.out.println("0. Non, retourner au menu principal");

        while (continuer){
            System.out.print("\nVotre choix : ");
            String choix = scanner.nextLine().trim();
            System.out.println();
            switch(choix){
                case "1":
                    //le print sera remplacer par une fonction de sauvegarde
                    System.out.println("Votre sauvegarde a été effectuée");
                    InterfaceUtilisateur.menu = Menu.PRINCIPAL;
                    continuer = false;
                    break;

                case "0": 
                    System.out.println("Retour vers le menu principal");
                    InterfaceUtilisateur.menu = Menu.PRINCIPAL;
                    continuer = false;
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez chosir une option entre 0 ou 1.");
                    break;
                }
                System.out.println();
            }




    }

    //Fonction pour quitter le programme
    public static void afficherMenuQuitter() {
        System.out.println("Voulez-vous vraiment quitter le programme ? (O/N)");
        String choixQuitter = scanner.nextLine().trim().toLowerCase();

        if (choixQuitter.equals("o")) {
            System.out.println("Au revoir !");
            System.exit(0);
            scanner.close();
        } else if (choixQuitter.equals("n")) {
            System.out.println("Retour au menu principal.");
            InterfaceUtilisateur.menu = Menu.PRINCIPAL;
        } else {
            System.out.println("Choix invalide. Veuillez entrer 'O' pour Oui ou 'N' pour Non.");
            afficherMenuQuitter();
        }
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
     * Renvoie une couleur RGB 0.0-1.0 entre le vert foncé ([0.002,0.072,0.070] à 0.0)
     * et le orange clair ([1, 0.75, 0.50] à 1) en passant par le bleu et le mauve, en
     * fonction du facteur t.
     * @param t
     * @return new float[]{R, G, B} ∊ [0-1]³
     */
    public static float[] rampeCouleurMagma(float t){
        // J'ai décidé d'utiliser des doubles pour aider à la lisibilité plus que pour un besoin de précision.
        final double 𝝅 = Math.PI;

        final double[] i = new double[]{-1/Math.sqrt(2),1/Math.sqrt(2),0};
        final double[] j = new double[]{1/Math.sqrt(3),1/Math.sqrt(3),1/Math.sqrt(3)};
        final double[] k = new double[]{-1/Math.sqrt(3),-1/Math.sqrt(3),1/Math.sqrt(3)};

        if (t >= 0 && t <= 1){
            double 𝜽 = 3*𝝅*t/4;
            double r = t;
            double z = t*Math.sqrt(3);

            double R = r*Math.cos(𝜽)*i[0] + z*j[0] + r*Math.sin(𝜽)*k[0];
            double G = r*Math.cos(𝜽)*i[1] + z*j[1] + r*Math.sin(𝜽)*k[1];
            double B = r*Math.cos(𝜽)*i[2] + z*j[2] + r*Math.sin(𝜽)*k[2];

            // mix( RGB, [1.0, 0.75, 0.50], t² )
            R = (t*t)*(1.0 -R) + R;
            G = (t*t)*(0.75-G) + G;
            B = (t*t)*(0.50-B) + B;
            return new float[]{(float)R, (float)G, (float)B};
        }else if(t < 0){
            return new float[]{0.11f,0.11f,0.11f};
        }else{
            return new float[]{1.0f,0.75f,0.50f};
        }
    }

    /**
     * Renvoie le code ANSI de la couleur 8bit de l'échéance d'une tâche selon la carte Magma (rampeCouleurMagma())
     * Plus une tâche est près de son échéance, plus elle sera visible.
     * La couleur s'étale sur 14 jours et plus de résolution est accordé près de l'échéance.
     * @param echeance
     * @return new float[]{R, G, B} ∊ [0-1]³
     */
    public static String couleurEcheance(Calendar echeance){
        final double QUATORZE_JOURS_MILLIS=1209600000D;
        float t = (float)((double)(echeance.getTimeInMillis() - System.currentTimeMillis())/QUATORZE_JOURS_MILLIS); // 0=0 et 1=14 jours
        if(t >= 0.0){
            // Transforme t pour avoir plus de résolution près de l'échéance
            t = 1f/(12f*(t+0.0802f)) - 0.0387f;
            t = (t*26.99f)/26f;

            float[] coul = rampeCouleurMagma((2*t)%1);
            if (t < 0.5){
                return coul8b(coul[0], coul[1], coul[2]);
            }else{
                return surl8b(coul[0], coul[1], coul[2]);
            }
        }else{
            return coul8b(0.2f,0.0f,0.0f);
        }
    }

    // ===================================
    // Fonctions et constantes d'affichage
    // ===================================

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
    private static final String GRIS=ESC+"90m";
    /**Met la couleur de la police en rouge clai */
    private static final String ROUGE_CLAIR=ESC+"91m";
    /**Met la couleur de la police en vert clair */
    private static final String VERT_CLAIR=ESC+"92m";
    /**Met la couleur de la police en jaune clair */
    private static final String JAUNE_CLAIR=ESC+"93m";
    /**Met la couleur de la police en bleu clair */
    private static final String BLEU_CLAIR=ESC+"94m";
    /**Met la couleur de la police en magenta clair */
    private static final String MAGENTA_CLAIR=ESC+"95m";
    /**Met la couleur de la police en cyan clair */
    private static final String CYAN_CLAIR=ESC+"96m";
    /**Met la couleur de la police en blanc */
    private static final String BLANC=ESC+"97m";
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
    private static final String SURL_GRIS=ESC+"100m";
    /**Met la couleur du surlignage en rouge clair */
    private static final String SURL_ROUGE_CLAIR=ESC+"101m";
    /**Met la couleur du surlignage en vert clair */
    private static final String SURL_VERT_CLAIR=ESC+"102m";
    /**Met la couleur du surlignage en jaune clair */
    private static final String SURL_JAUNE_CLAIR=ESC+"103m";
    /**Met la couleur du surlignage en bleu clair */
    private static final String SURL_BLEU_CLAIR=ESC+"104m";
    /**Met la couleur du surlignage en magenta clair */
    private static final String SURL_MAGENTA_CLAIR=ESC+"105m";
    /**Met la couleur du surlignage en cyan clair */
    private static final String SURL_CYAN_CLAIR=ESC+"106m";
    /**Met la couleur du surlignage en blanc */
    private static final String SURL_BLANC=ESC+"107m";
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
            return ESC+-x+'D';
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
            return ESC+-y+'A';
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
            return ESC+-n+'F';
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
            return ESC+-n+"S";
        }else{
            return ESC+n+"T";
        }
    }

    /**
     * Met la couleur de la police à la couleur RGB 8bits spécifiée.
     * 225 couleurs sont possibles (R,G,B ∊ {0;5}), ce qui fait en réalité 7,8 bits.
     * @param r ∊ [0;1]
     * @param g ∊ [0;1]
     * @param b ∊ [0;1]
     * @return \033[38;5;int(r*215.99 + g*35.99 + b*5.99 + 16)m
     */
    private static String coul8b (float r, float g, float b){
        return ESC+"38;5;"+((int)(r*5.99)*36 + (int)(g*5.99)*6 + (int)(b*5.99) + 16)+"m";
    }

    /**
     * Met la couleur du surlignage à la couleur RGB 8bits spécifiée.
     * 225 couleurs sont possibles (R,G,B ∊ {0;5}), ce qui fait en réalité 7,8 bits.
     * @param r ∊ [0;1]
     * @param g ∊ [0;1]
     * @param b ∊ [0;1]
     * @return \033[48;5;int(r*215.99 + g*35.99 + b*5.99 + 16)m
     */
    private static String surl8b (float r, float g, float b){
        return ESC+"48;5;"+((int)(r*5.99)*36 + (int)(g*5.99)*6 + (int)(b*5.99) + 16)+"m";
    }
}

@FunctionalInterface 
abstract interface SousRoutine {
    void appeler();
}

@FunctionalInterface 
abstract interface SousRoutineParam<T> {
    void appeler(T t);
}