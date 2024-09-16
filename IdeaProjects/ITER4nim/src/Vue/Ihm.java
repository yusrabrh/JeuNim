package Vue;


import Modele.Coup;
import Modele.GameException;

import java.util.Scanner;

public class Ihm {
    private Scanner scanner;

    public Ihm() {
        this.scanner = new Scanner(System.in);
    }

    public boolean demanderModeJeu() {
        System.out.println("Voulez-vous jouer contre l'IA ? (oui/non)");
        String reponse = scanner.nextLine().trim().toLowerCase();
        return reponse.equals("oui");
    }
    public boolean demanderSiStrategieAleatoire() {
        System.out.println("Voulez-vous que l'IA utilise une strategie aleatoire ? (oui/non)");
        String reponse = scanner.nextLine().trim().toLowerCase();
        return reponse.equals("oui");
    }
    public int demanderNombreTas() throws GameException {
        System.out.println("Combien de tas voulez-vous utiliser pour jouer ?");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new GameException("Entrée invalide. Veuillez saisir un nombre entier.");
        }
    }

    public int demanderContrainteAllumettes() {
        System.out.println("Voulez-vous limiter le nombre d'allumettes que l'on peut retirer a chaque coup ? (oui/non)");
        if (scanner.nextLine().trim().toLowerCase().equals("oui")) {
            System.out.println("Entrez le nombre maximal d'allumettes par coup  :");
            int max = scanner.nextInt();
            scanner.nextLine(); // consommer la ligne restante
            return max;
        }
        return Integer.MAX_VALUE; // Aucune contrainte si l'utilisateur dit non
    }

    public String demanderNomJoueur(int numeroJoueur) {
        System.out.println("Entrez le nom du joueur " + numeroJoueur + ":");
        return scanner.nextLine();
    }

    public Coup demanderCoup(String nomJoueur) throws GameException {
        System.out.println(nomJoueur + ": A vous de jouer (format 'm n' ou m est la ligne et n le nombre d'allumettes):");
        try {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            int tas = Integer.parseInt(parts[0]) - 1;
            int nombre = Integer.parseInt(parts[1]);

            return new Coup(tas, nombre);
        } catch (Exception e) {
            throw new GameException("Erreur de format ou d'entree invalide. Veuillez reessayer.");
        }
    }

    public void afficherEtatTas(int[] allumettes) {
        for (int i = 0; i < allumettes.length; i++) {
            System.out.print("Tas " + (i + 1) + " -> ");
            for (int j = 0; j < allumettes[i]; j++) {
                System.out.print("| ");
            }
            System.out.println();
        }
    }
    public boolean demanderConfirmation() throws GameException {
        System.out.println("Voulez-vous rejouer ? (oui/non)");
        String reponse = scanner.nextLine().trim().toLowerCase();
        if (reponse.equals("oui")) {
            return true;
        } else if (reponse.equals("non")) {
            return false;
        } else {
            throw new GameException("Réponse invalide. Veuillez entrer 'oui' ou 'non'.");
        }
    }


    public void afficherMessage(String message) {
        System.out.println(message);
}
}
