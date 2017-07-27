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
			String sql = "SELECT * FROM chansons WHERE disque_id = ? ORDER BY ordre";
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
			String sql = "INSERT INTO `chansons` (disque_id, nom, duree, ordre) VALUES ( ? , ? , ? , 50)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, disqueId);
			statement.setString(2, chanson.getNom());
			statement.setInt(3, chanson.getDureeEnSecondes());
			statement.executeUpdate();
			updateOrdreChansonsByDiscId(disqueId);

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

	public void updateOrdreChansonsByDiscId(String disqueId) {
		try {
			String sql = "SELECT * FROM `chansons` WHERE disque_id = ? ORDER BY ordre";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, disqueId);
			ResultSet resultSet = statement.executeQuery();
			List<Integer> listId = new ArrayList<>();
			while (resultSet.next()) {
				listId.add(resultSet.getInt("id"));
			}
			int i = 1;
			for (Integer id : listId) {
				String sql2 = "UPDATE `chansons` SET ordre = ?  WHERE id= ? ";
				PreparedStatement statement2 = connection.prepareStatement(sql2);
				statement2.setInt(1, i);
				statement2.setInt(2, id);
				statement2.executeUpdate();
				i++;
			}

		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
	}

	public void changementOrdreChanson(int id, int ordre) {
		try {
			String sql3 = "SELECT * FROM chansons WHERE id = ? ";
			PreparedStatement statement3 = connection.prepareStatement(sql3);
			statement3.setInt(1, id);
			ResultSet resultSet = statement3.executeQuery();
			String disqueId="";
			if (resultSet.next()) {
				disqueId = resultSet.getString("disque_id");
			} 
			
			updateOrdreChansonsByDiscId(disqueId);
			
			String sql4 = "SELECT * FROM chansons WHERE id = ? ";
			PreparedStatement statement4 = connection.prepareStatement(sql4);
			statement4.setInt(1, id);
			ResultSet resultSet2 = statement4.executeQuery();
			int ancienOrdre = 150000;
			if (resultSet2.next()) {
				ancienOrdre = resultSet.getInt("ordre");
			}
			
			String sql2 = "UPDATE `chansons` SET ordre = ?  WHERE id= ? AND disque_id = ? ";
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			if (ordre<ancienOrdre) {
				statement2.setFloat(1, (float) (ordre - 0.5));
			} else {
				statement2.setFloat(1, (float) (ordre + 0.5));
			}
			statement2.setInt(2, id);
			statement2.setString(3, disqueId);
			statement2.executeUpdate();
			updateOrdreChansonsByDiscId(disqueId);
			
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de réaliser l(es) opération(s)", e);
		}
	}
}
