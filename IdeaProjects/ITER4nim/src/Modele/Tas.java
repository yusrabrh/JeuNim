package Modele;

public class Tas {
    private int[] allumettes;

    public Tas(int nombreLignes) {
        allumettes = new int[nombreLignes];
        for (int i = 0; i < nombreLignes; i++) {
            allumettes[i] = 2 * (i + 1) - 1;
        }
    }

    public boolean retirerAllumettes(int ligne, int nombre) {
        if (ligne < allumettes.length && allumettes[ligne] >= nombre) {
            allumettes[ligne] -= nombre;
            return true;
        }
        return false;
    }

    public int[] getAllumettes() {
        return allumettes;
    }

    public boolean estVide() {
        for (int nombre : allumettes) {
            if (nombre > 0) return false;
        }
        return true;
    }
}
