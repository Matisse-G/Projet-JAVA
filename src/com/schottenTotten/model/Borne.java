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

    public void revendiquer(List<Joueur> joueur){
        if(cartesJoueur1.size() == 3){
            combJ1.analyserCartes(cartesJoueur1);
        }
        if(cartesJoueur2.size() == 3){
            combJ2.analyserCartes(cartesJoueur2);
        }
        if(combJ1.compareTo(combJ2) > 0){
            setProprietaire(joueur.get(0));
        }
        else if(combJ2.compareTo(combJ1) > 0){
            setProprietaire(joueur.get(1));
        }
    }
}
