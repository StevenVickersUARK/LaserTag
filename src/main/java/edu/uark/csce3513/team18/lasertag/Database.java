package edu.uark.csce3513.team18.lasertag;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
	private String url;
	private String username;
	private String password;
	private Connection connection;

	public Database(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public void connect() throws SQLException {
		connection = DriverManager.getConnection(url, username, password);
	}

	private Player getPlayerFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String codeName = rs.getString("codename");
		Player player = new Player(id, firstName, lastName, codeName);
		return player;
	}

	public List<Player> getPlayers() throws SQLException {
		List<Player> playersList = new ArrayList<Player>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM player");
		while (rs.next()) {
			Player player = getPlayerFromResultSet(rs);
			playersList.add(player);
		}
		return playersList;
	}

	public void createPlayer(Player player) throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("INSERT INTO player (id, first_name, last_name, codename) VALUES (?, ?, ?, ?)");
		stmt.setInt(1, player.getId());
		stmt.setString(2, player.getFirstName());
		stmt.setString(3, player.getLastName());
		stmt.setString(4, player.getCodeName());
		int rowsChanged = stmt.executeUpdate();
		if (rowsChanged <= 0) {
			throw new SQLException("Failed to create player: " + player.toString());
		}
	}

	public Player getPlayer(int id) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM player WHERE id = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return getPlayerFromResultSet(rs);
		}
		throw new SQLException("Failed to get player with id: " + id);
	}

	public void updatePlayer(Player player) throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("UPDATE player SET first_name = ?, last_name = ?, codename = ? WHERE id = ?");
		stmt.setString(1, player.getFirstName());
		stmt.setString(2, player.getLastName());
		stmt.setString(3, player.getCodeName());
		stmt.setInt(4, player.getId());
		int rowsChanged = stmt.executeUpdate();
		if (rowsChanged <= 0) {
			throw new SQLException("Failed to update player: " + player.toString());
		}
	}

	public void deletePlayer(int id) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("DELETE FROM player WHERE id = ?");
		stmt.setInt(1, id);
		int rowsChanged = stmt.executeUpdate();
		if (rowsChanged <= 0) {
			throw new SQLException("Failed to delete player: " + id);
		}
	}

	public void deleteAllPlayers() throws SQLException {
		Statement stmt = connection.createStatement();
		int rowsChanged = stmt.executeUpdate("DELETE FROM player");
		if (rowsChanged <= 0) {
			throw new SQLException("Failed to delete all players");
		}
	}

}
