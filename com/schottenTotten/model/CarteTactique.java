package model;

public class CarteTactique {
    public Nom nom;
    public Catégorie type;
    public CarteTactique(Nom nom, Catégorie type) {
        this.nom = nom;
        this.type = type;
    }

    public enum Catégorie { 
        TROUPE_ELITE, MODE_COMBAT, RUSE 
    }

    public enum Nom {
        JOKER, ESPION, PORTE_BOUCLIER,
        COLIN_MAILLARD, COMBAT_DE_BOUE,
        CHASSEUR_DE_TETE, STRATEGE, BANSHEE, TRAITRE
    }

}