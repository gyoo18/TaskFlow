## Vérification de la qualité du logiciel

### 1. Liste vide

**Comportement à vérifier :** L'application indique clairement qu'aucune tâche n'est présente.

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Inscrire réponse ici|Réponse|Réponse|Réponse|


### 2. Ajout valide

**Comportement à vérifier :** La tâche reçoint un identifiant et les valeurs par défaut attendues lorsque les informations facultatives sont omises.

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Inscrire réponse ici|Réponse|Réponse|Réponse|


### 3. Titre invalide

**Comportement à vérifier :** Un titre vide ou composé uniquement d'espaces est refusé sans ajout de tâche

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Une nouvelle tâche sans titre ainsi qu'une tâche avec un titre composé uniquement d'espace|Les deux tâches sont refusées lors de la création et aucune tâche n'est ajoutée|La tâche sans titre obtient le titre par défaut *Nouvelle Tâche* et est créée. La tâche ayant un titre composé uniquement d'espace est créée et n'a pas de titre lorsqu'elle s'affiche dans la liste des tâches.|Ceci démontre donc un petit problème avec l'implémentation actuelle de la création des tâches, il faut bloquer la création de tâche sans titre ou avec un titre composé uniquement d'espaces. Cette anomalie sera corrigée|


### 4. Liste de plusieurs tâches

**Comportement à vérifier :** Toutes les tâches sont affichées avec l'ensemble de leurs informations

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Trois tâches créées uniquement pour ce test|Les trois tâches s'affichent adéquatement, avec toutes leurs informations spécifiques|L'affichage se fait adéquatement, nous pouvons observer toutes les tâches et leurs informations|La fonctionnalité du menu permettant d'afficher les tâches est implémenté adéquatement|


### 5. Tâche terminée

**Comportement à vérifier :** Seul le statut de la tâche sélectionnée change. Une tâche déjà terminée est correctement signalée.

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Tâche "test tâche terminée" et tâche "test tâche non terminée"|Seulement la tâche "test tâche terminée" sera affichée comme étant terminée, l'autre tâche ne sera pas affectée|Comme prévu, seuelemnt la tâche "test tâche terminée" a eu un statut modifié à **Terminé**|La fonctionnalité permettant de changer le statut d'une tâche fonctionne adéquatement, et cible uniquement la tâche sélectionnée pour le changement|


### 6. Identifiant inconnu

**Comportement à vérifier :** L'applicaiton affiche un message et conserve les données existantes.

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Inscrire réponse ici|Réponse|Réponse|Réponse|


### 7. Priorité

**Comportement à vérifier :** Les valeurs autorisées sont acceptées. Une valeur invalide est refusée sans modifier la priorité actuelle.

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Inscrire réponse ici|Réponse|Réponse|Réponse|


### 8. Échéance

**Comportement à vérifier :** L'ajout, la modification et le retrait fonctionnent. Les dates valides sont acceptées, les formats incorrects et les dates impossibles sont refusés.

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Inscrire réponse ici|Réponse|Réponse|Réponse|


### 9. Sauvegarde et redémarrage

**Comportement à vérifier :** Toutes les informations sont retrouvées, y compris les identifiants, les status et les échéances absentes.

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Inscrire réponse ici|Réponse|Réponse|Réponse|


### 10. Ajout après rechargement

**Comportement à vérifier :** La nouvelle tâche reçoit un identifiant distinct de ceux des tâches déjà chargées.

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Inscrire réponse ici|Réponse|Réponse|Réponse|


### 11. Fichier absent ou mal formé

**Comportement à vérifier :** Un fichier absent permet un démarrage avec une liste vide. Un fichier mal formé ou illisible produit un message et interrompt le démarrage sans modifier le fichier.

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Inscrire réponse ici|Réponse|Réponse|Réponse|


### 12. Échec d'écriture

**Comportement à vérifier :** Une sauvegarde impossible est signalée comme un échec, sans message de réussite.

|Données utilisées|Résultat attendu|Résultat observé|Conclusion|
|:----------------|:---------------|:--------------|:----------|
|Inscrire réponse ici|Réponse|Réponse|Réponse|