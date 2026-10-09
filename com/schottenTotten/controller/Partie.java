package Projet-JAVA.com.schottenTotten.controller;

import com.schottenTotten.model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partie {
    private List<Joueur> joueurs;
    private List<Borne> bornes;
    private List<Carte> pioche;
    private int joueurActuelIndex;

    public Partie(String nomJoueur1, String nomJoueur2) {
        this.joueurs = new ArrayList<>();
        this.joueurs.add(new Joueur(nomJoueur1));
        this.joueurs.add(new Joueur(nomJoueur2));

        this.bornes = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            this.bornes.add(new Borne(i));
        }
        this.pioche = new ArrayList<>();
        initialiserPioche();

        this.joueurActuelIndex = 0;
        
    }

    private void distributionInitiale(){
        for(Joueur joueur : joueurs) {
            for(int i=0;i<6;i++){
                joueur.piocherCarte(Carte.remove(0));
            }
        }
    }

    public boolean jouerTour(Carte carte, Borne borne, boolean estJoueur1){
        Joueur joueurActuel = joueurs.get(joueurActuelIndex);

        boolean succes = borne.ajouterCarte(joueurActuelIndex, carte, estJoueur1);
        if(succes) {
            joueurActuel.jouerunecarte(carte);
        
            if (!pioche.isEmpty()) {
                    joueurActuel.piocherCarte(pioche.remove(0));
            }
            changerJoueur();
            return true;
        }
        return false;
    }
    

    private void changerJoueur() {
        joueurActuelIndex = (joueurActuelIndex + 1) % joueurs.size();
    }

    public Joueur getJoueurActuel() {
        return joueurs.get(joueurActuelIndex);
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public List<Borne> getBornes() {
        return bornes;
    }

    public List<Carte> getPioche() {
        return pioche;
    }

}