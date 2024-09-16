package strategie;

import Modele.Coup;
import Modele.Tas;

public class StrategieGagnante implements IStrategie {
    private int maxAllumettesParCoup;

    public StrategieGagnante(int maxAllumettesParCoup) {
        this.maxAllumettesParCoup = maxAllumettesParCoup;
    }
    public Coup appliquerStrategie(Tas tas, boolean estPremierJoueur) {
        int resultatXor = 0;
        for (int allumettes : tas.getAllumettes()) {
            resultatXor ^= allumettes;
        }

        if (resultatXor == 0) {
            // Jouer un coup quelconque (retirer une allumette du premier tas non vide)
            for (int i = 0; i < tas.getAllumettes().length; i++) {
                if (tas.getAllumettes()[i] > 0) {

                    return new Coup(i, 1);
                }
            }
        } else {
            for (int i = 0; i < tas.getAllumettes().length; i++) {
                int nb = tas.getAllumettes()[i] ^ resultatXor;
                if (nb < tas.getAllumettes()[i]) {
                    int allumettesARetirer = tas.getAllumettes()[i] - nb;
                    allumettesARetirer = Math.min(allumettesARetirer, maxAllumettesParCoup);  // Ne pas dépasser le maximum autorisé
                    return new Coup(i, allumettesARetirer);
                }
            }
        }
        return null; // en cas d'erreur
}
}
