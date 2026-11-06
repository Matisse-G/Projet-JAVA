package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class Partie {
    private List<Joueur> joueurs;
    private List<Borne> bornes;
    private Pioche pioche;
    private int joueurActuelIndex;

    public Partie(String nomJoueur1, String nomJoueur2, String typeJeu) {
        this.joueurs = new ArrayList<>();
        this.joueurs.add(new Joueur(nomJoueur1));
        this.joueurs.add(new Joueur(nomJoueur2));

        this.bornes = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            this.bornes.add(new Borne(i));
        }
        this.pioche = new Pioche(typeJeu);
        distributionInitiale();

        this.joueurActuelIndex = 0;
        
    }

    private void distributionInitiale(){
        for(Joueur joueur : joueurs) {
            for(int i=0;i<6;i++){
                if (!pioche.piocheNormal.isEmpty()) {
                    joueur.piocherCarte(pioche.piocheNormal.remove(0));
                }    
            }
        }
    }

    public boolean jouerTour(Carte carte, Borne borne, boolean estJoueur1){
        Joueur joueurActuel = joueurs.get(joueurActuelIndex);

        if (!joueurActuel.getMain().contains(carte)) {
            return false;
        }

        boolean succes = borne.ajouterCarte(joueurActuel, carte, estJoueur1);
        if(succes) {
            joueurActuel.jouerunecarte(carte);
        
            if (!pioche.piocheNormal.isEmpty()) {
                    joueurActuel.piocherCarte(pioche.piocheNormal.remove(0));
            }
             borne.revendiquer(joueurs.get(0), joueurs.get(1));
            return true;
        }
        return false;
    }
    

    public void changerJoueur() {
        joueurActuelIndex = (joueurActuelIndex + 1) % 2;
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

    public Pioche getPioche() {
        return pioche;
    }

    public Joueur quiaGagner(){
        int nb_propJ = 0;
        int nb_3daffiler = 0;
        for(Borne borne : bornes){
            if (borne.getProprietaire() == null){
                nb_3daffiler = 0;
            }
            else if(borne.getProprietaire().equals(getJoueurActuel())){
                nb_propJ++;
                nb_3daffiler++;
                if(nb_3daffiler == 3 || nb_propJ == 5){
                    return getJoueurActuel();
                }

            }
            else if(nb_3daffiler == 3){
                return getJoueurActuel();
            }
            else if(nb_propJ == 5){
                return getJoueurActuel();
            }
            else{
                nb_3daffiler = 0;
            }
            
            

        }
        return null;
    }

    public List<Carte> getCartesVisiblesPour(Joueur joueurActuel) {
    List<Carte> visibles = new ArrayList<>();
    
    for (Borne b : bornes) {
        visibles.addAll(b.getCartesJoueur1());
        visibles.addAll(b.getCartesJoueur2());
    }
    
    return visibles;
    }

    public List<Carte> getCartesDisponiblesEnPioche(Joueur joueurActuel) {
    List<Carte> disponibles = new ArrayList<>();
    List<Carte> visibles = getCartesVisiblesPour(joueurActuel);

    for (Carte.Couleur c : Carte.Couleur.values()) {
        for (int v = 1; v <= 9; v++) {
            Carte carteTest = new Carte(c, v);

            boolean estVisible = false;
            for (Carte vis : visibles) {
                if (vis.couleur == c && vis.valeur == v) {
                    estVisible = true;
                    break;
                }
            }
            if (!estVisible) {
                disponibles.add(carteTest);
            }
        }
    }
    return disponibles;
    }

    public boolean peutAnticiperRevendication(Borne borne, Joueur joueurActuel, Joueur adversaire) {
    List<Carte> cartesActuel = (joueurActuel == joueurs.get(0)) ? borne.getCartesJoueur1() : borne.getCartesJoueur2();
    List<Carte> cartesAdv = (adversaire == joueurs.get(0)) ? borne.getCartesJoueur1() : borne.getCartesJoueur2();

    if (cartesActuel.size() != 3 || borne.getProprietaire() != null) {
        return false;
    }

    Combinaison combiABattre = new Combinaison(cartesActuel);
    List<Carte> cartesDispo = getCartesDisponiblesEnPioche(joueurActuel);
    int nombreCartesManquantesAdv = 3 - cartesAdv.size();

    if (nombreCartesManquantesAdv == 1) {
        for (Carte c1 : cartesDispo) {
            List<Carte> simuAdv = new ArrayList<>(cartesAdv);
            simuAdv.add(c1);
            Combinaison combiSimulee = new Combinaison(simuAdv);
            
            if (combiSimulee.compareTo(combiABattre) >= 0) {
                return false; 
            }
        }
    } else if (nombreCartesManquantesAdv == 2) {
        for (int i = 0; i < cartesDispo.size(); i++) {
            for (int j = i + 1; j < cartesDispo.size(); j++) {
                List<Carte> simuAdv = new ArrayList<>(cartesAdv);
                simuAdv.add(cartesDispo.get(i));
                simuAdv.add(cartesDispo.get(j));
                Combinaison combiSimulee = new Combinaison(simuAdv);
                
                if (combiSimulee.compareTo(combiABattre) >= 0) {
                    return false;
                }
            }
        }
    } else if (nombreCartesManquantesAdv == 3) {
        for (int i = 0; i < cartesDispo.size(); i++) {
            for (int j = i + 1; j < cartesDispo.size(); j++) {
                for (int k = j + 1; k < cartesDispo.size(); k++) {
                    List<Carte> simuAdv = new ArrayList<>();
                    simuAdv.add(cartesDispo.get(i));
                    simuAdv.add(cartesDispo.get(j));
                    simuAdv.add(cartesDispo.get(k));
                    Combinaison combiSimulee = new Combinaison(simuAdv);
                    
                    if (combiSimulee.compareTo(combiABattre) >= 0) {
                        return false;
                    }
                }
            }
        }
    }

    return true;
    }

}