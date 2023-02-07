package edu.uark.lasertag;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://db.guyknscogcoggtigtfqc.supabase.co:5432/postgres";
		String username = "postgres";
		Scanner s = new Scanner(System.in);
		System.out.print("Enter database password: ");
		String password = s.nextLine();
		Database database = new Database(url, username, password);
		try {
			database.connect();
			List<Player> players = database.getPlayers();
			System.out.println("Players:");
			for (Player player : players) {
				player.print();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
