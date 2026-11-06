package model;

import java.util.ArrayList;
import java.util.List;

public class Borne {
    private int id;
    private List<Carte> cartesJoueur1;
    private List<Carte> cartesJoueur2;
    private Joueur proprietaire; 
    private Combinaison combJ1;
    private Combinaison combJ2;

    

    public Borne(int id){
        this.id = id;
        this.cartesJoueur1 = new ArrayList<>();
        this.cartesJoueur2 = new ArrayList<>();
        this.proprietaire = null;
/*         this.combJ1 = new Combinaison(cartesJoueur1);
        this.combJ2 = new Combinaison(cartesJoueur2); */
    }

    public boolean ajouterCarte(Joueur joueur, Carte carte, boolean estJoueur1) {
        if(estJoueur1) {
            if(cartesJoueur1.size() < 3 && proprietaire == null) {
                cartesJoueur1.add(carte);
                return true;
            }
        } else {
            if(cartesJoueur2.size() < 3 && proprietaire == null) {
                cartesJoueur2.add(carte);
                return true;
            }
        }
        return false;
    }
    
    public boolean estComplete() {
        return cartesJoueur1.size() == 3 && cartesJoueur2.size() == 3;
    }

    public int getId() {
        return id;
    }

    public List<Carte> getCartesJoueur1() {
        return cartesJoueur1;
    }

    public List<Carte> getCartesJoueur2() {
        return cartesJoueur2;
    }
    

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    } 

    public void revendiquer(Joueur j1, Joueur j2) {
        // On ne peut comparer que si les deux côtés ont 3 cartes
        if (cartesJoueur1.size() == 3 && cartesJoueur2.size() == 3) {
            
            // 1. On crée les objets Combinaison en leur donnant les listes
            Combinaison combJ1 = new Combinaison(cartesJoueur1);
            Combinaison combJ2 = new Combinaison(cartesJoueur2);

            // 2. On les compare
            int resultat = combJ1.compareTo(combJ2);

            // 3. On attribue la borne au vainqueur
            if (resultat > 0) {
                setProprietaire(j1);
                System.out.println("Le Joueur 1 remporte la borne " + id + " !");
            } 
            else if (resultat < 0) {
                setProprietaire(j2);
                System.out.println("Le Joueur 2 remporte la borne " + id + " !");
            } 
            else {
                // En cas d'égalité parfaite (même type et même somme)
                System.out.println("Égalité sur la borne " + id + " !");
                // Dans les vraies règles, c'est le joueur qui a posé sa dernière carte en PREMIER qui gagne.
                // Vous pourrez implémenter cette subtilité plus tard.
            }
        }
    }
}
