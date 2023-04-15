package com.collidacube.prison.prisoncore.commands;

import com.collidacube.prison.prisoncore.data.DataManager;
import com.collidacube.prison.prisoncore.data.stats.PrisonStatistic;
import com.collidacube.prison.prisoncore.data.stats.StatisticManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

public class StatisticCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        OfflinePlayer player = parsePlayerArg(sender, cmd, label, args);
        PrisonStatistic statistic = parseStatisticArg(sender, cmd, label, args);
        BiConsumer<PrisonStatistic, OfflinePlayer> action = parseActionArg(sender, cmd, label, args);

        if (player != null && statistic != null && action != null)
            action.accept(statistic, player);
        else showUsage(sender, cmd, label, args);

        return true;
    }

    private void showUsage(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("§cCorrect Usage: /statistic <player> <statistic> [<add|remove|set> <amount>|reset]");
    }

    private OfflinePlayer parsePlayerArg(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) return null;

        OfflinePlayer player = DataManager.getPlayer(args[0]);
        if (player == null)
            sender.sendMessage("§cThat player has not joined the server yet!");

        return player;
    }

    private PrisonStatistic parseStatisticArg(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) return null;

        PrisonStatistic stat = StatisticManager.getStatistic(args[1]);
        if (stat == null)
            sender.sendMessage("§cThis statistic does not exist!");

        return stat;
    }

    private BiConsumer<PrisonStatistic, OfflinePlayer> parseActionArg(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length < 3) return null;

        if (args[2].equalsIgnoreCase("reset"))
            return (stat, p) -> stat.setValue(p, stat.getSettings().initialAmount());

        if (args.length < 4) return null;

        int amount;
        try { amount = Integer.parseInt(args[3]); } catch (NumberFormatException e) {
            sender.sendMessage("§cInvalid amount!");
            return null;
        }

        if (args[2].equalsIgnoreCase("add"))
            return (stat, p) -> stat.setValue(p, stat.getValue(p) + amount);

        if (args[3].equalsIgnoreCase("remove"))
            return (stat, p) -> stat.setValue(p, stat.getValue(p) - amount);

        if (args[3].equalsIgnoreCase("set"))
            return (stat, p) -> stat.setValue(p, amount);

        sender.sendMessage("§cInvalid action! Must be add, remove, set, or reset!");
        return null;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return filterByPrompt(args[0], DataManager.playersByUsername.keySet());
        if (args.length == 2) return filterByPrompt(args[1], StatisticManager.getStatistics().stream().map(PrisonStatistic::getName).toList());
        if (args.length == 3) return filterByPrompt(args[2], "add", "remove", "set", "reset");
        if (args.length == 4 && !args[2].equals("reset")) return Collections.singletonList("<number>");
        return null;
    }

    public List<String> filterByPrompt(String prompt, Collection<String> options) {
        return options.stream()
                .filter(option -> option.startsWith(prompt))
                .toList();
    }

    public List<String> filterByPrompt(String prompt, String... options) {
        return Arrays.stream(options)
                .filter(option -> option.startsWith(prompt))
                .toList();
    }

}
