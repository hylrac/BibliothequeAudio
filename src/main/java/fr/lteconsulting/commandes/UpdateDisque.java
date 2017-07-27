package fr.lteconsulting.commandes;

import fr.lteconsulting.Commande;
import fr.lteconsulting.modele.Bibliotheque;
import fr.lteconsulting.modele.Chanson;
import fr.lteconsulting.modele.Disque;
import fr.lteconsulting.outils.Saisie;

public class UpdateDisque implements Commande{
	
	private Bibliotheque bibliotheque;

	public UpdateDisque(Bibliotheque bibliotheque) {
		this.bibliotheque = bibliotheque;
	}

	@Override
	public String getNom() {
		return "Mettre à jour un disque";
	}

	@Override
	public void executer() {
		String codeBarre = Saisie.saisie( "Code barre du disque à mettre à jour" );
		String nom = Saisie.saisie( "Nouveau nom du disque" );
		Disque disque = new Disque( codeBarre, nom );
		while( true )
		{
			String titre = Saisie.saisie( "Nom de la chanson (laisser vide pour terminer)" );
			if( titre.isEmpty() )
				break;

			int duree = Saisie.saisieInt( "Durée de la chanson" );

			Chanson chanson = new Chanson( titre, duree );
			disque.addChanson( chanson );
		}
		

		
		bibliotheque.update(disque);
		
	}

}
