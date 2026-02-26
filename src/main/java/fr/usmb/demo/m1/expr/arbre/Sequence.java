package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class Sequence implements Noeud {
    private Noeud gauche;
    private Noeud droite;

    public Sequence(Noeud gauche, Noeud droite) {
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public int getValeur() {
        if (gauche != null) gauche.getValeur();
        return droite.getValeur();
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println(";");
        if (gauche != null) gauche.display(level + 1);
        droite.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        if (gauche == null) {
            return droite.toPrefixString();
        }
        return "(; " + gauche.toPrefixString() + " " + droite.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        StringBuilder sb = new StringBuilder();
        if (gauche != null) sb.append(gauche.genererCode());
        sb.append(droite.genererCode());
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        if (gauche != null) gauche.collecterVariables(variables);
        droite.collecterVariables(variables);
    }
}
