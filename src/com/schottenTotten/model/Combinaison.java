package model;

import java.util.List;

public class Combinaison implements Comparable<Combinaison> {

    public enum Type {
        SOMME(1), SUITE(2), COULEUR(3), BRELAN(4), SUITE_COULEUR(5);
        
        private final int force;
        Type(int force) { this.force = force; }
        public int getForce() { return force; }
    }

    private Type type;
    private int sommeValeurs; 

    public Combinaison(List<Carte> cartes) {
        if (cartes == null || cartes.size() != 3) {
            throw new IllegalArgumentException("Une combinaison nécessite exactement 3 cartes.");
        }
        
        analyserCartes(cartes);
    }

    private void analyserCartes(List<Carte> cartes) {
        this.sommeValeurs = cartes.get(0).valeur + cartes.get(1).valeur + cartes.get(2).valeur;

        boolean isCouleur = verifierCouleur(cartes);
        boolean isSuite = verifierSuite(cartes);
        boolean isBrelan = verifierBrelan(cartes);

        if (isSuite && isCouleur) {
            this.type = Type.SUITE_COULEUR;
        } else if (isBrelan) {
            this.type = Type.BRELAN;
        } else if (isCouleur) {
            this.type = Type.COULEUR;
        } else if (isSuite) {
            this.type = Type.SUITE;
        } else {
            this.type = Type.SOMME;
        }
    }

    private boolean verifierCouleur(List<Carte> cartes) {
        return cartes.get(0).couleur == cartes.get(1).couleur && 
               cartes.get(1).couleur == cartes.get(2).couleur;
    }

    private boolean verifierSuite(List<Carte> cartes) {
        return cartes.get(0).valeur + 1 == cartes.get(1).valeur && 
               cartes.get(1).valeur + 1 == cartes.get(2).valeur 
               || cartes.get(0).valeur + 1 == cartes.get(1).valeur && 
               cartes.get(1).valeur + 1 == cartes.get(2).valeur 
               || cartes.get(0).valeur + 1 == cartes.get(1).valeur && 
               cartes.get(1).valeur + 1 == cartes.get(2).valeur;
    }
// a completer
    private boolean verifierBrelan(List<Carte> cartes) {
        return cartes.get(0).valeur == cartes.get(1).valeur && 
               cartes.get(1).valeur == cartes.get(2).valeur;
    }

    @Override
    public int compareTo(Combinaison autre) {
        
        if (this.type.getForce() != autre.type.getForce()) {
            return Integer.compare(this.type.getForce(), autre.type.getForce());
        }
        return Integer.compare(this.sommeValeurs, autre.sommeValeurs);
    }
}
