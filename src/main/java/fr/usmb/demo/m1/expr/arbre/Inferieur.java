package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class Inferieur implements Noeud {
    private Noeud gauche;
    private Noeud droite;

    public Inferieur(Noeud gauche, Noeud droite) {
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public int getValeur() {
        return (gauche.getValeur() < droite.getValeur()) ? 1 : 0;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("<");
        gauche.display(level + 1);
        droite.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(< " + gauche.toPrefixString() + " " + droite.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        // Évalue gauche < droite  → eax = 1 si vrai, 0 sinon.
        // Technique : on calcule gbx-eax (sub ebx,eax), les flags CPU indiquent <
        int id = LabelCounter.prochain();
        StringBuilder sb = new StringBuilder();
        sb.append(gauche.genererCode());                    // eax ← gauche
        sb.append("\t push eax\n");
        sb.append(droite.genererCode());                    // eax ← droite
        sb.append("\t pop ebx\n");                          // ebx ← gauche
        sb.append("\t sub ebx, eax\n");                     // flags ← gauche - droite
        sb.append("\t jl vrai_jl_").append(id).append("\n");
        sb.append("\t mov eax, 0\n");
        sb.append("\t jmp fin_jl_").append(id).append("\n");
        sb.append("vrai_jl_").append(id).append(":\n");
        sb.append("\t mov eax, 1\n");
        sb.append("fin_jl_").append(id).append(":\n");
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        gauche.collecterVariables(variables);
        droite.collecterVariables(variables);
    }
}
