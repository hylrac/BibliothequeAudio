package fr.lteconsulting.modele;

import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.dao.ChansonDAO;
import fr.lteconsulting.dao.DisqueDAO;

public class Bibliotheque
{

	DisqueDAO dao = new DisqueDAO();
	ChansonDAO cdao = new ChansonDAO();

	public void ajouterDisque( Disque disque )
	{
		dao.add(disque);
		for (Chanson chanson : disque.getChansons()) {
			cdao.add(chanson, disque.getCodeBarre());
		}
		
	}

	public List<Disque> getDisques()
	{
		return dao.findAll(cdao);
	}
	
	public void update(Disque disque) {
		dao.update(disque);
		for (Chanson chanson : disque.getChansons()) {
			cdao.add(chanson, disque.getCodeBarre());
		}
		
	}
	
	public void updateChanson(Chanson chanson) {
		cdao.update(chanson);
		
	}
	
	public void changementOrdreChanson (int id, int ordre) {
		cdao.changementOrdreChanson(id, ordre);
	}
	
	public void delete( String disqueId ) {
		cdao.deleteByDiscId(disqueId);
		dao.delete(disqueId);
	}
	
	public void deleteChanson( int id ) {
		cdao.delete(id);
	}

	public Disque rechercherDisqueParCodeBarre( String codeBarre )
	{
		return dao.findById(codeBarre);
	}

	public List<Disque> rechercherDisqueParNom( String recherche )
	{
		recherche = recherche.toLowerCase();

		List<Disque> resultat = new ArrayList<>();

		for( Disque disque : dao.findAll(cdao) )
		{
			if( disque.getNom().toLowerCase().contains( recherche ) )
				resultat.add( disque );
		}

		return resultat;
	}

	public List<Disque> rechercherDisqueParNom( List<String> termes )
	{
		List<Disque> resultat = new ArrayList<>();

		for( Disque disque : dao.findAll(cdao) )
		{
			boolean estValide = true;
			for( String terme : termes )
			{
				if( !disque.getNom().toLowerCase().contains( terme.toLowerCase() ) )
				{
					estValide = false;
					break;
				}
			}

			if( estValide )
				resultat.add( disque );
		}

		return resultat;
	}

	public void afficher()
	{
		List<Disque> disques = this.getDisques();
		System.out.println( "BIBLIOTHEQUE avec " + disques.size() + " disques" );
		for( Disque disque : disques )
			disque.afficher();
	}
}
