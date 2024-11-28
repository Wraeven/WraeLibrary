package me.wraeven.wraelibrary.lib;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Timer {
    private int time;
    private String formattedTime;
    private final Plugin plugin;
    private BukkitTask task;

    public enum Direction {
        UP, DOWN
    }

    public Timer(Plugin plugin, int startingTime) {
        this.time = startingTime;
        this.plugin = plugin;
    }

    public void formatTime() {
        int hours = this.time / 3600;
        int minutes = this.time % 3600 / 60;
        int seconds = this.time % 60;

        formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void startTimer(Direction direction) {
        if(direction == Direction.UP) {
            countUp();
        } else if(direction == Direction.DOWN) {
            countDown();
        }

    }
    private void countUp() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                time++;
                formatTime();
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
    private void countDown() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if(time > 0) {
                    time--;
                    formatTime();
                }
                else {
                    Timer.this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
    private void cancel(){
        if(task != null) {
            if(!task.isCancelled()) {
                task.cancel();
            }
        }
    }
}
