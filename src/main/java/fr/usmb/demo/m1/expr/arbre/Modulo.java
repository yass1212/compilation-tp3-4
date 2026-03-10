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
        // La VM n'a pas d'instruction 'mod'.
        // On calcule : gauche mod droite  =  gauche - (gauche / droite) * droite
        // Registres : ebx = gauche (dividende)   eax = droite (diviseur)   ecx = diviseur sauvegardé
        StringBuilder sb = new StringBuilder();
        sb.append(gauche.genererCode());      // eax ← gauche
        sb.append("\t push eax\n");
        sb.append(droite.genererCode());      // eax ← droite
        sb.append("\t pop ebx\n");            // ebx ← gauche
        sb.append("\t mov ecx, eax\n");       // ecx ← droite (sauvegarde du diviseur)
        sb.append("\t mov eax, ebx\n");       // eax ← gauche  (dividende dans eax pour div)
        sb.append("\t div ebx, ecx\n");       // ebx ← quotient (gauche / droite)
        sb.append("\t mul ebx, ecx\n");       // ebx ← quotient * droite
        sb.append("\t sub eax, ebx\n");       // eax ← gauche - quotient*droite  = reste
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        gauche.collecterVariables(variables);
        droite.collecterVariables(variables);
    }
}
