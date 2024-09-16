package strategie;

import Modele.Coup;
import Modele.Tas;

public interface IStrategie {
    Coup appliquerStrategie(Tas tas, boolean estPremierJoueur);

}
