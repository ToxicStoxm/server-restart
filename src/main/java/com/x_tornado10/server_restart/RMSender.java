package com.x_tornado10.server_restart;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.LocalTime;

public class RMSender {

    private final Server_Restart plugin = Server_Restart.getInstance();
    private String rMessage;
    private Logger logger;
    private LocalTime restart;

    public RMSender(String restart_Message, LocalTime restart) throws NullPointerException {
        if (restart_Message == null) {
            throw new NullPointerException();
        }
        this.rMessage = restart_Message;
        this.restart = restart;
        logger = plugin.getCustomLogger();
        timedMessages();

    }

    public void timedMessages() {

        boolean tenM = ConfigMgr.timed_messages_10min;
        boolean fiveM = ConfigMgr.timed_messages_5min;
        boolean threeM = ConfigMgr.timed_messages_3min;
        boolean twoM = ConfigMgr.timed_messages_2min;
        boolean oneM = ConfigMgr.timed_messages_1min;
        boolean thirtyS = ConfigMgr.timed_messages_30sec;
        boolean tenS = ConfigMgr.timed_messages_10sec;
        boolean fiveS = ConfigMgr.timed_messages_5sec;
        //boolean threeS = ConfigMgr.timed_messages_3sec;
        boolean oneS = ConfigMgr.timed_messages_1sec;
        boolean tenSCountdown = ConfigMgr.timed_messages_10sec_countdown;

        logger.broadcast("§c§lNext Server Restart: " + restart.toString(), false);

        BukkitTask bukkitTask = new BukkitRunnable() {

            int Minutes;
            int Seconds;

            @Override
            public void run() {

                int secondsBetween = restart.toSecondOfDay() - LocalTime.now().toSecondOfDay();
                LocalTime difference = LocalTime.ofSecondOfDay(Math.abs(secondsBetween));
                Minutes = difference.getMinute();
                Seconds = difference.getSecond();
                //logger.broadcast(Minutes + " min | " + Seconds + " sec", false);

                if (Minutes == 10 && Seconds == 0 && tenM) {
                    sendMessage(Minutes, Seconds);
                }

                if (Minutes > 0) {
                    if (Minutes == 5 && Seconds == 0 && fiveM) {
                        sendMessage(Minutes, Seconds);
                    }
                    if (Minutes == 3 && Seconds == 0 && threeM) {
                        sendMessage(Minutes, Seconds);
                    }
                    if (Minutes == 2 && Seconds == 0 && twoM) {
                        sendMessage(Minutes, Seconds);
                    }
                    if (Minutes == 1 && Seconds == 0 && oneM) {
                        sendMessage(Minutes, Seconds);
                    }
                } else {
                    if (Minutes == 0 && Seconds == 30 && thirtyS) {
                        sendMessage(Minutes, Seconds);
                    }
                    if (!tenSCountdown) {
                        if (Minutes == 0 && Seconds == 10 && tenS) {
                            sendMessage(Minutes, Seconds);
                        }
                        if (Minutes == 0 && Seconds == 5 && fiveS) {
                            sendMessage(Minutes, Seconds);
                        }
                        if (Minutes == 0 && Seconds == 1 && oneS) {
                            sendMessage(Minutes, Seconds);
                        }
                    } else {
                        if (Minutes == 0 && Seconds < 11) {
                            sendMessage(Minutes, Seconds);
                        }


                    }
                    if (Seconds == 0 && Minutes == 0 || Minutes > 10) {cancel();}
                }

            }
        }.runTaskTimer(plugin, 0, 20);


    }

    public void sendMessage(int Minutes, int Seconds) {

        boolean minORsec = Seconds == 0;
        String min = String.valueOf(Minutes);
        String sec = String.valueOf(Seconds);
        String minutes = min + (Minutes > 1 ? " Minutes" : " Minute");
        String seconds = sec + (Seconds > 1 ? " Seconds" : " Second");

        String rMessageTemp = rMessage.replace("%TIME%", minORsec ? minutes : seconds);
        logger.broadcast(rMessageTemp, false);

    }

}
