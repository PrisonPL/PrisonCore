package com.collidacube.prison.prisoncore.data.economy.vault;

import com.collidacube.prison.prisoncore.data.economy.EconomySettings;
import com.collidacube.prison.prisoncore.data.economy.PrisonEconomy;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class VaultEconomyHook implements Economy {

    private final PrisonEconomy eco;
    public VaultEconomyHook() {
        this(null);
    }

    public VaultEconomyHook(EconomySettings settings) {
        eco = new PrisonEconomy(settings);
    }

    public PrisonEconomy getPrisonEconomy() {
        return eco;
    }

    @Override
    public boolean isEnabled() {
        return eco.isEnabled();
    }

    @Override
    public String getName() {
        return eco.getName();
    }

    @Override
    public boolean hasBankSupport() {
        return eco.hasBankSupport();
    }

    @Override
    public int fractionalDigits() {
        return eco.fractionalDigits();
    }

    @Override
    public String format(double amount) {
        return eco.format(amount);
    }

    @Override
    public String currencyNamePlural() {
        return eco.currencyNamePlural();
    }

    @Override
    public String currencyNameSingular() {
        return eco.currencyNameSingular();
    }

    @Override
    public boolean hasAccount(String playerName) {
        return eco.hasAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return eco.hasAccount(player);
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return eco.hasAccount(playerName, worldName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return eco.hasAccount(player, worldName);
    }

    @Override
    public double getBalance(String playerName) {
        return eco.getBalance(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return eco.getBalance(player);
    }

    @Override
    public double getBalance(String playerName, String world) {
        return eco.getBalance(playerName, world);
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return eco.getBalance(player, world);
    }

    @Override
    public boolean has(String playerName, double amount) {
        return eco.has(playerName, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return eco.has(player, amount);
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return eco.has(playerName, worldName, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return eco.has(player, worldName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.withdrawPlayer(playerName, amount));
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.withdrawPlayer(player, amount));
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.withdrawPlayer(playerName, worldName, amount));
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.withdrawPlayer(player, worldName, amount));
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.depositPlayer(playerName, amount));
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.depositPlayer(player, amount));
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.depositPlayer(playerName, worldName, amount));
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.depositPlayer(player, worldName, amount));
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return VaultAdapter.adaptEconomyResponse(eco.createBank(name, player));
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return VaultAdapter.adaptEconomyResponse(eco.createBank(name, player));
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return VaultAdapter.adaptEconomyResponse(eco.deleteBank(name));
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return VaultAdapter.adaptEconomyResponse(eco.bankBalance(name));
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.bankHas(name, amount));
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.bankWithdraw(name, amount));
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return VaultAdapter.adaptEconomyResponse(eco.bankDeposit(name, amount));
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return VaultAdapter.adaptEconomyResponse(eco.isBankOwner(name, playerName));
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return VaultAdapter.adaptEconomyResponse(eco.isBankOwner(name, player));
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return VaultAdapter.adaptEconomyResponse(eco.isBankMember(name, playerName));
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return VaultAdapter.adaptEconomyResponse(eco.isBankMember(name, player));
    }

    @Override
    public List<String> getBanks() {
        return eco.getBanks();
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return eco.createPlayerAccount(playerName);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return eco.createPlayerAccount(player);
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return eco.createPlayerAccount(playerName, worldName);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return eco.createPlayerAccount(player, worldName);
    }

}
