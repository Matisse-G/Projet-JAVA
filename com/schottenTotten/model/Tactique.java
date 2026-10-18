package model;

import java.util.ArrayList;
import java.util.List;

public class Tactique {
    public List<CarteTactique> cartes;

    public Tactique() {
        this.cartes = new ArrayList<>();

        // Troupes d'élite
        cartes.add(new CarteTactique(CarteTactique.Nom.JOKER, CarteTactique.Catégorie.TROUPE_ELITE));
        cartes.add(new CarteTactique(CarteTactique.Nom.JOKER, CarteTactique.Catégorie.TROUPE_ELITE));
        cartes.add(new CarteTactique(CarteTactique.Nom.ESPION, CarteTactique.Catégorie.TROUPE_ELITE));
        cartes.add(new CarteTactique(CarteTactique.Nom.PORTE_BOUCLIER, CarteTactique.Catégorie.TROUPE_ELITE));

        // Modes de combat
        cartes.add(new CarteTactique(CarteTactique.Nom.COLIN_MAILLARD, CarteTactique.Catégorie.MODE_COMBAT));
        cartes.add(new CarteTactique(CarteTactique.Nom.COMBAT_DE_BOUE, CarteTactique.Catégorie.MODE_COMBAT));

        // Ruses
        cartes.add(new CarteTactique(CarteTactique.Nom.CHASSEUR_DE_TETE, CarteTactique.Catégorie.RUSE));
        cartes.add(new CarteTactique(CarteTactique.Nom.STRATEGE, CarteTactique.Catégorie.RUSE));
        cartes.add(new CarteTactique(CarteTactique.Nom.BANSHEE, CarteTactique.Catégorie.RUSE));
        cartes.add(new CarteTactique(CarteTactique.Nom.TRAITRE, CarteTactique.Catégorie.RUSE));
    }
}