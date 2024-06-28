package me.wraeven.wraelibrary.events;

import me.wraeven.wraelibrary.WraeLibrary;
import me.wraeven.wraelibrary.lib.WraePlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class LibEvents implements Listener {

    private final HashMap<UUID, Long> playerLogoutTimes = new HashMap<>();

    private final WraePlayerHandler handler;
    public LibEvents(WraePlayerHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        handler.addPlayer(p);

        playerLogoutTimes.remove(p.getUniqueId());
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID id = player.getUniqueId();
        long logoutTime;

        if(handler.exists(player)) {
            logoutTime = System.currentTimeMillis() / 1000;
            playerLogoutTimes.put(id, logoutTime);
        }

    }
    public HashMap<UUID, Long> getPlayerLogoutTimes() {
        return playerLogoutTimes;
    }
}
