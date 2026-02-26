package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class Modulo implements Noeud {
    private Noeud gauche;
    private Noeud droite;

    public Modulo(Noeud gauche, Noeud droite) {
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public int getValeur() {
        int d = droite.getValeur();
        if (d == 0) {
            System.err.println("Erreur: modulo par zéro.");
            return 0;
        }
        return gauche.getValeur() % d;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("mod");
        gauche.display(level + 1);
        droite.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(mod " + gauche.toPrefixString() + " " + droite.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(gauche.genererCode());
        sb.append("\t push eax\n");
        sb.append(droite.genererCode());
        sb.append("\t pop ebx\n");
        sb.append("\t mod ebx, eax\n");
        sb.append("\t mov eax, ebx\n");
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        gauche.collecterVariables(variables);
        droite.collecterVariables(variables);
    }
}
