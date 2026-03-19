package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class Or implements Noeud {
    private Noeud gauche;
    private Noeud droite;

    public Or(Noeud gauche, Noeud droite) {
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public int getValeur() {
        return (gauche.getValeur() != 0 || droite.getValeur() != 0) ? 1 : 0;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("or");
        gauche.display(level + 1);
        droite.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(OR " + gauche.toPrefixString() + " " + droite.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        // Évaluation en court-circuit (short-circuit evaluation)
        // si gauche est vrai (≠0), résultat = 1
        // sinon, résultat = évaluation de droite (normalisée à 0 ou 1)
        int id = LabelCounter.prochain();
        StringBuilder sb = new StringBuilder();
        
        sb.append(gauche.genererCode());                    // eax ← gauche
        sb.append("\t sub eax, 0\n");                       // positionne les drapeaux avec une commande acceptée (ZF=1 si eax=0)
        sb.append("\t jnz vrai_or_").append(id).append("\n"); // si gauche ≠ 0, saute à vrai
        
        sb.append(droite.genererCode());                    // eax ← droite
        sb.append("\t sub eax, 0\n");                       // positionne les drapeaux
        sb.append("\t jnz vrai_or_").append(id).append("\n"); // si droite ≠ 0, saute à vrai
        
        sb.append("\t mov eax, 0\n");                       // les deux sont faux
        sb.append("\t jmp fin_or_").append(id).append("\n");
        
        sb.append("vrai_or_").append(id).append(":\n");
        sb.append("\t mov eax, 1\n");                       // l'un des deux est vrai
        
        sb.append("fin_or_").append(id).append(":\n");
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        gauche.collecterVariables(variables);
        droite.collecterVariables(variables);
    }
}
