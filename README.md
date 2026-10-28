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
3. **Marquage comme terminée** : Changer l'état d'une tâche de "à faire" à "terminée".
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

Taskflow permet de gérer une liste de tâche à partir d'une interface en ligne de commande. Voici un exemple illustrant les principales fonctionnalités de l'application.

### 1. Démarrer l'application

Au démarrage, Taskflow charge automatiquement les tâches précédemment sauvegardées, lorsqu'un fichier de sauvegarde est disponible. L'utilisateur accès ensuite au menu principal pour naviguer entre les différentes fonctionnalités. 

### 2. Ajouter des tâches

L'utilisateur peut ajouter des tâches en fournissant les informations demandées.

Par exemple:

- **Tâche 1:** Vérifier la sauvegarde du serveur
    - Identifiant: Généré automatiquement
    - Priorité: Normale (par défaut)
    - Échéance: 15/11/2026 à 11h25
    - Statut: À faire
- **Tâche 2:** Mettre à jour le guide utilisateur
    - Identifiant: Généré automatiquement
    - Priorité: Basse
    - Échéance: 28/11/2026 à 16h00
    - Statut: À faire

Chaque tâche reçoit automatiquement un identifiant unique et un statut initial "À faire". 

### 3. Consulter les tâches

Après leur création, les tâches sont accessibles à partir de l'option permettant d'afficher la liste.

L'application présente les informations suivantes pour chaque tâche:

- Son identifiant;
- Son titre;
- Sa priorité;
- Son échéance;
- Son statut.

L'utilisateur peut sélectionner une tâche dans la liste afin d'accéder à son interface de gestion.

### 4. Modifier une tâche

En sélectionnant une tâche, l'utilisateur accède à son interface de gestion. Il peut notamment modifier sa priorité en choisissant parmi les niveaux suivants:

- Basse;
- Normale;
- Élevée;

Par exemple, la priorité de "Vérifier la sauvegarde du serveur" peut être chagée de **Normale** à **Élevée**. La modification est appliquée après confirmation.

### 5. Gérer les échéances

L'utilisateur peut modifier l'échéance d'une tâche existante.

Par exemple, il peut modifier l'échéance à la tache *Mettre à jour le guide utilisateur*. L'application vérifie la validité des dates saisies et rejette automatiquement les dates invalides afin de préserver la cohérence des données.

### 6. Marquer une tâche comme terminée

Lorsqu'une tâche est accomplie, l'utilisateur peut modifier son statut à partir de son interface de gestion.

Par exemple, le statut de "Vérifier la sauvegarde du serveur" passe de **À faire** à **Terminée**. Le changement est ensuite visible dans la liste des tâches.

### 7. Sauvegarder les données

L'utilisateur peut sauvegarder manuellement les tâches enregistrées. Cette opération permet de conserver les modifications effectuées.

Au prochain lancement de l'application, les données sont automatiquement rechargées à partir du fichier de sauvegarde.

TaskFlow prend également en charge la gestion des erreurs liées à la sauvegarde, notamment les fichiers corrompus et les problèmes d'écriture.

### 8. Résultat

À la suite de ces opérations, la liste reflète les modifications effectuées

|Tâche|Priorité|Échéance|Statut|
|:----|:-------|:-------|:-----|
|Vérifier la sauvegarde du serveur|Élevée|15/11/2026|Terminée|
|Mettre à jour le guide utilisateur|Basse|28/11/2026|À faire|

Les modifications peuvent ensuite être sauvegardées et retrouvées automatiquement lors d'une prochaine exécution de TaskFlow. 

## Format et emplacement du fichier de données :  
Le fichier de données est stocké à la racine du projet sous le nom de *fichier_sauvegarde.csv*. Nous avons décidé d'utiliser le format csv pour sa simplicité et sa structure tabulaire. Cela simplifie la conversion des données en objet java tout en restant facilement lisible par l'humain. 

La sauvegarde est déclenchée par l'usager via la commande '3' dans le menu principal. La classe Sauvegarder valide si un fichier existe et si non elle en crée un. L'ajout s'effectue en mode append pour ne pas écraser les données existantes. En cas d'erreur de sauvegarde un message est envoyé à l'utilisateur.

## Fonctionnement de la sauvegarde et du rechargement :  
Le rechargement est effectué au démarrage de l'application. Si le fichier de sauvegarde n'existe pas une nouvelle liste vide est initialisée. Si le fichier existe déjà, l'application lit le contenu et recrée les objets en mémoire en conservant l'information d'origine, les nouvelles tâches recoivent un identifiant distinct des anciennes tâches. Si le fichier est illisible ou corrompu l'exécution est interrompue et un message est envoyé à l'utilisateur. 

## Limites et anomalies connues : 
Limites ici

## Livrables

### Récits Utilisateurs

- [Ajouter une tâche](https://github.com/gyoo18/TaskFlow/issues/1)
- [Lister les tâches](https://github.com/gyoo18/TaskFlow/issues/38)
- [Gérer l'échéance d'une tâche](https://github.com/gyoo18/TaskFlow/issues/5)
- [Modifier la priorité d'une tâche](https://github.com/gyoo18/TaskFlow/issues/4)
- [Marquer une tâche comme terminée](https://github.com/gyoo18/TaskFlow/issues/3)
- [Quitter l'application](https://github.com/gyoo18/TaskFlow/issues/7)
- [Système de sauvegarde](https://github.com/gyoo18/TaskFlow/issues/6)
- [Système de chargement des données](https://github.com/gyoo18/TaskFlow/issues/12)
