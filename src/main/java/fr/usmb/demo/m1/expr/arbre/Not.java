package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class Not implements Noeud {
    private Noeud expression;

    public Not(Noeud expression) {
        this.expression = expression;
    }

    @Override
    public int getValeur() {
        return (expression.getValeur() == 0) ? 1 : 0;
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("not");
        expression.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(NOT " + expression.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        // Évalue NOT expr → eax = 1 si expr==0, sinon 0.
        int id = LabelCounter.prochain();
        StringBuilder sb = new StringBuilder();
        sb.append(expression.genererCode());                // eax ← expr
        sb.append("\t jz vrai_not_").append(id).append("\n");
        sb.append("\t mov eax, 0\n");
        sb.append("\t jmp fin_not_").append(id).append("\n");
        sb.append("vrai_not_").append(id).append(":\n");
        sb.append("\t mov eax, 1\n");
        sb.append("fin_not_").append(id).append(":\n");
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        expression.collecterVariables(variables);
    }
}
