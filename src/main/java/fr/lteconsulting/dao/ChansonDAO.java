package fr.lteconsulting.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.lteconsulting.modele.Chanson;

public class ChansonDAO {
	private Connection connection;

	public ChansonDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bibliotheque_audio?autoReconnect=true&useSSL=false", "root", "");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Chargement driver failure", e);
		} catch (SQLException e) {
			throw new RuntimeException("Impossible d'établir une connection avec le SGBD", e);
		}
	}

	public List<Chanson> findByDiscId(String id) {
		try {
			String sql = "SELECT * FROM chansons WHERE disque_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			List<Chanson> chansons = new ArrayList<>();
			while (resultSet.next()) {

				String nom = resultSet.getString("nom");
				int dureeEnSecondes = resultSet.getInt("duree");
				int chansonId = resultSet.getInt("id");
				Chanson chanson = new Chanson(nom, dureeEnSecondes, chansonId);
				chansons.add(chanson);

			}
			
			return chansons;
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
	}

	public Chanson add(Chanson chanson, String disqueId) {
		try {
			String sql = "INSERT INTO `chansons` (disque_id, nom, duree) VALUES ( ? , ? , ? )";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, disqueId);
			statement.setString(2, chanson.getNom());
			statement.setInt(3, chanson.getDureeEnSecondes());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
		return chanson;
	}
	
	public void update(Chanson chanson) {
		try {
			String sql = "UPDATE `chansons` SET nom= ?, duree = ?  WHERE id= ? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, chanson.getNom());
			statement.setInt(2, chanson.getDureeEnSecondes());
			statement.setInt(3, chanson.getId());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
	}
	
	public void delete(int id) {
		try {
			String sql = "DELETE FROM `chansons` WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			
				
			
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
		
	}
	
	public void deleteByDiscId(String disqueId) {
		try {
			String sql = "DELETE FROM `chansons` WHERE disque_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, disqueId);
			statement.executeUpdate();
			
				
			
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
		
	}
}
