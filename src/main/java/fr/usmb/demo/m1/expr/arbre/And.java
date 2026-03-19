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
        int id = LabelCounter.prochain();
        StringBuilder sb = new StringBuilder();
        
        sb.append(gauche.genererCode());                      // eax ← gauche
        sb.append("\t sub eax, 0\n");                         // Astuce pour forcer la mise à jour des flags (ZF=1 si eax=0)
        sb.append("\t jz faux_and_").append(id).append("\n"); // Évaluation opportuniste : si gauche est faux, on saute droite
        
        sb.append(droite.genererCode());                      // eax ← droite
        sb.append("\t sub eax, 0\n");                         
        sb.append("\t jz faux_and_").append(id).append("\n"); // Si droite est faux, le AND est faux
        
        sb.append("\t mov eax, 1\n");                         // Les deux sont vrais, le résultat final est vrai (1)
        sb.append("\t jmp fin_and_").append(id).append("\n");
        
        sb.append("faux_and_").append(id).append(":\n");
        sb.append("\t mov eax, 0\n");                         // Au moins un des deux était faux, le résultat est 0
        
        sb.append("fin_and_").append(id).append(":\n");
        
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        gauche.collecterVariables(variables);
        droite.collecterVariables(variables);
    }
}
