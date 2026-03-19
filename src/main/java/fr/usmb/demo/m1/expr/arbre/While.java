package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class While implements Noeud {
    private Noeud condition;
    private Noeud corps;

    public While(Noeud condition, Noeud corps) {
        this.condition = condition;
        this.corps = corps;
    }

    @Override
    public int getValeur() {
        int dernier = 0;
        while (condition.getValeur() != 0) {
            dernier = corps.getValeur();
        }
        return dernier;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("while");
        condition.display(level + 1);
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("do");
        corps.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(WHILE " + condition.toPrefixString() + " " + corps.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        // Génère le code d'une boucle while.
        //
        // Structure assembleur :
        //   debut_while_N:
        //     <condition> → eax
        //     and eax, eax      ; positionne les drapeaux
        //     jz fin_while_N    ; sort si condition == 0 (faux)
        //     <corps>
        //     jmp debut_while_N
        //   fin_while_N:
        int id = LabelCounter.prochain();
        StringBuilder sb = new StringBuilder();
        sb.append("\t mov eax, 0\n");
        sb.append("\t push eax\n");                         // dernier = 0
        sb.append("debut_while_").append(id).append(":\n");
        sb.append(condition.genererCode());                 // eax ← condition
        sb.append("\t sub eax, 0\n");                        // s'assure que les flags sont à jour (ZF=1 si eax=0)
        sb.append("\t jz fin_while_").append(id).append("\n");
        sb.append("\t pop eax\n");                          // supprime l'ancien 'dernier'
        sb.append(corps.genererCode());                     // corps de la boucle -> eax
        sb.append("\t push eax\n");                         // consigne le nouveau 'dernier'
        sb.append("\t jmp debut_while_").append(id).append("\n");
        sb.append("fin_while_").append(id).append(":\n");
        sb.append("\t pop eax\n");                          // on récupère 'dernier' dans eax comme résultat de l'ast
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        condition.collecterVariables(variables);
        corps.collecterVariables(variables);
    }
}
