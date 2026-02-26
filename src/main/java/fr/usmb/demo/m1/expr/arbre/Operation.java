package fr.usmb.demo.m1.expr.arbre;



import java.util.Set;

public interface Operation extends Noeud {

    String symbole = "";

    public int Calcul(Noeud exp1, Noeud exp2);

    public int getOperation();
}
