package com.x_tornado10.server_restart;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.x_tornado10.server_restart.ConfigMgr.schedule_presets_enabled;


public final class Server_Restart extends JavaPlugin {

    private static Server_Restart instance;
    private ConfigMgr configMgr;
    long start;
    private RestartMgr restartMgr;
    private RestartMessagesMgr rM;
    private TextFormatting txtFormatting;
    private Logger logger;
    private PlayerMessages plmsg;
    private final String prefix = "§c[SR]: ";

    @Override
    public void onLoad() {
        instance = this;
        start = System.currentTimeMillis();

        configMgr = new ConfigMgr();
        txtFormatting = new TextFormatting();
        logger = new Logger(txtFormatting.stripColorCodes(prefix));
        plmsg = new PlayerMessages(prefix);
        saveDefaultConfig();

    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        configMgr.reload(getConfig());
        List<LocalTime> restart_schedule = new ArrayList<>();
        if (schedule_presets_enabled) {

            if (ConfigMgr.schedule_presets_15min) {
                int hours = 0;
                int minutes = 0;

                while (hours != 24) {
                    String hourString = String.format("%02d", hours);
                    String minuteString = String.format("%02d", minutes);

                    String timeString = hourString + ":" + minuteString;
                    if (isSeparatedByTenMinutes(timeString, ConfigMgr.restart_schedule)) {
                        ConfigMgr.restart_schedule.add(timeString);
                    }

                    minutes += 15;
                    if (minutes >= 60) {
                        minutes = 0;
                        hours++;
                    }
                }

            }

            if (ConfigMgr.schedule_presets_30min) {
                int hours = 0;
                int minutes = 0;

                while (hours != 24) {
                    String hourString = String.format("%02d", hours);
                    String minuteString = String.format("%02d", minutes);

                    String timeString = hourString + ":" + minuteString;
                    if (isSeparatedByTenMinutes(timeString, ConfigMgr.restart_schedule)) {
                        ConfigMgr.restart_schedule.add(timeString);
                    }
                    minutes += 30;
                    if (minutes >= 60) {
                        minutes = 0;
                        hours++;
                    }
                }

            }
            if (ConfigMgr.schedule_presets_1h) {
                int hours = 0;
                int minutes = 0;
                int duration = 24; // Duration in hours (24 hours = 1 day)

                while (hours < duration) {
                    String hourString = String.format("%02d", hours);
                    String minuteString = String.format("%02d", minutes);

                    String timeString = hourString + ":" + minuteString;
                    if (isSeparatedByTenMinutes(timeString, ConfigMgr.restart_schedule)) {
                        ConfigMgr.restart_schedule.add(timeString);
                    }
                    hours++;
                }

            }
            if (ConfigMgr.schedule_presets_2h) {
                int hours = 0;
                int minutes = 0;
                int duration = 24;

                while (hours < duration) {
                    String hourString = String.format("%02d", hours);
                    String minuteString = String.format("%02d", minutes);

                    String timeString = hourString + ":" + minuteString;
                    if (isSeparatedByTenMinutes(timeString, ConfigMgr.restart_schedule)) {
                        ConfigMgr.restart_schedule.add(timeString);
                    }
                    hours += 2;
                }

            }
            if (ConfigMgr.schedule_presets_4h) {
                int hours = 0;
                int minutes = 0;
                int duration = 24; // Duration in hours (24 hours = 1 day)

                while (hours < duration) {
                    String hourString = String.format("%02d", hours);
                    String minuteString = String.format("%02d", minutes);

                    String timeString = hourString + ":" + minuteString;
                    if (isSeparatedByTenMinutes(timeString, ConfigMgr.restart_schedule)) {
                        ConfigMgr.restart_schedule.add(timeString);
                    }
                    hours += 4;
                }

            }
            if (ConfigMgr.schedule_presets_6h) {
                int hours = 0;
                int minutes = 0;
                int duration = 24; // Duration in hours (24 hours = 1 day)

                while (hours < duration) {
                    String hourString = String.format("%02d", hours);
                    String minuteString = String.format("%02d", minutes);

                    String timeString = hourString + ":" + minuteString;
                    if (isSeparatedByTenMinutes(timeString, ConfigMgr.restart_schedule)) {
                        ConfigMgr.restart_schedule.add(timeString);
                    }
                    hours += 6;
                }

            }
            if (ConfigMgr.schedule_presets_8h) {
                int hours = 0;
                int minutes = 0;
                int duration = 24; // Duration in hours (24 hours = 1 day)

                while (hours < duration) {
                    String hourString = String.format("%02d", hours);
                    String minuteString = String.format("%02d", minutes);

                    String timeString = hourString + ":" + minuteString;
                    if (isSeparatedByTenMinutes(timeString, ConfigMgr.restart_schedule)) {
                        ConfigMgr.restart_schedule.add(timeString);
                    }
                    hours += 8;
                }

            }
            if (ConfigMgr.schedule_presets_12h) {
                int hours = 0;
                int minutes = 0;
                int duration = 24; // Duration in hours (24 hours = 1 day)

                while (hours < duration) {
                    String hourString = String.format("%02d", hours);
                    String minuteString = String.format("%02d", minutes);

                    String timeString = hourString + ":" + minuteString;
                    if (isSeparatedByTenMinutes(timeString, ConfigMgr.restart_schedule)) {
                        ConfigMgr.restart_schedule.add(timeString);
                    }
                    hours += 12;
                }

            }
            if (ConfigMgr.schedule_presets_1d) {
                String timeString = "00:00";
                if (isSeparatedByTenMinutes(timeString, ConfigMgr.restart_schedule)) {
                    ConfigMgr.restart_schedule.add(timeString);
                }
            }
        }

        ConfigMgr.restart_schedule.sort(Comparator.naturalOrder());
        if (!ConfigMgr.restart_schedule.isEmpty()) {


            for (String s : ConfigMgr.restart_schedule) {

                LocalTime lT = LocalTime.parse(s);
                restart_schedule.add(lT);

            }

        }

        for (int i = 1; i < restart_schedule.size(); i++) {
            LocalTime previous = restart_schedule.get(i - 1);
            LocalTime current = restart_schedule.get(i);

            if (previous.until(current, java.time.temporal.ChronoUnit.MINUTES) < 10) {
                restart_schedule.remove(i);
                i--;
            }
        }

        /*
        for (LocalTime lT : restart_schedule) {

            logger.info(String.valueOf(lT));

        }
         */

        restartMgr = new RestartMgr(restart_schedule);
        restartMgr.run();
        if (ConfigMgr.enable_restart_message) {

            rM = new RestartMessagesMgr(restart_schedule, ConfigMgr.restart_message);
            rM.run();

        }


        long timeElapsed = System.currentTimeMillis() - start;
        logger.info(ChatColor.GREEN + "Successfully enabled (took " + timeElapsed / 1000 + "." + timeElapsed % 1000 + "s)");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Server_Restart getInstance() {
        return instance;
    }

    public Logger getCustomLogger() {
        return logger;
    }

    public TextFormatting getTxtformatting() {
        return txtFormatting;
    }

    public PlayerMessages getPlayerMessages() {
        return plmsg;
    }
    public static boolean isSeparatedByTenMinutes(String newTimeString, List<String> existingTimes){
        LocalTime newTime;
        try {
            newTime = LocalTime.parse(newTimeString);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
        for (String existingTime : existingTimes) {
            LocalTime existingTimeParsed = LocalTime.parse(existingTime);
            long minutesBetween = existingTimeParsed.until(newTime, ChronoUnit.MINUTES);
            if (Math.abs(minutesBetween) < 10) {
                return false;
            }
        }
        return true;
    }
}
