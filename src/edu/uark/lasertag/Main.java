package edu.uark.lasertag;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static Scanner s;
	private static Database database;

	private static void printMenu() {
		System.out.println("Database Operations:");
		System.out.println("(C) - Create a player");
		System.out.println("(R) - Get a player");
		System.out.println("(U) - Update a player");
		System.out.println("(D) - Delete a player");
		System.out.println("(DA) - Delete all players");
		System.out.println("(RA) - Get all players");
		System.out.println("(H) - Print this list again");
		System.out.println("(Q) - Quit");
	}

	private static int getIdFromUser() {
		System.out.print("ID: ");
		int id = Integer.parseInt(s.nextLine());
		return id;
	}

	private static Player getPlayerFromUser() {
		int id = getIdFromUser();
		System.out.print("First name: ");
		String firstName = s.nextLine();
		System.out.print("Last name: ");
		String lastName = s.nextLine();
		System.out.print("Codename: ");
		String codeName = s.nextLine();
		Player player = new Player(id, firstName, lastName, codeName);
		return player;
	}

	private static void createPlayer() throws SQLException {
		Player player = getPlayerFromUser();
		database.createPlayer(player);
		System.out.println("Created player: " + player.toString());
	}

	private static void getPlayer() throws SQLException {
		int id = getIdFromUser();
		Player player = database.getPlayer(id);
		player.print();
	}

	private static void updatePlayer() throws SQLException {
		Player player = getPlayerFromUser();
		database.updatePlayer(player);
		System.out.println("Player " + player.getId() + " was updated.");
	}

	private static void deletePlayer() throws SQLException {
		int id = getIdFromUser();
		database.deletePlayer(id);
		System.out.println("Player " + id + " was deleted.");
	}

	private static void deleteAllPlayers() throws SQLException {
		database.deleteAllPlayers();
		System.out.println("Deleted all players.");
	}

	private static void getAllPlayers() throws SQLException {
		List<Player> players = database.getPlayers();
		System.out.println("Players:");
		for (Player player : players) {
			player.print();
		}
	}

	private static void executeCommand(String command) throws SQLException {
		switch (command.toUpperCase()) {
		case "C":
			createPlayer();
			break;
		case "R":
			getPlayer();
			break;
		case "U":
			updatePlayer();
			break;
		case "D":
			deletePlayer();
			break;
		case "DA":
			deleteAllPlayers();
			break;
		case "RA":
			getAllPlayers();
			break;
		case "H":
			printMenu();
			break;
		case "Q":
			break;
		default:
			System.out.println("Unrecognized command. Type H to print the list of commands again.");
			break;
		}
	}

	public static void main(String[] args) {
		String url = "jdbc:postgresql://db.guyknscogcoggtigtfqc.supabase.co:5432/postgres";
		String username = "postgres";

		s = new Scanner(System.in);
		System.out.print("Enter database password: ");
		String password = s.nextLine();

		database = new Database(url, username, password);
		try {
			database.connect();
		} catch (SQLException ex) {
			System.err.println("Unable to connect to the database.");
			ex.printStackTrace();
		}

		printMenu();
		String command = "";
		while (!command.toUpperCase().equals("Q")) {
			System.out.print("> ");
			command = s.nextLine();
			try {
				executeCommand(command);
			} catch (Exception ex) {
				System.err.println("The operation failed.");
				ex.printStackTrace();
			}
		}
	}
}
