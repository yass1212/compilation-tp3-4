package fr.usmb.demo.m1.expr.arbre;


import java.util.Set;

public interface Noeud {
    int getValeur();
    void display(int level);
    String toPrefixString();
    String genererCode();
    void collecterVariables(Set<String> variables);
}
