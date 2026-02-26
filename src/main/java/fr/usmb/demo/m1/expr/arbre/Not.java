package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class Not implements Noeud {
    private Noeud expression;

    public Not(Noeud expression) {
        this.expression = expression;
    }

    @Override
    public int getValeur() {
        return (expression.getValeur() == 0) ? 1 : 0;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("not");
        expression.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(NOT " + expression.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        return "";
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        expression.collecterVariables(variables);
    }
}
