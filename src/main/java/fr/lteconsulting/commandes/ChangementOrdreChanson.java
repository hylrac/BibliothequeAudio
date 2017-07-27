package fr.lteconsulting.commandes;

import fr.lteconsulting.Commande;
import fr.lteconsulting.modele.Bibliotheque;
import fr.lteconsulting.outils.Saisie;

public class ChangementOrdreChanson implements Commande {

	private Bibliotheque bibliotheque;

	public ChangementOrdreChanson(Bibliotheque bibliotheque) {
		this.bibliotheque = bibliotheque;
	}

	@Override
	public String getNom() {
		return "Changer l'ordre d'une chanson par id";
	}

	@Override
	public void executer() {
		int id = Saisie.saisieInt("Id de la chanson à déplacer");
		int ordre = Saisie.saisieInt("Nouvel ordre de la chanson");
		bibliotheque.changementOrdreChanson(id, ordre);
	}

}
