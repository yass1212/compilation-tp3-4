package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class Let implements Noeud {
    private String nom;
    private Noeud expression;

    public Let(String nom, Noeud expression) {
        this.nom = nom;
        this.expression = expression;
    }

    @Override
    public int getValeur() {
        int val = expression.getValeur();
        Environnement.setVariable(nom, val);
        return val;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("let");
        for (int i = 0; i < level + 1; i++) System.out.print("  ");
        System.out.println(nom);
        expression.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(LET " + nom + " " + expression.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(expression.genererCode());
        sb.append("\t mov ").append(nom).append(", eax\n");
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        variables.add(nom);
        expression.collecterVariables(variables);
    }
}
