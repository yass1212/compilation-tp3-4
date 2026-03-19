package fr.usmb.demo.m1.expr.arbre;

import java.util.Set;

public class MoinsUnaire implements Noeud {
    private Noeud expression;

    public MoinsUnaire(Noeud expression) {
        this.expression = expression;
    }

    @Override
    public int getValeur() {
        return -expression.getValeur();
    }

    @Override
    public void display(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("- (unaire)");
        expression.display(level + 1);
    }

    @Override
    public String toPrefixString() {
        return "(- " + expression.toPrefixString() + ")";
    }

    @Override
    public String genererCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(expression.genererCode());
        sb.append("\t push eax\n");
        sb.append("\t pop ebx\n");
        sb.append("\t mov eax, 0\n");
        sb.append("\t sub eax, ebx\n");
        return sb.toString();
    }

    @Override
    public void collecterVariables(Set<String> variables) {
        expression.collecterVariables(variables);
    }
}
