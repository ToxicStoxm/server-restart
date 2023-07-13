package com.x_tornado10.server_restart;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Logger {

    private String prefix;
    private Server_Restart plugin;
    private ConsoleCommandSender commandSender;
    private PluginLogger logger;
    private TextFormatting textFormatting;

    public Logger(String prefix) {

        this.prefix = prefix;
        plugin = Server_Restart.getInstance();
        logger = new PluginLogger(plugin);
        commandSender = Bukkit.getConsoleSender();
        textFormatting = plugin.getTxtformatting();

    }

    public void info(String message) {

            //logger.info(prefix + message);
            commandSender.sendMessage(prefix + message);

    }

    public void warning(String message) {

            //logger.warning(prefix + "§6" + message + "§r");
            commandSender.sendMessage(prefix + "§6" + message + "§r");

    }

    public void severe(String message) {

            //logger.severe(prefix + "§4" + message + "§r");
            commandSender.sendMessage(prefix + "§4" + message + "§r");

    }
    public void broadcast(String message, boolean color) {

        info(color ? message : textFormatting.stripColorAndFormattingCodes(message));
        plugin.getPlayerMessages().msg(Bukkit.getOnlinePlayers(), message);

    }

    public void broadcast(String message, boolean color, List<UUID> exclude) {

        info(color ? message : textFormatting.stripColorAndFormattingCodes(message));

        Collection<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());

        players.removeIf(player -> exclude.contains(player.getUniqueId()));

        plugin.getPlayerMessages().msg(players, message);

    }
    public void upDateValues(String prefix) {

        this.prefix = prefix;

    }

}
