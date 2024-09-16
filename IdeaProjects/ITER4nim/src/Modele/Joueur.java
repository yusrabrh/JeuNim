package Modele;

public class Joueur {
    private String nom;
    private int partiesGagnees;

    public Joueur(String nom) {
        this.nom = nom;
        this.partiesGagnees = 0;
    }

    public String getNom() {
        return nom;
    }

    public void incrementerPartiesGagnees() {
        partiesGagnees++;
    }

    public int getPartiesGagnees() {
        return partiesGagnees;
    }
}
