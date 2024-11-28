package me.wraeven.wraelibrary.files;

import me.wraeven.wraelibrary.WraePlayer;
import me.wraeven.wraelibrary.lib.WraePlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileHandler {

    private final Plugin plugin;
    private final WraePlayerHandler playerHandler;

    private File players;
    public static FileConfiguration playersConfig;

    public FileHandler(Plugin plugin, WraePlayerHandler handler) {
        this.plugin = plugin;
        this.playerHandler = handler;
        createPlayers();
    }

    public void createPlayers() {
        players = new File(plugin.getDataFolder(), "players.yml");
        if (!players.exists()) {
            try {
                players.getParentFile().mkdirs();
                players.createNewFile();
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("Failure to create new file players.yml in Plugin: " + plugin.getName());

            }
        }
        playersConfig = YamlConfiguration.loadConfiguration(players);
    }

    public void savePlayers() {
        for (WraePlayer pl : playerHandler.getPlayers().values()) {
            playersConfig.set("Players." + pl.getPlayer().getUniqueId() + ".Name", pl.getName());
            playersConfig.set("Players." + pl.getPlayer().getUniqueId() + ".Stats.Wins", pl.getPlayerStats().getWins());
            playersConfig.set("Players." + pl.getPlayer().getUniqueId() + ".Stats.Games_Played", pl.getPlayerStats().getGamesPlayed());
        }
        try {
            playersConfig.save(players);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failure to save players.yml in Plugin: " + plugin.getName());
        }
    }

    public void savePlayer(WraePlayer Offline, UUID player) {
        playersConfig.set("Players." + player + ".Name", Offline.getName());
        playersConfig.set("Players." + player + ".Stats.Wins", Offline.getPlayerStats().getWins());
        playersConfig.set("Players." + player + ".Stats.Games_Played", Offline.getPlayerStats().getGamesPlayed());

        try {
            playersConfig.save(players);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failure to save Player: " + Offline.getName() + " in Plugin: " + plugin.getName());
        }
    }
}
