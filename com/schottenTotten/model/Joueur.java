package Projet-JAVA.com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String nom;
    private List<Carte> main;

    public Joueur(String nom) {
        this.nom = nom;
        this.main = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public List<Carte> getMain() {
        return main;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMain(List<Carte> main) {
        this.main = main;
    }

    public void jouerunecarte(Carte carte){
        if(main.contains(carte)) {
            main.remove(carte);
        } else {
            System.out.println("La carte n'est pas dans la main du joueur.");
        }
    }

    public void piocherCarte(Carte carte) {
        this.main.add(carte);
    }

}