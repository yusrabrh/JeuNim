package Modele;

public class Coup {
    private int numeroTas;
    private int nombreAllumettes;

    public Coup(int numeroTas, int nombreAllumettes) {
        this.numeroTas = numeroTas;
        this.nombreAllumettes = nombreAllumettes;
    }

    public int getNumeroTas() {
        return numeroTas;
    }

    public int getNombreAllumettes() {
        return nombreAllumettes;
    }
}
