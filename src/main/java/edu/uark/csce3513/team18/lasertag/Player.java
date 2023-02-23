package edu.uark.csce3513.team18.lasertag;

/**
 * The Player class stores information about a particular player in the game.
 * Player objects are designed to store information about the player and are
 * therefore readonly. In order to change player information, the Database class
 * should be used to create, update, and delete players.
 * 
 * @author Ben Easterling
 * @see Database
 *
 */
public class Player {
	private int id;
	private String codeName;

	/**
	 * Create a new player.
	 * 
	 * @param id        The ID of the player
	 * @param codeName  The codename of the player
	 */
	public Player(int id, String codeName) {
		this.id = id;
		this.codeName = codeName;
	}

	/**
	 * Gets the ID associated with this player.
	 * 
	 * @return The ID associated with this player
	 */
	public int getId() {
		return id;
	}


	/**
	 * Gets the codename associated with this player.
	 * 
	 * @return The code name associated with this player
	 */
	public String getCodeName() {
		return codeName;
	}

	public String toString() {
		return String.format("Player %d:, codename %s", id, codeName);
	}

	/**
	 * Prints this Player's toString representation to standard out.
	 */
	public void print() {
		System.out.println(toString());
	}
}
