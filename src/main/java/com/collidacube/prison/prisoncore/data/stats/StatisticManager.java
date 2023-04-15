package com.collidacube.prison.prisoncore.data.stats;

import com.collidacube.prison.prisoncore.data.PrisonEconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class StatisticManager {

    private static final HashMap<String, PrisonStatistic> statistics = new HashMap<>();
    public static boolean registerStatistic(PrisonStatistic statistic) {
        if (statistic.getName() == null) return false;
        return statistics.putIfAbsent(statistic.getName(), statistic) == null;
    }

    public static boolean registerStatistic(StatisticSettings settings) {
        return registerStatistic(new PrisonStatistic(settings));
    }

    public static PrisonStatistic getStatistic(String name) {
        return statistics.get(name);
    }

    protected final OfflinePlayer player;
    public StatisticManager(OfflinePlayer player) {
        this.player = player;
    }

    public <T> T value(String statisticName, Function<PrisonStatistic, T> editor) {
        PrisonStatistic statistic = getStatistic(statisticName);
        if (statistic == null) throw new NoSuchElementException();
        return editor.apply(statistic);
    }

    public int getValue(String statisticName) {
        return value(statisticName, stat -> stat.getValue(player));
    }

    public PrisonEconomyResponse setValue(String statisticName, int amount) {
        int value = getValue(statisticName);
        if (value < amount) return deposit(statisticName, amount - value);
        else return withdraw(statisticName, value - amount);
    }

    public PrisonEconomyResponse deposit(String statisticName, int amount) {
        return value(statisticName, stat -> stat.depositPlayer(player, amount));
    }

    public PrisonEconomyResponse withdraw(String statisticName, int amount) {
        return value(statisticName, stat -> stat.withdrawPlayer(player, amount));
    }

    public boolean has(String statisticName, int amount) {
        return value(statisticName, stat -> stat.has(player, amount));
    }

    public static Collection<PrisonStatistic> getStatistics() {
        return statistics.values();
    }

}
