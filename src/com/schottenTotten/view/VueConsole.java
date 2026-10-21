package view;

import controller.Partie;
import model.*;


import java.util.Scanner;

public class VueConsole {
    private Partie partie;
    private Scanner scanner;

    public VueConsole() {
        this.scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer le nom du premier joueur :");
        String nom1 = scanner.next();
        System.out.println("Veuillez entrer le nom du deuxième joueur :");
        String nom2 = scanner.next();
        this.partie = new Partie(nom1, nom2, "Normal"); // Vous pouvez changer le type de jeu ici
        
    }
    public void demarrer() {
        System.out.println("=========================================");
        System.out.println("   Bienvenue dans Schotten-Totten (PROG1)  ");
        System.out.println("=========================================");

        boolean partieEnCours = true;

        while (partieEnCours) {
            Joueur joueurActuel = partie.getJoueurActuel();
            boolean estJoueur1 = partie.getJoueurs().indexOf(joueurActuel) == 0;

            afficherEtatJeu();

            System.out.println("\nC'est au tour de **" + joueurActuel.getNom() + "**.");
            System.out.println("Vos cartes en main :");
            for (int i = 0; i < joueurActuel.getMain().size(); i++) {
                System.out.println("[" + (i+1) + "] " + joueurActuel.getMain().get(i));
            }

            // Saisie du choix de la carte
            System.out.print("Choisissez l'index de la carte à jouer (-1 pour quitter) : ");
            int choixCarteIndex = scanner.nextInt();

            if (choixCarteIndex == -1) {
                partieEnCours = false;
                System.out.println("Partie interrompue.");
                break;
            }

            if (choixCarteIndex < 0 || choixCarteIndex-1 >= joueurActuel.getMain().size()) {
                System.out.println("Index invalide. Réessayez.");
                continue;
            }

            Carte carteChoisie = joueurActuel.getMain().get(choixCarteIndex -1);

            System.out.print("Choisissez le numéro de la borne (1 à 9) : ");
            int idBorne = scanner.nextInt();

            Borne borneCible = null;
            for (Borne b : partie.getBornes()) {
                if (b.getId() == idBorne) {
                    borneCible = b;
                    break;
                }
            }
            
            if (borneCible == null) {
                System.out.println("Borne inexistante. Réessayez.");
                continue;
            }

            boolean coupReussi = partie.jouerTour(carteChoisie, borneCible, estJoueur1);
            if (!coupReussi) {
                System.out.println("Coup invalide (borne pleine ou erreur). Réessayez.");
            } else {
                System.out.println("Carte jouée avec succès !\n");
            }
            
            if (partie.quiaGagner() == null){}
            else  {
                System.out.println("Fin de partie !  Le joueur " + joueurActuel.getNom() + " a gagner");
                partieEnCours = false;
            }
            partie.changerJoueur();
        }

        scanner.close();
    }

    
    private void afficherEtatJeu() {
        System.out.println("\n----------------- PLATEAU -----------------");
        for (Borne b : partie.getBornes()) {
            System.out.print("Borne " + b.getId() + " | J1:" + b.getCartesJoueur1());
            System.out.println(" vs J2:" + b.getCartesJoueur2()); 
        }
        System.out.println("-------------------------------------------");
    }
}
