package fr.usmb.demo.m1.expr;

import java.io.*;
import java.util.*;

import fr.usmb.demo.m1.expr.arbre.Environnement;
import fr.usmb.demo.m1.expr.arbre.Noeud;

public class TestMachineVirtuelle {

    private static final Map<String, Integer> EXPECTED_RESULTS = new HashMap<>();

    static {
        // [Copying expected results from TestArbre]
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
        if (testFiles == null || testFiles.length == 0) return;

        Map<String, File> sortedFiles = new TreeMap<>();
        for (File f : testFiles) sortedFiles.put(f.getName(), f);

        int passed = 0, failed = 0, skipped = 0;

        System.out.println("====================================================");
        System.out.println("EXÉCUTION DES TESTS ASM DANS LA MACHINE VIRTUELLE");
        System.out.println("====================================================");
        System.out.printf("%-30s | %-8s | %-8s | %-8s%n", "Fichier", "Attendu", "Obtenu", "Résultat");
        System.out.println("--------------------------------------------------------------------");

        for (Map.Entry<String, File> entry : sortedFiles.entrySet()) {
            String fileName = entry.getKey();
            File txtFile = entry.getValue();

            if (fileName.equals("Input.txt")) {
                skipped++;
                continue;
            }

            if (!EXPECTED_RESULTS.containsKey(fileName)) {
                skipped++;
                continue;
            }

            int expected = EXPECTED_RESULTS.get(fileName);
            String asmFileName = txtFile.getAbsolutePath().replace(".txt", ".asm");
            File asmFile = new File(asmFileName);

            try {
                // 1. Compiler le code Java en ASM via le générateur (comme le fait Main.java)
                Environnement.clear();
                Lexer lexer = new Lexer(new FileReader(txtFile));
                Parser parser = new Parser(lexer);
                java_cup.runtime.Symbol result = parser.parse();
                Noeud arbre = (Noeud) result.value;

                if (arbre == null) {
                    System.out.printf("%-30s | %-8d | %-8s | %-8s%n", fileName, expected, "NULL", "ÉCHEC compilation");
                    failed++;
                    continue;
                }

                Set<String> variables = new LinkedHashSet<>();
                arbre.collecterVariables(variables);

                StringBuilder codeAsm = new StringBuilder();
                codeAsm.append("DATA SEGMENT\n");
                for (String var : variables) {
                    codeAsm.append("\t").append(var).append(" DD\n");
                }
                codeAsm.append("DATA ENDS\n");
                codeAsm.append("CODE SEGMENT\n");
                codeAsm.append(arbre.genererCode());
                
                // On ajoute une instruction OUT EAX à la fin pour obliger la VM à recracher le résultat final 
                // (sauf si c'est Output.txt qui le fait déjà, mais ce n'est pas grave ça affichera 2 fois)
                codeAsm.append("\t out eax\n");
                
                codeAsm.append("CODE ENDS\n");

                try (PrintWriter pw = new PrintWriter(new FileWriter(asmFile))) {
                    pw.print(codeAsm);
                }

                // 2. Exécuter le .asm dans vm-0.9.jar
                ProcessBuilder pb = new ProcessBuilder("java", "-jar", "vm-0.9.jar", asmFileName);
                pb.redirectErrorStream(true);
                Process process = pb.start();
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                String finalResult = null;
                boolean syntaxError = false;
                
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Erreur de syntaxe") || line.contains("Exception")) {
                        syntaxError = true;
                    }
                    if (line.startsWith(">>>>") && !line.contains("That's all")) {
                        // On prend le dernier match avant "That's all"
                        String val = line.substring(4).trim();
                        if (!val.isEmpty()) finalResult = val;
                    }
                }
                
                process.waitFor();

                if (syntaxError || finalResult == null) {
                    System.out.printf("%-30s | %-8d | %-8s | %-8s%n", fileName, expected, "CRASH", "ÉCHEC SYNTAXE VM");
                    failed++;
                } else {
                    int actual = Integer.parseInt(finalResult);
                    if (actual == expected) {
                        System.out.printf("%-30s | %-8d | %-8d | %-8s%n", fileName, expected, actual, "OK");
                        passed++;
                    } else {
                        System.out.printf("%-30s | %-8d | %-8d | %-8s%n", fileName, expected, actual, "ÉCHEC MAUVAIS RESULTAT");
                        failed++;
                    }
                }
            } catch (Exception e) {
                System.out.printf("%-30s | %-8d | %-8s | %-8s%n", fileName, expected, "ERR", "ÉCHEC FATAL");
                e.printStackTrace();
                failed++;
            }
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println("RÉSUMÉ MACHINE VIRTUELLE :");
        System.out.println("  PASSÉS  : " + passed);
        System.out.println("  ÉCHECS  : " + failed);
        System.out.println("  IGNORÉS : " + skipped);
        System.out.println("  TOTAL   : " + (passed + failed + skipped));
        System.out.println("====================================================");
    }
}
