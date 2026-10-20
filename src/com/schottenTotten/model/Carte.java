package model;

public class Carte {
    public int valeur;
    public Couleur couleur;
    public Carte(Couleur couleur, int valeur){
        this.couleur = couleur;
        this.valeur = valeur;
    }
    public enum Couleur{
        BLEU, ROUGE, VERT, VIOLET, JAUNE, BRUN
    }
}
