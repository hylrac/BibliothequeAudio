package fr.lteconsulting.commandes;

import fr.lteconsulting.Commande;
import fr.lteconsulting.modele.Bibliotheque;
import fr.lteconsulting.outils.Saisie;

public class SupprimerChansonParId implements Commande{
	
	private Bibliotheque bibliotheque;

	public SupprimerChansonParId(Bibliotheque bibliotheque) {
		this.bibliotheque = bibliotheque;
	}

	@Override
	public String getNom() {
		return "Supprimer une chanson par id";
	}

	@Override
	public void executer() {
		int id = Saisie.saisieInt("Id de la chanson à supprimer");
		
		bibliotheque.deleteChanson(id);
	}

}
