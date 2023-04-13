package edu.uark.csce3513.team18.lasertag;

import java.util.HashMap;
import java.util.Map;

public class GameState {

    private static GameState instance;
    private static int POINTS_FOR_HIT = 10;

    private int blueTeamScore = 0;
    private int redTeamScore = 0;
    // Maps the player ID to the player object (key = player ID, value = object)
    private Map<Integer, Player> players;
    // Maps the player ID to the player score (key = player ID, value = player
    // score)
    private Map<Integer, Integer> playerScores;

    public GameState() {
        players = new HashMap<>();
        playerScores = new HashMap<>();
    }

    public static GameState getGameState() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void reset() {
        blueTeamScore = 0;
        redTeamScore = 0;
        players.clear();
        playerScores.clear();
    }

    public int getRedTeamScore() {
        return blueTeamScore;
    }

    public int getBlueTeamScore() {
        return redTeamScore;
    }

    public int getPlayerScore(int playerId) {
        return playerScores.getOrDefault(playerId, -1);
    }

    public void putPlayer(Player player) {
        players.put(player.getId(), player);
        playerScores.put(player.getId(), 0);
    }

    public void blueTeamHitsRedTeam(int bluePlayerId) {
        blueTeamScore += POINTS_FOR_HIT;
        int newScore = getPlayerScore(bluePlayerId) + POINTS_FOR_HIT;
        playerScores.put(bluePlayerId, newScore);
    }

    public void redTeamHitsBlueTeam(int redPlayerId) {
        redTeamScore += POINTS_FOR_HIT;
        int newScore = getPlayerScore(redPlayerId) + POINTS_FOR_HIT;
        playerScores.put(redPlayerId, newScore);
    }

}
