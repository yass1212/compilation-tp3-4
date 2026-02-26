package fr.usmb.demo.m1.expr.arbre;

import java.util.HashMap;
import java.util.Map;

public class Environnement {
    private static Map<String, Integer> variables = new HashMap<>();

    public static void setVariable(String nom, int valeur) {
        variables.put(nom, valeur);
    }

    public static int getVariable(String nom) {
        if (!variables.containsKey(nom)) {
            System.err.println("Erreur: variable non définie : " + nom);
            return 0;
        }
        return variables.get(nom);
    }
}
