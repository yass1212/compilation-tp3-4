package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class InferieurEgal implements Noeud {
    private Noeud gauche;
    private Noeud droite;

    public InferieurEgal(Noeud gauche, Noeud droite) {
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public int getValeur() {
        return (gauche.getValeur() <= droite.getValeur()) ? 1 : 0;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("<=");
        gauche.display(level + 1);
        droite.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(<= " + gauche.toPrefixString() + " " + droite.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        return "";
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        gauche.collecterVariables(variables);
        droite.collecterVariables(variables);
    }
}
