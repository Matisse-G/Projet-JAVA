package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Pioche{
    public List<Carte> pioche1;
    public List<Carte> pioche2;
    public String choix;
    public Pioche(String choix){
        this.choix = choix;
        this.pioche1 = new ArrayList<>();
        this.pioche2 = new ArrayList<>();
        if(choix.equals("Normal")){
            this.pioche1.addAll(new Normal().cartes);
            Collections.shuffle(this.pioche1);
        }
        else if(choix.equals("Tactique")){
            this.pioche1.addAll(new Normal().cartes);
            this.pioche2.addAll(new Tactique().cartes);
            Collections.shuffle(this.pioche1);
            Collections.shuffle(this.pioche2);
        }
        else{
            System.out.println("Veuillez choisir soit Tactique soit Normal");
        }
    }
}
