## Organisation du travail d'équipe

### Objectif du produit
L'objectif du produit est de fournir une application qui sert de gestionnaire de tâches en console qui permet la persistance des données à l'aide d'un fichier de sauvegarde en format csv.

Cette application doit permettre à l'utilisateur de : 
1. Ajouter une tâche 
2. Affichager les tâches ajoutées
3. Marquager comme terminée
4. Modification de la priorité 
5. Gestion de l'échéance 
6. Sauvegarder et recharger les données

### L'organisation de l'équipe

Pour organiser le travail d'équipe, nous avons commencé par créer des issues pour l'ensemble des tâches du projet. Ensuite, nous les avons réparties entre les membres, généralement en fonction des fonctionnalités à développer. Chaque membre a ensuite créé une branche dédiée pour sa fonctionnalité et l'a clonée en local pour y travailler. Par la suite, le code était poussé sur GitHub et une Pull Request (PR) était ouverte. Celle-ci devait obligatoirement être approuvée par un autre membre de l'équipe avant d'être fusionnée avec la branche principale. Enfin, une fois le travail validé et terminé, chacun veillait à fermer son issue.

### Backlog
[Lien vers les issues](https://github.com/gyoo18/TaskFlow/issues)

### Les choix de priorité et les estimations

### La définition de terminé

Une définition commune de "terminé" a été établie par l'équipe afin de s'assurer que chaque tâche (*issue*) soit réellement complétée avant de modifier son statut à l'état **Terminé** dans GitHub Projects.

Une tâche est considérée comme terminée lorsque les conditions suivantes sont atteintes:

1. **Critère d'acceptation satisfaits :** Tous les critères d'acceptation en lien avec l'élément sont atteints. La fonctionnalité répond au besoin de l'utilisateur décrit dans le récit utilisateur ou dans la description de la tâche.

2. **Vérifications effectuées :** Le code a été compilé et exécuté afin de vérifier son fonctionnement. Les tests pertinents à chaque fonctionnalité ont été effectués, notamment les scénarios normaux, les cas limites et la gestion des erreurs lorsque cela s'applique.

3. **Intégration dans la version commune :** Lorsque les modifications sont testées et fonctionnelles, elles sont poussées sur GitHub à l'aide d'une branche locale du développeur et ensuite intégrées à la branche commune de la fonctionnalité à la suite d'une *pull request* acceptée. Aucun conflit ou problème connu ne doit s'intégrer dans la version commune. 

4. **Revue par un autre membre de l'équipe :** Chaque *pull request* est révisée par au moins un autre membre de l'équipe. Les commentaires ou problèmes identifiés lors de cette révision de code sont traités avant l'intégration dans les branches communes.

5. **Documentation mise à jour :** La documentation pertinente est mise à jour lorsque les changements le nécessitent. Ceci peut inclure le README, les commentaires utiles dans le code ou les documents d'organisation et de vérification du projet.

Une fois toutes ces conditions atteintes, le responsable de la tâche (*issue*) peut déplacer cette dernière vers l'état **Terminé** dans GitHub Projects. Cette définition s'applique à tous les membres de l'équipe et à l'ensemble des tâches du projet afin de maintenir un niveau de qualité acceptable et une cohérence entre les activités des développeurs.

### Les objectifs et les résultats de chaque itération
Nous avons décidé de séparer le travail en 2 itérations : 

#### Itération 1 | Un premier prototype fonctionnel : 
Le but de la première itération était de produire un premier prototype fonctionnel. Nous voulions avoir une interface utilisateur complète ainsi que la fonctionnalité de base d'ajouter une tâche. 

Le résultat : Une première itération qui est exécutable et qui permet de naviguer entre les différents menus (ajouter, modifier, quitter). On peut aussi quitter le programme et ajouter une tâche. Ce qui n'est pas inclu dans cette version : Sauvegarder les données, modifier une tâche et charger les données au lancement.


#### Itération 2 | Version finale et pleinement fonctionnelle :
Le but de la deuxième itération était d'arriver à une version finale et complète de l'application. Nous voulions intégrer la persistance des données (chargement et sauvegarde automatiques) et finaliser la logique de modification des tâches.

Le résultat : Une version complète de TaskFlow qui permet d'ajouter et de modifier des tâches, de charger automatiquement les données au lancement et d'assurer une sauvegarde fiable à la requête de l'utilisateur. L'intégrité des identifiants des tâches sont garanties et l'application gère proprement les erreurs de fichiers (fichiers manquants ou corrompus).

### Les ajustements apportés à notre démarche

### La contribution de chaque membre

#### Francis Lapointe : 

#### Sébastien Roussel :

#### Félix Ladouceur :
-Implémentation de la fonctionnalité d'enregistrer une tâche localement
-Implémentation de l'option de Quitter l'application
-Acceptation de pull requests des autres membres

Liens:
-Évaluation de pull requests d'autres membres: https://github.com/gyoo18/TaskFlow/pulls?q=is%3Apr+reviewed-by%3A%40me
-Complétion d'Issues: https://github.com/gyoo18/TaskFlow/issues?q=is%3Aissue+assignee%3AMrCarnaval

#### Vincent Godbout : 

#### Hugo Thivierge :
- Documentation (readme.md & organisation.md)
- Classe charger-donnees et son implémentation (fonctionnalité du chargement automatique des données)
- Merge des fonctionalités de sauvegarde et du chargement de données

Liens :
- [Issues](https://github.com/gyoo18/TaskFlow/issues?q=is%3Aissue+state%3Aclosed+assignee%3AHugus12)
- [Pull request](https://github.com/gyoo18/TaskFlow/pulls?q=is%3Apr+state%3Aclosed+author%3AHugus12)
- [Reviews de code (approbation des pull requests de collègues)](https://github.com/gyoo18/TaskFlow/pulls?q=is%3Apr+state%3Aclosed+reviewed-by%3A%40me)
