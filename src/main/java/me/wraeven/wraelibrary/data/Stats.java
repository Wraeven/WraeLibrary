package me.wraeven.wraelibrary.data;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;


public class Stats {
    private int wins;
    private int gamesPlayed;

    public Stats() {
        this.wins = 0;
        this.gamesPlayed = 0;
    }
    public Stats(OfflinePlayer player, FileConfiguration data) {
        wins = data.getInt("Players." + player.getUniqueId() + ".Wins");
        gamesPlayed = data.getInt("Players." + player.getUniqueId() + ".GamesPlayed");
    }
    public void incrementWins(int amount) {wins = wins + amount;}
    public void incrementGamesPlayed(int amount) {gamesPlayed = gamesPlayed + amount;}
    public int getWins() {return wins;}
    public int getGamesPlayed() {return gamesPlayed;}
}
