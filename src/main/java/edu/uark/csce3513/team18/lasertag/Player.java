package edu.uark.csce3513.team18.lasertag;

import java.io.PrintStream;

public class Player {
	private int id;
	private String firstName;
	private String lastName;
	private String codeName;

	public Player(int id, String firstName, String lastName, String codeName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.codeName = codeName;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCodeName() {
		return codeName;
	}

	public String toString() {
		return String.format("Player %d: %s %s, codename %s", id, firstName, lastName, codeName);
	}

	public void print(PrintStream stream) {
		stream.println(toString());
	}

	public void print() {
		print(System.out);
	}
}
