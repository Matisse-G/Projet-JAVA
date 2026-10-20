package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Pioche{
    public List<Carte> piocheNormal;
    public List<CarteTactique> piocheTactique;
    public String typeJeu;
    public Pioche(String typeJeu){
        this.typeJeu = typeJeu;
        this.piocheNormal = new ArrayList<>();
        this.piocheTactique = new ArrayList<>();
        if(typeJeu.equals("Normal")){
            this.piocheNormal.addAll(new Normal().cartes);
            Collections.shuffle(this.piocheNormal);
        }
        else if(typeJeu.equals("Tactique")){
            this.piocheNormal.addAll(new Normal().cartes);
            this.piocheTactique.addAll(new Tactique().cartes);
            Collections.shuffle(this.piocheNormal);
            Collections.shuffle(this.piocheTactique);
        }
        else{
            System.out.println("Veuillez choisir soit Tactique soit Normal");
        }
    }
}
