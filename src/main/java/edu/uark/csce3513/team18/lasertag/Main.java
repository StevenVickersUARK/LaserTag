package edu.uark.csce3513.team18.lasertag;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        new TeamIntroScreen();
        new SplashScreen().showSplashScreen();

        try {
            Database database = Database.getDatabase();
            database.connect();
        } catch (SQLException ex) {
            System.err.println("Failed to connect to database!");
            ex.printStackTrace();
        }

        Entry entry = new Entry();
        entry.showEntryScreen();
    }
}
