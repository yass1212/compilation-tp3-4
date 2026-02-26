package fr.usmb.demo.m1.expr;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.usmb.demo.m1.expr.Lexer;
import fr.usmb.demo.m1.expr.Parser;
import fr.usmb.demo.m1.expr.arbre.Noeud;

public class Main {
    public static void main(String[] args) throws Exception {
        Lexer lexer;
        if (args.length > 0)
            lexer = new Lexer(new FileReader(args[0]));
        else
            lexer = new Lexer(new InputStreamReader(System.in));
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
                System.out.println("Évaluation : " + arbre.getValeur());

                // Génération de code
                System.out.println("\nCode généré :");
                Set<String> variables = new LinkedHashSet<>();
                arbre.collecterVariables(variables);
                
                System.out.println("DATA SEGMENT");
                for (String var : variables) {
                    System.out.println("\t " + var + " DD");
                }
                System.out.println("DATA ENDS");
                
                System.out.println("CODE SEGMENT");
                System.out.print(arbre.genererCode());
                System.out.println("CODE ENDS");
            }
        } catch (Exception e) {
            System.err.println("Erreur d'analyse : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
