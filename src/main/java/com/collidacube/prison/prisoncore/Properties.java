package com.collidacube.prison.prisoncore;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Properties {

    private static FileConfiguration config;

    public static String format(String node) {
        return format(node, null, null);
    }

    public static String format(String node, OfflinePlayer player) {
        if (PrisonCore.isPapiEnabled())
            return format(node, player, null);
        else {
            HashMap<String, String> variables = new HashMap<>();
            variables.put("player_name", player.getName());

            Player onlinePlayer = player.getPlayer();
            if (onlinePlayer != null) {
                variables.put("player_displayname", onlinePlayer.getDisplayName());
            }
            return format(node, null, variables);
        }
    }

    public static String format(String node, HashMap<String, String> variables) {
        return format(node, null, variables);
    }

    public static String format(String node, OfflinePlayer player, HashMap<String, String> variables) {
        String message = config.getString(node);
        if (message == null) return null;

        System.out.println(player + " != null && " + PrisonCore.isPapiEnabled());
        if (player != null && PrisonCore.isPapiEnabled()) {
            System.out.println("BEFORE: " + message);
            message = PlaceholderAPI.setPlaceholders(player, message);
            System.out.println("AFTER: " + message);
        }

        if (variables != null) {
            for (String var : variables.keySet()) {
                String val = variables.get(var);
                message = message.replaceAll("%" + var + "%", val);
            }
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void load() {
        PrisonCore plugin = PrisonCore.getInstance();
        plugin.saveDefaultConfig();
        config = plugin.getConfig();

        Utils.ifNotNull(config.getConfigurationSection("economy"), ConfigLoader::loadEco);
        Utils.ifNotNull(config.getConfigurationSection("statistics"), ConfigLoader::loadStats);
    }

}
