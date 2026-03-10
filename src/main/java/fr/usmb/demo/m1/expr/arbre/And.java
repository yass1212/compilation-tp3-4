package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class And implements Noeud {
    private Noeud gauche;
    private Noeud droite;

    public And(Noeud gauche, Noeud droite) {
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public int getValeur() {
        return (gauche.getValeur() != 0 && droite.getValeur() != 0) ? 1 : 0;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("and");
        gauche.display(level + 1);
        droite.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(AND " + gauche.toPrefixString() + " " + droite.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        // Évalue gauche AND droite → eax = 1 (vrai) ou 0 (faux).
        // and eax, ebx  ≠ 0 ssi les deux opérandes sont ≠ 0.
        int id = LabelCounter.prochain();
        StringBuilder sb = new StringBuilder();
        sb.append(gauche.genererCode());                    // eax ← gauche
        sb.append("\t push eax\n");
        sb.append(droite.genererCode());                    // eax ← droite
        sb.append("\t pop ebx\n");                          // ebx ← gauche
        sb.append("\t and eax, ebx\n");                     // eax = gauche & droite (≠0 si les deux vrais)
        sb.append("\t jnz vrai_and_").append(id).append("\n");
        sb.append("\t mov eax, 0\n");
        sb.append("\t jmp fin_and_").append(id).append("\n");
        sb.append("vrai_and_").append(id).append(":\n");
        sb.append("\t mov eax, 1\n");
        sb.append("fin_and_").append(id).append(":\n");
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        gauche.collecterVariables(variables);
        droite.collecterVariables(variables);
    }
}
