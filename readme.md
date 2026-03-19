
## TP 3-4 Compilation

Auteur : Yassine Benkirane



## Présentation du code
Voici comment le projet est structuré :

- **expr.jflex** : Définit les règles lexicales pour identifier les mots.
- **expr.cup** : Définit la grammaire. Il lit les mots de JFlex et construit l'arbre (AST) qui représente les instructions du code.
- **Dossier `arbre`** : Contient tous les opérateurs du langage (`And`, `Or`, `If`, `While`, etc.). Chaque élément possède deux fonctions principales :
  - `collecterVariables()` : Pour collecter les variables de l'opération.
  - `genererCode()` : Pour se traduire lui-même en instructions assembleur (ASM) pour la machine virtuelle.
- **`Main.java`** : Définit le compilateur. Il lit un fichier `.txt`, lance la construction de l'arbre, l'affiche dans le terminal, et crée le fichier `.asm`.
- **`TestArbre.java`** : Un script de test automatique qui vérifie si l'arbre est correctement construit et interprété (avec `getValeur()`).
- **`TestMachineVirtuelle.java`** : Permet de vérifier que le code assembleur généré donne le bon résultat final une fois qu'il tourne dans la machine virtuelle.

## Exercices

### Exercice 1

```bash
java -jar build/libs/tp3-4-fat.jar test_exerc1.txt 2>&1
```


### Exercice 2

```bash
java -jar build/libs/tp3-4-fat.jar pgcd.txt 2>&1
```

Pour utiliser le pgcd.asm :
Il faudra ensuite fournir les 2 valeurs pour calculer le PGCD.
```bash
java -jar vm-0.9.jar pgcd.asm
```

Pour tester le PGCD avec des valeurs passées directement via le terminal :

```bash
# Test PGCD(12, 8)
echo "12\n8" | java -jar vm-0.9.jar pgcd.asm 2>&1
# Test PGCD(15, 10)
echo "15\n10" | java -jar vm-0.9.jar pgcd.asm 2>&1
```




## Exemples de code

Ces 4 exemples (dans le dossier `exemples-interessants/`) permettent de vérifier le compilateur.

### Exemple 1 : Priorité des opérateurs (`Exemple1_Priorite.txt`)
Vérifie le respect de l'ordre mathématique : calcule `2 + 3 * 4` et doit afficher `14` (et non `20`).
```bash
java -jar build/libs/tp3-4-fat.jar exemples-interessants/Exemple1_Priorite.txt
java -jar vm-0.9.jar exemples-interessants/Exemple1_Priorite.asm
```

### Exemple 2 : Entrées, Sorties et Variables (`Exemple2_InputOutput.txt`)
Demande un nombre à l'utilisateur, le multiplie par 2, puis affiche le résultat. *(Exemple : si on entre `5`, affiche `10`)*.
```bash
java -jar build/libs/tp3-4-fat.jar exemples-interessants/Exemple2_InputOutput.txt
echo "5" | java -jar vm-0.9.jar exemples-interessants/Exemple2_InputOutput.asm
```

### Exemple 3 : Condition If/Else (`Exemple3_IfElse.txt`)
Demande un nombre et vérifie s'il est strictement positif (`x > 0`). *(Exemple : si on entre `-10`, affiche `0`. Si on entre `45`, affiche `1`)*.
```bash
java -jar build/libs/tp3-4-fat.jar exemples-interessants/Exemple3_IfElse.txt
echo "-10" | java -jar vm-0.9.jar exemples-interessants/Exemple3_IfElse.asm
echo "45" | java -jar vm-0.9.jar exemples-interessants/Exemple3_IfElse.asm
```

### Exemple 4 : La boucle While (`Exemple4_While.txt`)
Compte à rebours d'une boucle `while`. Doit afficher `3`, `2` puis `1` et s'arrêter.
```bash
java -jar build/libs/tp3-4-fat.jar exemples-interessants/Exemple4_While.txt
java -jar vm-0.9.jar exemples-interessants/Exemple4_While.asm
```











## Tests

### Évaluation opportuniste via un court-circuit

Exécute du début à la fin un test prouvant que le court-circuit du `and` (la non-évaluation du terme de droite) fonctionne avec l'assembleur généré et exécuté :

```bash
./test_short_circuit.sh
```



















## Commandes

Voici toutes les commandes pour compiler le projet, générer l'assembleur, lancer la machine virtuelle et exécuter les tests :

### 1. Compiler le projet
Construire le projet et générer le `.jar` exécutable :
```bash
./gradlew build
```

ou

```bash
./gradlew fatJar 2>&1
```

### 2. Lancer le compilateur pour générer l'assembleur
Pour analyser un fichier `.txt` avec l'arbre syntaxique abstrait (AST) et générer son équivalent `.asm` :
```bash
java -jar build/libs/tp3-4-fat.jar test-arbre/Addition.txt
```
*(Le fichier généré sera enregistré dans le même dossier sous le nom `Addition.asm`)*.

### 3. Exécuter un fichier ASM dans la machine virtuelle
Une fois le `.asm` généré, vous pouvez l'exécuter dans la VM :
```bash
java -jar vm-0.9.jar test-arbre/Addition.asm
```

## Tests des opérateurs

### Tests de l'arbre abstrait
Vérifie la bonne construction de l'AST et son interprétation via `getValeur()` sur l'ensemble des fichiers `test-arbre/*.txt` :
```bash
./gradlew testArbre
```

### Tests de la machine virtuelle
Vérifie que l'assembleur généré pour tous les fichiers `.txt` de test est bien compatible avec la VM et donne le bon résultat lors de l'exécution :
```bash
./gradlew testVM
```
