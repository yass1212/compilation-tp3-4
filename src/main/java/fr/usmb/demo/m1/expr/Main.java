package fr.usmb.demo.m1.expr;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.usmb.demo.m1.expr.arbre.Noeud;

public class Main {
    public static void main(String[] args) throws Exception {
        Lexer lexer;
        String asmFile = "output.asm";

        if (args.length > 0) {
            lexer = new Lexer(new FileReader(args[0]));
            // Dérive le nom du fichier .asm à partir de l'entrée
            asmFile = args[0].replaceAll("\\.[^.]+$", "") + ".asm";
        } else {
            lexer = new Lexer(new InputStreamReader(System.in));
        }

        @SuppressWarnings("deprecation")
        Parser p = new Parser(lexer);
        try {
            java_cup.runtime.Symbol result = p.parse();
            Noeud arbre = (Noeud) result.value;
            if (arbre != null) {
                System.out.println("Arbre abstrait généré (indente) :");
                arbre.display(0);
                System.out.println("Arbre abstrait généré (préfixé) :");
                System.out.println(arbre.toPrefixString());

                // Collecte des variables déclarées (pour le DATA SEGMENT)
                Set<String> variables = new LinkedHashSet<>();
                arbre.collecterVariables(variables);

                // Construction du code assembleur complet
                StringBuilder codeAsm = new StringBuilder();
                codeAsm.append("DATA SEGMENT\n");
                for (String var : variables) {
                    codeAsm.append("\t").append(var).append(" DD\n");
                }
                codeAsm.append("DATA ENDS\n");
                codeAsm.append("CODE SEGMENT\n");
                codeAsm.append(arbre.genererCode());
                codeAsm.append("CODE ENDS\n");

                // Affichage console
                System.out.println("\nCode généré :");
                System.out.print(codeAsm);

                // Écriture dans le fichier .asm pour vm-0.9.jar
                try (PrintWriter pw = new PrintWriter(new FileWriter(asmFile))) {
                    pw.print(codeAsm);
                }
                System.out.println("\n→ Code assembleur écrit dans : " + asmFile);
            }
        } catch (Exception e) {
            System.err.println("Erreur d'analyse : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
