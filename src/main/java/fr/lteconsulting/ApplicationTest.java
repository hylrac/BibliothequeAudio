package fr.lteconsulting;

import fr.lteconsulting.modele.Bibliotheque;

public class ApplicationTest {
	public static void main(String[] args) {
		Bibliotheque biblio = new Bibliotheque();
		
		InferfaceUtilisateur ui = new InferfaceUtilisateur(biblio);
		
		ui.execute();

		
		
	}
/*
	private static void afficherTousLesDisques(DisqueDAO dao) {
		
	}

	private static void chercherEtAfficherDisque(DisqueDAO dao, String id) {
		Disque disque = dao.findById(id);
		if (disque != null) {
			System.out.println("Le disque " + id + " a été trouvé :");
			disque.afficher();
		} else {
			System.out.println("Le disque " + id + "n'existe pas");
		}
	}
	
	private static void ajouterDisque( DisqueDAO dao, Disque disque) {
		dao.add(disque);
	}
	
	private static void updateDisque(DisqueDAO dao, Disque disque) {
		dao.update(disque);
	}
	
	private static void supprimerDisque(DisqueDAO dao, String id) {
		dao.delete(id);
	}*/
}
