package com.collidacube.prison.prisoncore.data.economy;

import com.collidacube.prison.prisoncore.data.PrisonEconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.*;

@SuppressWarnings({"deprecation", "DataFlowIssue"})
public class PrisonEconomy {

    protected final HashMap<UUID, Double> balances = new HashMap<>();

    protected boolean enabled = true;
    protected final EconomySettings settings;
    public PrisonEconomy() {
        this(EconomySettings.None);
    }

    public PrisonEconomy(EconomySettings settings) {
        this.settings = settings == null ? EconomySettings.None : settings;
    }

    public EconomySettings getSettings() {
        return settings;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return settings.economyName();
    }

    public boolean hasBankSupport() {
        return false;
    }

    public int fractionalDigits() {
        return settings.fractionalDigits();
    }

    public String format(double amount) {
        return settings.format() == null ?
                String.valueOf(amount) :
                settings.format().formatted(amount);
    }

    public String currencyNameSingular() {
        return settings.currencySingular();
    }

    public String currencyNamePlural() {
        return settings.currencyPlural();
    }

    public boolean hasAccount(String playerName) {
        return true;
    }

    public boolean hasAccount(OfflinePlayer player) {
        return true;
    }

    public boolean hasAccount(String playerName, String worldName) {
        return true;
    }

    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return true;
    }

    public double getBalance(String playerName) {
        return getBalance(Bukkit.getPlayer(playerName));
    }

    public double getBalance(OfflinePlayer player) {
        return getBalance(player.getUniqueId());
    }

    public double getBalance(UUID uuid) {
        return balances.getOrDefault(uuid, settings.initialAmount());
    }

    public double getBalance(String playerName, String world) {
        return getBalance(playerName);
    }

    public double getBalance(OfflinePlayer player, String world) {
        return getBalance(player);
    }

    public double getBalance(UUID uuid, String world) {
        return getBalance(uuid);
    }

    public PrisonEconomyResponse setBalance(String playerName, double amount) {
        return setBalance(Bukkit.getPlayer(playerName), amount);
    }

    public PrisonEconomyResponse setBalance(OfflinePlayer player, double amount) {
        return setBalance(player.getUniqueId(), amount);
    }

    public PrisonEconomyResponse setBalance(UUID uuid, double amount) {
        double oldBalance = getBalance(uuid);
        balances.put(uuid, amount);
        return new PrisonEconomyResponse(amount - oldBalance, amount, PrisonEconomyResponse.ResponseType.SUCCESS, null);
    }

    public PrisonEconomyResponse setBalance(String playerName, String world, double amount) {
        return setBalance(playerName, amount);
    }

    public PrisonEconomyResponse setBalance(OfflinePlayer player, String world, double amount) {
        return setBalance(player, amount);
    }

    public PrisonEconomyResponse setBalance(UUID uuid, String world, double amount) {
        return setBalance(uuid, amount);
    }

    public boolean has(String playerName, double amount) {
        return has(Bukkit.getOfflinePlayer(playerName), amount);
    }

    public boolean has(OfflinePlayer player, double amount) {
        return getBalance(player) >= amount;
    }

    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName, amount);
    }

    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return has(player, amount);
    }

    public PrisonEconomyResponse withdrawPlayer(String playerName, double amount) {
        return withdrawPlayer(Bukkit.getOfflinePlayer(playerName), amount);
    }

    public PrisonEconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        double balance = getBalance(player);

        if (amount < 0)
            return new PrisonEconomyResponse(0, balance, PrisonEconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative amounts!");

        if (balance < amount)
            amount = balance;

        return setBalance(player, balance - amount);
    }

    public PrisonEconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    public PrisonEconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return withdrawPlayer(player, amount);
    }

    public PrisonEconomyResponse depositPlayer(String playerName, double amount) {
        return depositPlayer(Bukkit.getOfflinePlayer(playerName), amount);
    }

    public PrisonEconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        double balance = getBalance(player);

        if (amount < 0)
            return new PrisonEconomyResponse(0, balance, PrisonEconomyResponse.ResponseType.FAILURE, "Cannot deposit negative amounts!");

        return setBalance(player, balance + amount);
    }

    public PrisonEconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
    }

    public PrisonEconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return depositPlayer(player, amount);
    }

    public PrisonEconomyResponse createBank(String name, String player) {
        return createBank(null, (OfflinePlayer) null);
    }

    public PrisonEconomyResponse createBank(String name, OfflinePlayer player) {
        return new PrisonEconomyResponse(0, 0, PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    public PrisonEconomyResponse deleteBank(String name) {
        return new PrisonEconomyResponse(0, 0, PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    public PrisonEconomyResponse bankBalance(String name) {
        return new PrisonEconomyResponse(0, 0, PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    public PrisonEconomyResponse bankHas(String name, double amount) {
        return new PrisonEconomyResponse(0, 0, PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    public PrisonEconomyResponse bankWithdraw(String name, double amount) {
        return new PrisonEconomyResponse(0, 0, PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    public PrisonEconomyResponse bankDeposit(String name, double amount) {
        return new PrisonEconomyResponse(0, 0, PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    public PrisonEconomyResponse isBankOwner(String name, String playerName) {
        return new PrisonEconomyResponse(0, 0, PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    public PrisonEconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return new PrisonEconomyResponse(0, 0, PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    public PrisonEconomyResponse isBankMember(String name, String playerName) {
        return new PrisonEconomyResponse(0, 0, PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    public PrisonEconomyResponse isBankMember(String name, OfflinePlayer player) {
        return new PrisonEconomyResponse(0, 0, PrisonEconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported!");
    }

    public List<String> getBanks() {
        return new ArrayList<>();
    }

    public boolean createPlayerAccount(String playerName) {
        return createPlayerAccount(Bukkit.getOfflinePlayer(playerName));
    }

    public boolean createPlayerAccount(OfflinePlayer player) {
        if (balances.containsKey(player.getUniqueId())) return false;
        balances.put(player.getUniqueId(), settings.initialAmount());
        return true;
    }

    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }

    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return createPlayerAccount(player);
    }

    public Set<UUID> getPlayers() {
        return balances.keySet();
    }

}