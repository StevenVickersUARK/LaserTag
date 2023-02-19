package edu.uark.csce3513.team18.lasertag;

import java.sql.SQLException;
import java.util.List;
public class Main {
    public static void main(String[] args) 
    {
        String url = "jdbc:postgresql://db.xbbsojmzbwlxprdwscxj.supabase.co:5432/postgres";
		String username = "postgres";
		
		String password = "team18isthebest";
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
        splashScreen splash = new splashScreen(); // declare splashScreen and call from main.java
        splash.showSplashScreen();

        Entry entry = new Entry();
        entry.showEntryScreen();
    }
}
