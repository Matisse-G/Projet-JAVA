package model;

public class Carte {
    public int valeur;
    public Couleur couleur;
    public Carte(Couleur couleur, int valeur){
        this.couleur = couleur;
        this.valeur = valeur;
    }
    public enum Couleur{
        BLEU("\u001B[34m"),ROUGE("\u001B[31m"),VERT("\u001B[32m"),VIOLET("\u001B[35m"),JAUNE("\u001B[33m"),BRUN("\u001B[38;5;130m");
        private final String codeAnsi;
    
    
        public static final String RESET = "\u001B[0m"; 

        Couleur(String codeAnsi) {
            this.codeAnsi = codeAnsi;
        }

        public String getCodeAnsi() {
            return codeAnsi;
        }
    }
    @Override
    public String toString() {
       
        return couleur.getCodeAnsi() + valeur + Couleur.RESET;
        
    }
}

