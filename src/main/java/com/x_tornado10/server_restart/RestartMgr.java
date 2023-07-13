package com.x_tornado10.server_restart;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestartMgr {

    private List<LocalTime> restart_schedule;
    private Logger logger;

    public RestartMgr(List<LocalTime> restart_schedule) {

        this.restart_schedule = new ArrayList<>();
        this.restart_schedule.addAll(restart_schedule);
        logger = plugin.getCustomLogger();

    }
    private final Server_Restart plugin = Server_Restart.getInstance();


    public void run() {

        BukkitTask bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (LocalTime lT : restart_schedule) {

                    LocalDateTime lDT = LocalDateTime.now();
                    LocalTime lTCurrent = LocalTime.ofSecondOfDay(lDT.toLocalTime().toSecondOfDay());
                    if (lTCurrent.equals(lT) || lTCurrent.isAfter(lT) && lTCurrent.isBefore(lT.plusSeconds(2))) {

                        logger.broadcast("§c§lServer is restarting...", false);
                        Bukkit.spigot().restart();

                    }

                }



            }
        }.runTaskTimer(plugin, 0, 5);

    }

}
