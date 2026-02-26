package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class Nil implements Noeud {
    @Override
    public int getValeur() {
        return 0;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("nil");
    }

    @Override
    public String toPrefixString() {
        return "NIL";
    }

    @Override
    public String genererCode() {
        return "";
    }

    @Override
    public void collecterVariables(Set<String> variables) {
    }
}
