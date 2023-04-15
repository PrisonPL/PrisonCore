package com.collidacube.prison.prisoncore;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class Utils {

    public static PluginCommand registerCommand(String label, JavaPlugin plugin, TabExecutor tabExecutor) {
        PluginCommand command = plugin.getCommand(label);
        if (command != null) {
            command.setExecutor(tabExecutor);
            command.setTabCompleter(tabExecutor);
        }
        return command;
    }

    public static PluginCommand registerCommand(String label, JavaPlugin plugin, CommandExecutor executor) {
        PluginCommand command = plugin.getCommand(label);
        if (command != null)
            command.setExecutor(executor);
        return command;
    }

    public static PluginCommand registerCommand(String label, JavaPlugin plugin, TabCompleter completer) {
        PluginCommand command = plugin.getCommand(label);
        if (command != null)
            command.setTabCompleter(completer);
        return command;
    }

    public static <T> void ifNotNull(T value, Consumer<T> action) {
        if (value != null) action.accept(value);
    }

}
