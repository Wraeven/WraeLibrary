package me.wraeven.wraelibrary.data;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

public class Stats {
    private final int wins;
    private final int gamesPlayed;

    public Stats() {
        this.wins = 0;
        this.gamesPlayed = 0;
    }
    public Stats(OfflinePlayer player, FileConfiguration data) {
        wins = data.getInt("Players." + player.getUniqueId() + ".Wins");
        gamesPlayed = data.getInt("Players." + player.getUniqueId() + ".GamesPlayed");
    }

    public int getWins() {return wins;}
    public int getGamesPlayed() {return gamesPlayed;}
}
