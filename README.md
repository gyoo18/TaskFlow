# Mini projet 1 - Organisation agile et développement collaboratif - TASKFLOW

## Travail par : 
- Hugo Thivierge - THIH66360209  
- Sébastien Roussel - ROUS77050209
- Vincent Godbout - GODV66300500
- Félix Ladouceur - LADF71260305
- Francis Lapointe - LAPF24039801

## Fonctionnalités de l'application :
L'application est un gestionnaire de tâches en ligne de commande qui propose les fonctionnalités suivantes :
1. **Ajout d'une tâche** : Créer une nouvelle tâche avec un titre obligatoire, priorité par défaut (Normale), échéance optionnelle, identifiant auto-généré et statut À faire.
2. **Affichage des tâches** : Lister toutes les tâches enregistrées (pour chacune, son identifiant, son titre, sa priorité, son échéance ou l'absence d'échéance, et son statut).
3. **Marquage comme terminée** : Changer l'état d'une tâche de "en cours" à "terminée".
4. **Modification de la priorité** : Ajuster le niveau de priorité d'une tâche existante (ex: Basse, Normale, Élevée).
5. **Gestion de l'échéance** : Ajout, modification ou suppression d'échéance. Rejet automatique des dates invalides.
6. **Sauvegarde et rechargement** : Sauvegarde manuelle et rechargement automatique au lancement. Gestion des fichiers corrompues et des erreurs de sauvegarde.

## Prérequis et version du JDK
**Java Development Kit (JDK)** : La version 21 ou supérieure est recommandée.  
**Outils optionnels** : Aucun outil externe requis.  

## Commandes pour compiler et exécuter l'application :  
1. Décompresser le fichier zip du projet dans un dossier désiré.
2. Naviguer jusqu'a l'emplacement où le zip du projet a été décompressé avec la ligne de commande.
3. Utiliser la commande suivante à l'emplacement des fichiers .java : `javac *.java ` suivi de `java Main`

### Les commandes pour utiliser l'application : 
1. Une fois le programme lancé, l'utilisateur se trouve dans le menu principal. 4 options s'offrent à lui ; 
- Appuyer sur la touche "1" et lancer la commande ajouter une tâche; 
- Appuyer sur la touche "2" et lancer la commande afficher les tâches; 
- Appuyer sur la touche "3" et lancer la commande sauvegarder;
- Appuyer sur la touche "4" et lancer la commande quitter l'application;

2. Après avoir séléctionné "ajouter une tâche, l'utilisateur sera dirigé vers le menu ajouter tâche et devra saisir un titre et peut préciser une priorité et une échéance pour la nouvelle tâche. 

3. Après avoir slectionné "afficher les tâches" l'utilisateur peut ; 
- contenu à valider avec la navigation

## Exemple d'utilisation :  
Exemple ici  

## Format et emplacement du fichier de données :  
Le fichier de donée est stocké à la racine du projet sous le nom de *fichier_sauvegarde.csv*. Nous avons décidé d'utiliser le format csv pour sa simplicité et sa structure tabulaire. Cela simplifie la conversion des données en objet java tout en restant facilement lisible par l'humain. 

La sauvegarde est déclenchée par l'usager via la commande '3' dans le menu principal. La classe Sauvegarder valide si un fichier existe et si non elle en crée un. L'ajout s'effectue en mode append pour ne pas écraser les données existantes. En cas d'erreur de sauvegarde un message est envoyé à l'utilisateur.

## Fonctionnement de la sauvegarde et du rechargement :  
Le recharggement est effectué au démarrage de l'application. Si le fichier de sauvegarde n'existe pas une nouvelle liste vide est initialisée. Si le fichier existe déjà, l'application lit le contenu et recrée les objets en mémoir en conservant l'information d'origine, les nouvelles tâches recoivent un identifiant distinct des anciennes tâches. Si le fichier est illisible ou corrompu l'exécution est interrompue et un message est envoyé à l'utilisateur. 

## Limites et anomalies connues : 
Limites ici
