package com.collidacube.prison.prisoncore.data.economy;

import com.collidacube.prison.prisoncore.data.PrisonEconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class EconomyManager {

    private static final HashMap<String, PrisonEconomy> economies = new HashMap<>();
    public static boolean registerEconomy(PrisonEconomy economy) {
        if (economy.getName() == null) return false;
        return economies.putIfAbsent(economy.getName().toLowerCase(), economy) == null;
    }

    public static boolean registerEconomy(EconomySettings settings) {
        return registerEconomy(new PrisonEconomy(settings));
    }

    public static PrisonEconomy getEconomy(String name) {
        return economies.get(name.toLowerCase());
    }

    protected final OfflinePlayer player;
    public EconomyManager(OfflinePlayer player) {
        this.player = player;
    }

    public <T> T balance(String economyName, Function<PrisonEconomy, T> editor) {
        PrisonEconomy economy = getEconomy(economyName);
        if (economy == null) throw new NoSuchElementException();
        return editor.apply(economy);
    }

    public double getBalance(String economyName) {
        return balance(economyName, eco -> eco.getBalance(player));
    }

    public PrisonEconomyResponse setBalance(String economyName, double amount) {
        double balance = getBalance(economyName);
        if (balance < amount) return deposit(economyName, amount - balance);
        else return withdraw(economyName, balance - amount);
    }

    public PrisonEconomyResponse deposit(String economyName, double amount) {
        return balance(economyName, eco -> eco.depositPlayer(player, amount));
    }

    public PrisonEconomyResponse withdraw(String economyName, double amount) {
        return balance(economyName, eco -> eco.withdrawPlayer(player, amount));
    }

    public boolean has(String economyName, double amount) {
        return balance(economyName, eco -> eco.has(player, amount));
    }

    public static Collection<PrisonEconomy> getEconomies() {
        return economies.values();
    }

}
