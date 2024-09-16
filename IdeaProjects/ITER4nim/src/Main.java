
import Vue.Ihm;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();

        boolean contreIA = ihm.demanderModeJeu();
        int maxAllumettesParCoup = ihm.demanderContrainteAllumettes();
        boolean utiliserStrategieAleatoire = ihm.demanderSiStrategieAleatoire();

        Controleur.ControleurJeu controleurJeu = new Controleur.ControleurJeu(ihm, contreIA, maxAllumettesParCoup, utiliserStrategieAleatoire);
        controleurJeu.jouer();
    }
}
