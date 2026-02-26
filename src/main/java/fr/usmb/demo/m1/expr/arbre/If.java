package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class If implements Noeud {
    private Noeud condition;
    private Noeud alors;
    private Noeud sinon;

    public If(Noeud condition, Noeud alors, Noeud sinon) {
        this.condition = condition;
        this.alors = alors;
        this.sinon = sinon;
    }

    @Override
    public int getValeur() {
        if (condition.getValeur() != 0) {
            return alors.getValeur();
        } else {
            return sinon.getValeur();
        }
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("if");
        condition.display(level + 1);
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("then");
        alors.display(level + 1);
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("else");
        sinon.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(IF " + condition.toPrefixString() + " " + alors.toPrefixString() + " " + sinon.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        return "";
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        condition.collecterVariables(variables);
        alors.collecterVariables(variables);
        sinon.collecterVariables(variables);
    }
}
