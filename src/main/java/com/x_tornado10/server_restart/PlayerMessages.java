package com.x_tornado10.server_restart;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Collection;

public class PlayerMessages {

    private String prefix;

    private String line = "§7---------------------------------------§r";

    public PlayerMessages (String prefix) {

        this.prefix = prefix;

    }

    public void msg(Player player, String message) {

        player.sendMessage(prefix + "§7" + message + "§r");

    }

    public void msg(ArrayList<Player> players, String message) {

        for (Player player : players) {

            msg(player ,"§7" + message + "§r");

        }

    }

    public void msg(Collection<? extends Player> players, String message) {

        for (Player player : players) {

            msg(player , "§7" + message + "§r");

        }

    }

    public void line(Player p) {

        msg(p,line);

    }

    public String getLine() {
        return line;
    }

    public void msg_inlines(Player p, String message) {

        line(p);
        msg(p,message);
        line(p);

    }

    public void upDateValues(String prefix) {

        this.prefix = prefix;

    }

}

