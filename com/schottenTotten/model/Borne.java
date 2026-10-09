package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Borne {
    private int id;
    private List<Carte> cartesJoueur1;
    private List<Carte> cartesJoueur2
    private Joueur proprietaire;

    public Borne(int id){
        this.id = id;
        this.cartesJoueur1 = new ArrayList<>();
        this.cartesJoueur2= new ArrayList<>();
        this.proprietaire = null;
    }

    public ajouterCarte(Joueur joueur, Carte carte, boolean estJoueur1) {
        if(estJoueur1) {
            if(carteJoueur1.size() < 3 && joueur.equals(proprietaire)) {
                cartesJoueur1.add(carte);
                return true;
            }
        } else {
            if(carteJoueur2.size() < 3 && joueur.equals(proprietaire)) {
                cartesJoueur2.add(carte);
                return true;
            }
        }
        return false
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

}
