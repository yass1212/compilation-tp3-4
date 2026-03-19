#!/bin/bash
echo "Création du fichier de test source..."
cat << 'EOF' > test-arbre/Test_Opportuniste_Exe.txt
let x = 10; 
0 and (let x = 20); 
output x.
EOF

echo "Compilation de Test_Opportuniste_Exe.txt en ASM..."
./gradlew build -q
java -jar build/libs/tp3-4-fat.jar test-arbre/Test_Opportuniste_Exe.txt > /dev/null

echo "Exécution dans la VM..."
RESULT=$(java -jar vm-0.9.jar test-arbre/Test_Opportuniste_Exe.asm)

echo "Résultat de la VM : $RESULT"

# On s'attend à ce que le résultat contienne "10" si c'est opportuniste (x=10 conservé)
# Et "20" si le court-circuit n'est PAS respecté (le let x=20 aura été exécuté à tort)
if echo "$RESULT" | grep -q '10'; then
    echo "✅ SUCCÈS : L'évaluation opportuniste (court-circuit) fonctionne parfaitement ! La droite n'a pas été évaluée, x est bien resté à 10."
    exit 0
else
    echo "❌ ÉCHEC : La partie droite (let x = 20) a été exécutée alors que 'gauche' était 0 ! L'évaluation opportuniste n'est pas respectée."
    exit 1
fi
