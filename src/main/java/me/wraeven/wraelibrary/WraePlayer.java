package me.wraeven.wraelibrary;

import me.wraeven.wraelibrary.data.Stats;
import me.wraeven.wraelibrary.files.FileHandler;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class WraePlayer {
    private final OfflinePlayer player;

    private Stats playerStats;

    public WraePlayer(Player player) {
        this.player = player;
        initialize();
    }

    protected void initialize() {
        if(!player.hasPlayedBefore()) {
            playerStats = new Stats();
            return;
        }
        playerStats = new Stats(player, FileHandler.playersConfig);
    }

    public String getName() {
        return player.getName();
    }

    public OfflinePlayer getPlayer() {return player;}
    public Stats getPlayerStats() {return playerStats;}
}
