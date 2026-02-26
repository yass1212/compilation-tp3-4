package fr.usmb.demo.m1.expr.arbre;



import java.util.Set;

public class Expression implements Noeud {

    int valeur;

    public Expression(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public int getValeur() {
        return this.valeur;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println(valeur);
    }

    @Override
    public String toPrefixString() {
        return Integer.toString(valeur);
    }

    @Override
    public String genererCode() {
        return "\t mov eax, " + valeur + "\n";
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        // Rien à collecter pour une constante
    }
}
