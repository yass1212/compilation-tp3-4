package fr.usmb.demo.m1.expr;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import fr.usmb.demo.m1.expr.arbre.Environnement;
import fr.usmb.demo.m1.expr.arbre.Noeud;

public class TestArbre {

    private static final Map<String, Integer> EXPECTED_RESULTS = new HashMap<>();

    static {
        // Opérateurs de base
        EXPECTED_RESULTS.put("Addition.txt", 15);
        EXPECTED_RESULTS.put("And.txt", 0);
        EXPECTED_RESULTS.put("Division.txt", 5);
        EXPECTED_RESULTS.put("Egalite.txt", 1);
        EXPECTED_RESULTS.put("Expression.txt", 42);
        EXPECTED_RESULTS.put("If.txt", 10);
        EXPECTED_RESULTS.put("Inferieur.txt", 1);
        EXPECTED_RESULTS.put("InferieurEgal.txt", 1);
        EXPECTED_RESULTS.put("Let.txt", 10);
        EXPECTED_RESULTS.put("Modulo.txt", 1);
        EXPECTED_RESULTS.put("MoinsUnaire.txt", -5);
        EXPECTED_RESULTS.put("Multiplication.txt", 6);
        EXPECTED_RESULTS.put("Nil.txt", 0);
        EXPECTED_RESULTS.put("Not.txt", 0);
        EXPECTED_RESULTS.put("Or.txt", 1);
        EXPECTED_RESULTS.put("Output.txt", 10);
        EXPECTED_RESULTS.put("Sequence.txt", 15);
        EXPECTED_RESULTS.put("Soustraction.txt", 5);
        EXPECTED_RESULTS.put("Variable.txt", 42);
        EXPECTED_RESULTS.put("While.txt", 10);

        // Tables de vérité et cas limites
        EXPECTED_RESULTS.put("And_00.txt", 0);
        EXPECTED_RESULTS.put("And_01.txt", 0);
        EXPECTED_RESULTS.put("And_10.txt", 0);
        EXPECTED_RESULTS.put("And_11.txt", 1);
        EXPECTED_RESULTS.put("Or_00.txt", 0);
        EXPECTED_RESULTS.put("Or_01.txt", 1);
        EXPECTED_RESULTS.put("Or_10.txt", 1);
        EXPECTED_RESULTS.put("Or_11.txt", 1);
        EXPECTED_RESULTS.put("Not_0.txt", 1);
        EXPECTED_RESULTS.put("Not_1.txt", 0);
        EXPECTED_RESULTS.put("Egalite_Vrai.txt", 1);
        EXPECTED_RESULTS.put("Egalite_Faux.txt", 0);
        EXPECTED_RESULTS.put("Inferieur_Vrai.txt", 1);
        EXPECTED_RESULTS.put("Inferieur_Faux_Egal.txt", 0);
        EXPECTED_RESULTS.put("Inferieur_Faux_Sup.txt", 0);
        EXPECTED_RESULTS.put("InferieurEgal_Vrai_Inf.txt", 1);
        EXPECTED_RESULTS.put("InferieurEgal_Vrai_Egal.txt", 1);
        EXPECTED_RESULTS.put("InferieurEgal_Faux.txt", 0);
        EXPECTED_RESULTS.put("If_True.txt", 10);
        EXPECTED_RESULTS.put("If_False.txt", 20);

        // Nouveaux cas : Valeurs arbitraires et Court-circuit
        EXPECTED_RESULTS.put("And_Arbitrary.txt", 1);
        EXPECTED_RESULTS.put("Or_Arbitrary.txt", 1);
        EXPECTED_RESULTS.put("And_NonZero.txt", 1);
        EXPECTED_RESULTS.put("Or_NonZero.txt", 1);
        EXPECTED_RESULTS.put("And_ShortCircuit.txt", 10);
        EXPECTED_RESULTS.put("Or_ShortCircuit.txt", 10);
    }

    public static void main(String[] args) {
        File testDir = new File("test-arbre");
        if (!testDir.exists() || !testDir.isDirectory()) {
            System.err.println("Dossier 'test-arbre' non trouvé.");
            return;
        }

        File[] testFiles = testDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (testFiles == null || testFiles.length == 0) {
            System.out.println("Aucun fichier .txt trouvé dans 'test-arbre'.");
            return;
        }

        // Utilisation de TreeMap pour trier par nom de fichier
        Map<String, File> sortedFiles = new TreeMap<>();
        for (File f : testFiles) {
            sortedFiles.put(f.getName(), f);
        }

        int passed = 0;
        int failed = 0;
        int skipped = 0;

        System.out.println("====================================================");
        System.out.println("EXÉCUTION DES TESTS DE L'ARBRE ABSTRAIT");
        System.out.println("====================================================");
        System.out.printf("%-30s | %-8s | %-8s | %-8s%n", "Fichier", "Attendu", "Obtenu", "Résultat");
        System.out.println("--------------------------------------------------------------------");

        for (Map.Entry<String, File> entry : sortedFiles.entrySet()) {
            String fileName = entry.getKey();
            File file = entry.getValue();

            if (fileName.equals("Input.txt")) {
                System.out.printf("%-30s | %-8s | %-8s | %-8s%n", fileName, "?", "SKIP", "IGNORÉ (Interactif)");
                skipped++;
                continue;
            }

            if (!EXPECTED_RESULTS.containsKey(fileName)) {
                System.out.printf("%-30s | %-8s | %-8s | %-8s%n", fileName, "?", "?", "NON DÉFINI");
                skipped++;
                continue;
            }

            int expected = EXPECTED_RESULTS.get(fileName);
            try {
                Environnement.clear(); // Réinitialiser les variables entre chaque test
                Lexer lexer = new Lexer(new FileReader(file));
                Parser parser = new Parser(lexer);
                java_cup.runtime.Symbol result = parser.parse();
                Noeud arbre = (Noeud) result.value;

                if (arbre == null) {
                    System.out.printf("%-30s | %-8d | %-8s | %-8s%n", fileName, expected, "NULL", "ÉCHEC (Arbre vide)");
                    failed++;
                } else {
                    int actual = arbre.getValeur();
                    if (actual == expected) {
                        System.out.printf("%-30s | %-8d | %-8d | %-8s%n", fileName, expected, actual, "OK");
                        passed++;
                    } else {
                        System.out.printf("%-30s | %-8d | %-8d | %-8s%n", fileName, expected, actual, "ÉCHEC");
                        failed++;
                    }
                }
            } catch (Exception e) {
                System.out.printf("%-30s | %-8d | %-8s | %-8s%n", fileName, expected, "ERROR", "ERREUR");
                failed++;
            }
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println("RÉSUMÉ :");
        System.out.println("  PASSÉS  : " + passed);
        System.out.println("  ÉCHECS  : " + failed);
        System.out.println("  IGNORÉS : " + skipped);
        System.out.println("  TOTAL   : " + (passed + failed + skipped));
        System.out.println("====================================================");
    }
}
