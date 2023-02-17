package edu.uark.csce3513.team18.lasertag;

public class Main {
    public static void main(String[] args) 
    {
        splashScreen splash = new splashScreen(); // declare splashScreen and call from main.java
        splash.showSplashScreen();

        Entry entry = new Entry();
        entry.showEntryScreen();
    }
}
