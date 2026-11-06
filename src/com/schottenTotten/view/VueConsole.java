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
        String nom1 = scanner.nextLine(); // Utilisation de nextLine() pour éviter les bugs de buffer
        System.out.println("Veuillez entrer le nom du deuxième joueur :");
        String nom2 = scanner.nextLine();
        this.partie = new Partie(nom1, nom2, "Normal"); 
    }

    public void demarrer() {
        System.out.println("=========================================");
        System.out.println("   Bienvenue dans Schotten-Totten (PROG1)  ");
        System.out.println("=========================================");

        boolean partieEnCours = true;

        while (partieEnCours) {
            Joueur joueurActuel = partie.getJoueurActuel();
            Joueur adversaire = (joueurActuel == partie.getJoueurs().get(0)) ? partie.getJoueurs().get(1) : partie.getJoueurs().get(0);
            boolean estJoueur1 = partie.getJoueurs().indexOf(joueurActuel) == 0;

            // --- VÉRIFICATION PAR ANTICIPATION / REVENDICATION ---
            for (Borne b : partie.getBornes()) {
                if (b.getProprietaire() == null) {
                    // Test 1: Si la borne a 3 cartes des deux côtés (classique)
                    if (b.getCartesJoueur1().size() == 3 && b.getCartesJoueur2().size() == 3) {
                        b.revendiquer(partie.getJoueurs().get(0), partie.getJoueurs().get(1));
                    }
                    // Test 2: Anticipation (revendication prématurée)
                    else if (partie.peutAnticiperRevendication(b, joueurActuel, adversaire)) {
                        b.setProprietaire(joueurActuel);
                        System.out.println("\n🔥 REVENDICATION PAR ANTICIPATION ! " + joueurActuel.getNom() + " remporte la borne " + b.getId() + " !");
                    }
                }
            }

            afficherEtatJeu();

            // Vérification immediate si l'anticipation a déclenché une victoire
            Joueur gagnant = partie.quiaGagner();
            if (gagnant != null) {
                System.out.println("\nFin de partie ! Le joueur " + gagnant.getNom() + " a gagné !");
                partieEnCours = false;
                break;
            }

            System.out.println("\nC'est au tour de **" + joueurActuel.getNom() + "**.");
            System.out.println("Vos cartes en main :");
            for (int i = 0; i < joueurActuel.getMain().size(); i++) {
                System.out.println("[" + (i+1) + "] " + joueurActuel.getMain().get(i));
            }

            // Saisie du choix de la carte
            System.out.print("Choisissez l'index de la carte à jouer (-1 pour quitter) : ");
            if (!scanner.hasNextInt()) {
                System.out.println("Saisie invalide (chiffre requis). Réessayez.");
                scanner.next();
                continue;
            }
            int choixCarteIndex = scanner.nextInt();

            if (choixCarteIndex == -1) {
                partieEnCours = false;
                System.out.println("Partie interrompue.");
                break;
            }

            if (choixCarteIndex < 1 || choixCarteIndex > joueurActuel.getMain().size()) {
                System.out.println("Index invalide. Réessayez.");
                continue;
            }

            Carte carteChoisie = joueurActuel.getMain().get(choixCarteIndex - 1);

            System.out.print("Choisissez le numéro de la borne (1 à 9) : ");
            if (!scanner.hasNextInt()) {
                System.out.println("Saisie invalide. Réessayez.");
                scanner.next();
                continue;
            }
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
                System.out.println("Coup invalide (borne pleine, déjà revendiquée ou erreur). Réessayez.");
                continue; // Ne pas changer de joueur si le coup a échoué !
            } else {
                System.out.println("Carte jouée avec succès !\n");
            }
            
            // Vérification de victoire après avoir joué la carte
            gagnant = partie.quiaGagner();
            if (gagnant != null) {
                afficherEtatJeu();
                System.out.println("\nFin de partie ! Le joueur " + gagnant.getNom() + " a gagné !");
                partieEnCours = false;
                break;
            }

            partie.changerJoueur();
        }

        scanner.close();
    }

    private void afficherEtatJeu() {
        System.out.println("\n----------------- PLATEAU -----------------");
        
        String COULEUR_J1 = "\u001B[36m"; // Cyan
        String COULEUR_J2 = "\u001B[35m"; // Violet
        String RESET = "\u001B[0m";

        for (Borne b : partie.getBornes()) {
            System.out.print("Borne " + b.getId() + " | J1: " + b.getCartesJoueur1());
            System.out.print(" vs J2: " + b.getCartesJoueur2()); 
            
            if (b.getProprietaire() != null) {
                if (b.getProprietaire().equals(partie.getJoueurs().get(0))) {
                    System.out.print("  ==> " + COULEUR_J1 + "[Gagnée par " + b.getProprietaire().getNom() + "]" + RESET);
                } else {
                    System.out.print("  ==> " + COULEUR_J2 + "[Gagnée par " + b.getProprietaire().getNom() + "]" + RESET);
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------");
    }
}