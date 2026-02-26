package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class Variable implements Noeud {
    private String nom;

    public Variable(String nom) {
        this.nom = nom;
    }

    @Override
    public int getValeur() {
        return Environnement.getVariable(nom);
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println(nom);
    }

    @Override
    public String toPrefixString() {
        return nom;
    }

    @Override
    public String genererCode() {
        return "\t mov eax, " + nom + "\n";
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        // L'utilisation d'une variable ne la déclare pas forcément ici,
        // c'est le LET qui s'en charge.
    }
}
