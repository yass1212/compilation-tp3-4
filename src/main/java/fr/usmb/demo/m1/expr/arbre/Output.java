package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class Output implements Noeud {
    private Noeud expression;

    public Output(Noeud expression) {
        this.expression = expression;
    }

    @Override
    public int getValeur() {
        int val = expression.getValeur();
        System.out.println("OUTPUT: " + val);
        return val;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("output");
        expression.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(OUTPUT " + expression.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        return expression.genererCode() + "\t out eax\n";
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        expression.collecterVariables(variables);
    }
}
