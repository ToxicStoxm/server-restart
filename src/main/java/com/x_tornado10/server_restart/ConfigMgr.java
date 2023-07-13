package com.x_tornado10.server_restart;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigMgr {

    public static boolean enable_restart_message;
    public static String restart_message;
    public static boolean timed_messages_10min;
    public static boolean timed_messages_5min;
    public static boolean timed_messages_3min;
    public static boolean timed_messages_2min;
    public static boolean timed_messages_1min;
    public static boolean timed_messages_30sec;
    public static boolean timed_messages_10sec;
    public static boolean timed_messages_5sec;
    public static boolean timed_messages_3sec;
    public static boolean timed_messages_1sec;
    public static boolean timed_messages_10sec_countdown;
    public static List<String> restart_schedule;
    public static boolean schedule_presets_enabled;
    public static boolean schedule_presets_15min;
    public static boolean schedule_presets_30min;
    public static boolean schedule_presets_1h;
    public static boolean schedule_presets_2h;
    public static boolean schedule_presets_4h;
    public static boolean schedule_presets_6h;
    public static boolean schedule_presets_8h;
    public static boolean schedule_presets_12h;
    public static boolean schedule_presets_1d;
    /*
    public static boolean schedule_presets_2d;
    public static boolean schedule_presets_7d;
    public static boolean schedule_presets_14d;
    public static boolean schedule_presets_30d;

     */


    private final Server_Restart plugin = Server_Restart.getInstance();

    public boolean reload(FileConfiguration config) {
        try {

            plugin.reloadConfig();

            enable_restart_message = config.getBoolean(Paths.restart_messages_enabled);
            restart_message = config.getString(Paths.restart_message);

            timed_messages_10min = config.getBoolean(Paths.timed_messages_10min);
            timed_messages_5min = config.getBoolean(Paths.timed_messages_5min);
            timed_messages_3min = config.getBoolean(Paths.timed_messages_3min);
            timed_messages_2min = config.getBoolean(Paths.timed_messages_2min);
            timed_messages_1min = config.getBoolean(Paths.timed_messages_1min);
            timed_messages_30sec = config.getBoolean(Paths.timed_messages_30sec);
            timed_messages_10sec = config.getBoolean(Paths.timed_messages_10sec);
            timed_messages_5sec = config.getBoolean(Paths.timed_messages_5sec);
            //timed_messages_3sec = config.getBoolean(Paths.timed_messages_3sec);
            timed_messages_1sec = config.getBoolean(Paths.timed_messages_1sec);
            timed_messages_10sec_countdown = config.getBoolean(Paths.timed_messages_10sec_countdown);

            restart_schedule = config.getStringList(Paths.restart_schedule);

            schedule_presets_enabled = config.getBoolean(Paths.schedule_presets_enabled);

            schedule_presets_15min = config.getBoolean(Paths.schedule_presets_15min);
            schedule_presets_30min = config.getBoolean(Paths.schedule_presets_30min);
            schedule_presets_1h = config.getBoolean(Paths.schedule_presets_1h);
            schedule_presets_2h = config.getBoolean(Paths.schedule_presets_2h);
            schedule_presets_4h = config.getBoolean(Paths.schedule_presets_4h);
            schedule_presets_6h = config.getBoolean(Paths.schedule_presets_6h);
            schedule_presets_8h = config.getBoolean(Paths.schedule_presets_8h);
            schedule_presets_12h = config.getBoolean(Paths.schedule_presets_12h);
            schedule_presets_1d = config.getBoolean(Paths.schedule_presets_1d);
            /*
            schedule_presets_2d = config.getBoolean(Paths.schedule_presets_2d);
            schedule_presets_7d = config.getBoolean(Paths.schedule_presets_7d);
            schedule_presets_14d = config.getBoolean(Paths.schedule_presets_14d);
            schedule_presets_30d = config.getBoolean(Paths.schedule_presets_30d);

             */



            plugin.saveConfig();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
