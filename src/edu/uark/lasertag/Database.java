package edu.uark.lasertag;

import java.sql.Connection;
import java.sql.DriverManager;
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

	private ResultSet executeQuery(String query) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	public List<Player> getPlayers() throws SQLException {
		List<Player> playersList = new ArrayList<Player>();
		ResultSet rs = executeQuery("SELECT * FROM player");
		while (rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String codeName = rs.getString("codename");
			Player player = new Player(id, firstName, lastName, codeName);
			playersList.add(player);
		}
		return playersList;
	}

}
