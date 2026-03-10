package fr.usmb.demo.m1.expr.arbre;

/**
 * Compteur de labels global partagé par tous les nœuds de l'AST.
 * Chaque appel à {@code prochain()} renvoie un entier unique, ce qui
 * garantit que les étiquettes générées (debut_while_N, fin_if_N, etc.)
 * ne se chevauchent pas dans le code assembleur produit.
 */
public class LabelCounter {
    private static int compteur = 0;

    private LabelCounter() { /* classe utilitaire, pas d'instance */ }

    /** Renvoie le prochain identifiant disponible et l'incrémente. */
    public static int prochain() {
        return compteur++;
    }
}
