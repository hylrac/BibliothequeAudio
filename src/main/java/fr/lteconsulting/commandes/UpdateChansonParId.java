package fr.lteconsulting.commandes;

import fr.lteconsulting.Commande;
import fr.lteconsulting.modele.Bibliotheque;
import fr.lteconsulting.modele.Chanson;
import fr.lteconsulting.outils.Saisie;

public class UpdateChansonParId implements Commande{
	
	private Bibliotheque bibliotheque;
	
	public UpdateChansonParId(Bibliotheque bibliotheque) {
		this.bibliotheque = bibliotheque;
	}

	@Override
	public String getNom() {
		return "Mettre à jour une chanson";
	}

	@Override
	public void executer() {
		int id = Saisie.saisieInt( "Id de la chanson à mettre à jour" );
		String nom = Saisie.saisie( "Nouveau nom de la chanson" );
		int duree = Saisie.saisieInt( "Nouvelle durée de la chanson" );
		Chanson chanson = new Chanson(nom, duree, id);
			

		
		bibliotheque.updateChanson(chanson);
		
	}

}
