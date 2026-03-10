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
        //     jz fin_while_N    ; sort si condition == 0 (faux)
        //     <corps>
        //     jmp debut_while_N
        //   fin_while_N:
        int id = LabelCounter.prochain();
        StringBuilder sb = new StringBuilder();
        sb.append("debut_while_").append(id).append(":\n");
        sb.append(condition.genererCode());                 // eax ← résultat de la condition (0 ou 1)
        sb.append("\t jz fin_while_").append(id).append("\n");
        sb.append(corps.genererCode());                     // corps de la boucle
        sb.append("\t jmp debut_while_").append(id).append("\n");
        sb.append("fin_while_").append(id).append(":\n");
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        condition.collecterVariables(variables);
        corps.collecterVariables(variables);
    }
}
