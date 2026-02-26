package fr.usmb.demo.m1.expr.arbre;

import java.util.Scanner;
import java.util.Set;

public class Input implements Noeud {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public int getValeur() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.err.println("Erreur: entrée non valide (entier attendu)");
            if (scanner.hasNext()) scanner.next(); // consommer l'entrée invalide
            return 0;
        }
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("input");
    }

    @Override
    public String toPrefixString() {
        return "INPUT";
    }

    @Override
    public String genererCode() {
        return "\t in eax\n";
    }

    @Override
    public void collecterVariables(Set<String> variables) {
    }
}
