package me.wraeven.wraelibrary;

import me.wraeven.wraelibrary.events.LibEvents;
import me.wraeven.wraelibrary.files.FileHandler;
import me.wraeven.wraelibrary.lib.WraePlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class WraeLibrary extends JavaPlugin {
    private static WraeLibrary instance;
    private final WraePlayerHandler handler;
    private final LibEvents events;
    private final FileHandler fileHandler;

    public WraeLibrary() {
        instance = this;
        this.handler = new WraePlayerHandler();
        this.events = new LibEvents(handler);
        this.fileHandler = new FileHandler(this, handler);
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(events, this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            long currentTime = System.currentTimeMillis() / 1000;
            HashMap<UUID, Long> logoutTimes = events.getPlayerLogoutTimes();
            if(!logoutTimes.isEmpty()) {
                logoutTimes.entrySet().removeIf(entry -> {
                    UUID playerUUID = entry.getKey();
                    long logoutTime = entry.getValue();

                    if((currentTime - logoutTime) > 1800) {
                        if(handler.exists(Bukkit.getOfflinePlayer(playerUUID))) {
                            fileHandler.savePlayer(handler.getPlayer(playerUUID), playerUUID);
                            handler.removePlayer(Bukkit.getOfflinePlayer(playerUUID));
                            return true;
                        }
                    }
                    return false;
                });
            }
        }, 0L, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        fileHandler.savePlayers();
        instance = null;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public LibEvents getEvents() {
        return events;
    }

    public WraePlayerHandler getHandler() {
        return handler;
    }

    public static WraeLibrary getInstance() {
        return instance;
    }
}
