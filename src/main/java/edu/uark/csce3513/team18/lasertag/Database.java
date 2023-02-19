package edu.uark.csce3513.team18.lasertag;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The Database class can be used to interface with the PostgreSQL database to
 * store and retrieve player information.
 * 
 * @author Ben Easterling
 *
 */
public class Database {
	private String url;
	private String username;
	private String password;
	private Connection connection;

	/**
	 * Creates a new Database object.
	 * 
	 * @param url      The URL of the PostgreSQL database
	 * @param username The username of the PostgreSQL database
	 * @param password The password of the PostgreSQL database
	 */
	public Database(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/**
	 * Establishes connection with the database.
	 * 
	 * @throws SQLException An error is thrown if the connection is unsuccessful.
	 */
	public void connect() throws SQLException {
		connection = DriverManager.getConnection(url, username, password);
	}

	/**
	 * Creates a player object from a ResultSet.
	 * 
	 * @param rs The ResultSet to get the player fields from
	 * @return A player object with the fields set from the ResultSet
	 * @throws SQLException An error is thrown if there is an error retrieving one
	 *                      of the fields from ResultSet
	 */
	private Player getPlayerFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String codeName = rs.getString("codename");
		Player player = new Player(id,codeName);
		return player;
	}

	/**
	 * Gets a list of all of the players in the database.
	 * 
	 * @return An ArrayList containing all of the players in the database
	 * @throws SQLException An error is thrown if the query is unsuccessful
	 */
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

	/**
	 * Creates a new player in the database.
	 * 
	 * @param player The player to insert into the database
	 * @throws SQLException An error is thrown if the update is unsuccessful
	 */
	public void createPlayer(Player player) throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("INSERT INTO player (id, codename) VALUES (?, ?)");
		stmt.setInt(1, player.getId());
		stmt.setString(2, player.getCodeName());
		int rowsChanged = stmt.executeUpdate();
		if (rowsChanged <= 0) {
			throw new SQLException("Failed to create player: " + player.toString());
		}
	}

	/**
	 * Gets the player with the specified ID.
	 * 
	 * @param id The ID of the player to retrieve from the database
	 * @return A player object containing all of the information for the requested
	 *         player.
	 * @throws SQLException An error is thrown if the query is unsuccessful
	 */
	public Player getPlayer(int id) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM player WHERE id = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return getPlayerFromResultSet(rs);
		}
		throw new SQLException("Failed to get player with id: " + id);
	}

	/**
	 * Updates the provided player in the database.
	 * 
	 * @param player The object of the player to update, containing the new field
	 *               values
	 * @throws SQLException An error is thrown if the update is unsuccessful
	 */
	public void updatePlayer(Player player) throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("UPDATE player SET codename = ? WHERE id = ?");
		stmt.setString(1, player.getCodeName());
		stmt.setInt(2, player.getId());
		int rowsChanged = stmt.executeUpdate();
		if (rowsChanged <= 0) {
			throw new SQLException("Failed to update player: " + player.toString());
		}
	}

	/**
	 * Deletes the player with the specified ID from the database.
	 * 
	 * @param id The ID of the player to delete
	 * @throws SQLException An error is thrown if the update is unsuccessful
	 */
	public void deletePlayer(int id) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("DELETE FROM player WHERE id = ?");
		stmt.setInt(1, id);
		int rowsChanged = stmt.executeUpdate();
		if (rowsChanged <= 0) {
			throw new SQLException("Failed to delete player: " + id);
		}
	}

	/**
	 * Deletes all players from the players table, but preserves the table schema.
	 * 
	 * @throws SQLException An error is thrown if the update is unsuccessful
	 */
	public void deleteAllPlayers() throws SQLException {
		Statement stmt = connection.createStatement();
		int rowsChanged = stmt.executeUpdate("DELETE FROM player");
		if (rowsChanged <= 0) {
			throw new SQLException("Failed to delete all players");
		}
	}

}
