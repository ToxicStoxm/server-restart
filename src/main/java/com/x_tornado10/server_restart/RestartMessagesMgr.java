package com.x_tornado10.server_restart;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestartMessagesMgr {

    private List<LocalTime> restart_schedule;
    private String rMessage;

    public RestartMessagesMgr(List<LocalTime> restart_schedule, String restart_message) {

        this.restart_schedule = new ArrayList<>();
        this.restart_schedule.addAll(restart_schedule);
        this.rMessage = restart_message;
    }
    private final Server_Restart plugin = Server_Restart.getInstance();


    public void run() {

        BukkitTask bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                List<LocalTime> to_remove = new ArrayList<>();
                for (LocalTime lT : restart_schedule) {


                    LocalTime lTemp = lT.minusMinutes(10);

                    LocalDateTime lDT = LocalDateTime.now();
                    LocalTime lzero = LocalTime.parse("00:00");
                    LocalTime lzerob = LocalTime.parse("23:50");
                    LocalTime lTCurrent = LocalTime.ofSecondOfDay(lDT.toLocalTime().toSecondOfDay());
                    if (lTCurrent.equals(lTemp) || lTCurrent.isAfter(lTemp) && lTCurrent.isBefore(lT) || lT.equals(lzero) && lTCurrent.isAfter(lzerob)) {

                        new RMSender(rMessage, lT);
                        to_remove.add(lT);

                    }

                }

                for (LocalTime localTime : to_remove) {

                    restart_schedule.remove(localTime);

                }


            }
        }.runTaskTimer(plugin, 0, 20);

    }

}
