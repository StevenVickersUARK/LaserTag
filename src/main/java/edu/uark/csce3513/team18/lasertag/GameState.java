package edu.uark.csce3513.team18.lasertag;

import java.util.HashMap;
import java.util.Map;

public class GameState {

    public enum Team {
        Red,
        Blue
    }

    private static GameState instance;
    private static final int POINTS_FOR_HIT = 10;

    private int blueTeamScore = 0;
    private int redTeamScore = 0;
    // Maps the player ID to the player object (key = player ID, value = object)
    private Map<Integer, Player> players;
    // Maps the field name to the player ID (key = field name, value = player ID)
    private Map<String, Integer> playerFieldNames;
    // Maps the player ID to the player's team (key = player ID, value = team)
    private Map<Integer, Team> playerTeams;
    // Maps the player ID to the player score (key = player ID, value = player
    // score)
    private Map<Integer, Integer> playerScores;
    private String playerFeed = "";

    private void printToFeed(String message) {
        playerFeed += message + '\n';
    }

    private void printHit(int hitterId, int hitteeId) {
        Player hitter = players.get(hitterId);
        Player hittee = players.get(hitteeId);
        String hitterName = hitter.getCodeName();
        String hitteeName = hittee.getCodeName();
        printToFeed(hitterName + " hit " + hitteeName);
    }

    public GameState() {
        players = new HashMap<>();
        playerTeams = new HashMap<>();
        playerFieldNames = new HashMap<>();
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
        playerTeams.clear();
        playerFieldNames.clear();
        playerScores.clear();
        playerFeed = "";
    }

    public String getFeed() {
        return playerFeed;
    }

    public int getRedTeamScore() {
        return redTeamScore;
    }

    public int getBlueTeamScore() {
        return blueTeamScore;
    }

    public int getPlayerScore(int playerId) {
        return playerScores.getOrDefault(playerId, 0);
    }

    public int getPlayerScore(String fieldName) {
        int playerId = playerFieldNames.getOrDefault(fieldName, -1);
        return getPlayerScore(playerId);
    }

    public void putPlayer(Team team, Player player, String fieldName) {
        players.put(player.getId(), player);
        playerTeams.put(player.getId(), team);
        playerFieldNames.put(fieldName, player.getId());
        playerScores.put(player.getId(), 0);
    }

    public void sendHitEvent(int hitterId, int hitteeId) {
        Team hitterTeam = playerTeams.get(hitterId);
        if (hitterTeam == Team.Red) {
            redTeamScore += POINTS_FOR_HIT;
        } else {
            blueTeamScore += POINTS_FOR_HIT;
        }

        int newScore = getPlayerScore(hitterId) + POINTS_FOR_HIT;
        playerScores.put(hitterId, newScore);
        printHit(hitterId, hitteeId);
    }

}
