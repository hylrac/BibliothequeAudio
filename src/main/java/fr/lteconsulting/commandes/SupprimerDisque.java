package fr.lteconsulting.commandes;

import fr.lteconsulting.Commande;
import fr.lteconsulting.modele.Bibliotheque;
import fr.lteconsulting.outils.Saisie;

public class SupprimerDisque implements Commande {
	
	private Bibliotheque bibliotheque;

	public SupprimerDisque(Bibliotheque bibliotheque) {
		this.bibliotheque = bibliotheque;
	}

	@Override
	public String getNom() {
		return "Supprimer un disque par code barre";
	}

	@Override
	public void executer() {
		String id = Saisie.saisie("Code barre du disque à supprimer");
		bibliotheque.delete(id);
		
	}

}
