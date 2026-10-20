package model;
import java.util.ArrayList;
import java.util.List;

public class Normal{
    public List<Carte> cartes;
    public Normal(){
        this.cartes = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            for(Carte.Couleur j : Carte.Couleur.values()){
                Carte carte = new Carte(j, i);
                cartes.add(carte);
            }
        }
    }
    
    public String toString() {
        return "Cartes du jeu : " + cartes;
    }
    public static void main(String[] args){
        Normal jeu = new Normal();
        for(Carte carte : jeu.cartes ){
            System.out.println("La valeur de la carte est " + carte.valeur + " sa couleur est " + carte.couleur);
        }
        System.out.println(jeu.cartes);
    }
}