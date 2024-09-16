package strategie;

import Modele.Coup;
import Modele.Tas;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrategieAleatoire implements IStrategie {
    private Random random;
    private int maxAllumettesParCoup;

    public StrategieAleatoire(int maxAllumettesParCoup) {
        this.random = new Random();
        this.maxAllumettesParCoup = maxAllumettesParCoup;
    }

    @Override
    public Coup appliquerStrategie(Tas tas, boolean estPremierJoueur) {
        List<Coup> coupsPossibles = new ArrayList<>();
        int[] allumettes = tas.getAllumettes();

        // Calculer tous les coups possibles
        for (int i = 0; i < allumettes.length; i++) {
            int maxRetrait = Math.min(allumettes[i], maxAllumettesParCoup);
            for (int j = 1; j <= maxRetrait; j++) {
                coupsPossibles.add(new Coup(i, j));
            }
        }

        // Choisir un coup au hasard parmi les coups possibles
        if (!coupsPossibles.isEmpty()) {
            int indexCoupChoisi = random.nextInt(coupsPossibles.size());
            return coupsPossibles.get(indexCoupChoisi);
        }

        return null;  // En cas d'absence de coups possibles (ne devrait jamais arriver si la partie n'est pa
    }
}
