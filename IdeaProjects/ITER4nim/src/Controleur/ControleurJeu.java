package Controleur;


import Modele.Coup;
import Modele.GameException;
import Modele.Joueur;
import Modele.Tas;
import Vue.Ihm;
import strategie.IStrategie;
import strategie.StrategieAleatoire;
import strategie.StrategieGagnante;

public class ControleurJeu {
    private Ihm ihm;
    private Joueur joueur1;
    private Joueur joueur2;
    private Tas tas;
    private IStrategie strategie;
    private boolean contreIA;
    private int maxAllumettesParCoup;

    public ControleurJeu(Ihm ihm, boolean contreIA, int maxAllumettesParCoup, boolean utiliserStrategieAleatoire) {
        this.ihm = ihm;
        this.contreIA = contreIA;
        this.maxAllumettesParCoup = maxAllumettesParCoup;
        this.strategie = utiliserStrategieAleatoire ? new StrategieAleatoire(maxAllumettesParCoup) : new StrategieGagnante(maxAllumettesParCoup);
    }

    public void jouer() {
        boolean continuer = true;

        while (continuer) {
            try {
                joueur1 = new Joueur(ihm.demanderNomJoueur(1));
                if (contreIA) {
                    joueur2 = new Joueur("IA");
                } else {
                    joueur2 = new Joueur(ihm.demanderNomJoueur(2));
                }

                boolean saisieValide = false;
                int nombreTas = 0;
                while (!saisieValide) {
                    try {
                        nombreTas = ihm.demanderNombreTas();
                        saisieValide = true; // La saisie est valide, on peut sortir de la boucle
                    } catch (GameException e) {
                        ihm.afficherMessage(e.getMessage()); // Affiche le message d'erreur mais reste dans la boucle
                    }
                }

                tas = new Tas(nombreTas);
                Joueur joueurActuel = joueur1;

                while (!tas.estVide()) {
                    ihm.afficherEtatTas(tas.getAllumettes());
                    if ( joueurActuel == joueur2 &&contreIA) {
                        // Exécution automatique du tour de l'IA sans interaction utilisateur
                        Coup coup = strategie.appliquerStrategie(tas, false);
                        tas.retirerAllumettes(coup.getNumeroTas(), coup.getNombreAllumettes());
                        ihm.afficherMessage("IA joue : retirer " + coup.getNombreAllumettes() + " allumette(s) du tas " + (coup.getNumeroTas() + 1));
                    } else {
                        boolean coupValide = false;

                        while (!coupValide) {
                            try {
                                Coup coup = ihm.demanderCoup(joueurActuel.getNom());
                                if (coup.getNombreAllumettes() > maxAllumettesParCoup) {
                                    throw new GameException("Vous ne pouvez pas retirer plus de " + maxAllumettesParCoup + " allumettes.");
                                }
                                coupValide = tas.retirerAllumettes(coup.getNumeroTas(), coup.getNombreAllumettes());
                                if (!coupValide) {
                                    ihm.afficherMessage("Coup invalide, veuillez réessayer.");
                                }
                            } catch (GameException e) {
                                ihm.afficherMessage("Erreur de coup: " + e.getMessage());
                            }
                        }
                    }

                    // Vérifiez si le jeu est terminé avant de passer au prochain joueur
                    if (tas.estVide()) {
                        ihm.afficherMessage("Le gagnant est " + joueurActuel.getNom());
                        joueurActuel.incrementerPartiesGagnees();
                        break; // Sortez de la boucle de jeu principale si le jeu est terminé
                    }

                    joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
                }

                continuer = ihm.demanderConfirmation();
                if (continuer) {
                    // Redemander la contrainte pour la nouvelle partie
                    maxAllumettesParCoup = ihm.demanderContrainteAllumettes();

                }
            } catch (GameException e) {
                ihm.afficherMessage("Erreur inattendue: " + e.getMessage());
            }
        }
        afficherResultatsFin();
    }

    private void afficherResultatsFin() {
        ihm.afficherMessage("Résultats finaux :");
        ihm.afficherMessage(joueur1.getNom() + " a gagné " + joueur1.getPartiesGagnees() + " parties.");
        ihm.afficherMessage(joueur2.getNom() + " a gagné " + joueur2.getPartiesGagnees() + " parties.");
        if (joueur1.getPartiesGagnees() == joueur2.getPartiesGagnees()) {
            ihm.afficherMessage("Le résultat est un ex aequo !");
        } else {
            Joueur vainqueur = joueur1.getPartiesGagnees() > joueur2.getPartiesGagnees() ? joueur1 : joueur2;
            ihm.afficherMessage("Le vainqueur est " + vainqueur.getNom());
 }
    }
}
