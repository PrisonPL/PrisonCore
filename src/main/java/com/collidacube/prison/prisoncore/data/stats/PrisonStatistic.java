package com.collidacube.prison.prisoncore.data.stats;

import com.collidacube.prison.prisoncore.data.PrisonEconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings({"deprecation", "DataFlowIssue"})
public class PrisonStatistic {

    protected final HashMap<UUID, Integer> statistics = new HashMap<>();

    protected boolean enabled = true;
    protected final StatisticSettings settings;
    public PrisonStatistic() {
        this(StatisticSettings.None);
    }

    public PrisonStatistic(StatisticSettings settings) {
        this.settings = settings;
    }

    public StatisticSettings getSettings() {
        return settings;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return settings.statisticName();
    }

    public boolean hasBankSupport() {
        return false;
    }

    public String format(int amount) {
        return settings.format() == null ?
                String.valueOf(amount) :
                settings.format().formatted(amount);
    }

    public String valueNameSingular() {
        return settings.valueSingular();
    }

    public String valueNamePlural() {
        return settings.valuePlural();
    }

    public int getValue(String playerName) {
        return getValue(Bukkit.getPlayer(playerName));
    }

    public int getValue(OfflinePlayer player) {
        return getValue(player.getUniqueId());
    }

    public int getValue(UUID uuid) {
        return statistics.getOrDefault(uuid, settings.initialAmount());
    }

    public int getValue(String playerName, String world) {
        return getValue(playerName);
    }

    public int getValue(OfflinePlayer player, String world) {
        return getValue(player);
    }

    public int getValue(UUID uuid, String world) {
        return getValue(uuid);
    }

    public PrisonEconomyResponse setValue(String playerName, int amount) {
        return setValue(Bukkit.getPlayer(playerName), amount);
    }

    public PrisonEconomyResponse setValue(OfflinePlayer player, int amount) {
        return setValue(player.getUniqueId(), amount);
    }

    public PrisonEconomyResponse setValue(UUID uuid, int amount) {
        int oldValue = getValue(uuid);
        statistics.put(uuid, amount);
        return new PrisonEconomyResponse(amount - oldValue, amount, PrisonEconomyResponse.ResponseType.SUCCESS, null);
    }

    public PrisonEconomyResponse setValue(String playerName, String world, int amount) {
        return setValue(playerName, amount);
    }

    public PrisonEconomyResponse setValue(OfflinePlayer player, String world, int amount) {
        return setValue(player, amount);
    }

    public PrisonEconomyResponse setValue(UUID uuid, String world, int amount) {
        return setValue(uuid, amount);
    }

    public boolean has(String playerName, int amount) {
        return has(Bukkit.getOfflinePlayer(playerName), amount);
    }

    public boolean has(OfflinePlayer player, int amount) {
        return getValue(player) >= amount;
    }

    public boolean has(String playerName, String worldName, int amount) {
        return has(playerName, amount);
    }

    public boolean has(OfflinePlayer player, String worldName, int amount) {
        return has(player, amount);
    }

    public PrisonEconomyResponse withdrawPlayer(String playerName, int amount) {
        return withdrawPlayer(Bukkit.getOfflinePlayer(playerName), amount);
    }

    public PrisonEconomyResponse withdrawPlayer(OfflinePlayer player, int amount) {
        int value = getValue(player);

        if (amount < 0)
            return new PrisonEconomyResponse(0, value, PrisonEconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative amounts!");

        if (value < amount)
            amount = value;

        return setValue(player, value - amount);
    }

    public PrisonEconomyResponse withdrawPlayer(String playerName, String worldName, int amount) {
        return withdrawPlayer(playerName, amount);
    }

    public PrisonEconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, int amount) {
        return withdrawPlayer(player, amount);
    }

    public PrisonEconomyResponse depositPlayer(String playerName, int amount) {
        return depositPlayer(Bukkit.getOfflinePlayer(playerName), amount);
    }

    public PrisonEconomyResponse depositPlayer(OfflinePlayer player, int amount) {
        int value = getValue(player);

        if (amount < 0)
            return new PrisonEconomyResponse(0, value, PrisonEconomyResponse.ResponseType.FAILURE, "Cannot deposit negative amounts!");

        return setValue(player, value + amount);
    }

    public PrisonEconomyResponse depositPlayer(String playerName, String worldName, int amount) {
        return depositPlayer(playerName, amount);
    }

    public PrisonEconomyResponse depositPlayer(OfflinePlayer player, String worldName, int amount) {
        return depositPlayer(player, amount);
    }

    public boolean createPlayerAccount(String playerName) {
        return createPlayerAccount(Bukkit.getOfflinePlayer(playerName));
    }

    public boolean createPlayerAccount(OfflinePlayer player) {
        if (statistics.containsKey(player.getUniqueId())) return false;
        statistics.put(player.getUniqueId(), settings.initialAmount());
        return true;
    }

    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }

    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return createPlayerAccount(player);
    }

    public Set<UUID> getPlayers() {
        return statistics.keySet();
    }

}