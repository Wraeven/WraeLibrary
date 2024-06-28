package me.wraeven.wraelibrary.lib;

import me.wraeven.wraelibrary.WraePlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public class WraePlayerHandler {
    private final HashMap<UUID, WraePlayer> players;

    public WraePlayerHandler() {
        players = new HashMap<>();
    }

    public boolean exists(OfflinePlayer player) {
        return players.containsKey(player.getUniqueId());
    }
    /*
     addPlayer is called every time someone joins.
     If a player is returning between Server restarts, exists will return false.
     */
    public void addPlayer(Player player) {
        if(exists(player)) return;
        players.put(player.getUniqueId(), new WraePlayer(player));
    }
    public void removePlayer(OfflinePlayer player) {
        if(!exists(player)) return;
        players.remove(player.getUniqueId());
    }

    public WraePlayer getPlayer(Player player) {
        if (!exists(player)) {
            addPlayer(player);
            Bukkit.getLogger().warning("Player " + player.getName() + " was created through the getPlayer Method.");
        }
        return players.get(player.getUniqueId());
    }
    public WraePlayer getPlayer(UUID uuid) {
        if(players.containsKey(uuid)) {
            return players.get(uuid);
        }
        return null;
    }

    public HashMap<UUID, WraePlayer> getPlayers() {
        return players;
    }
}
