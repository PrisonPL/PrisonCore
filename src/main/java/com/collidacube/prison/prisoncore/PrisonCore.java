package com.collidacube.prison.prisoncore;

import com.collidacube.prison.prisoncore.commands.EconomyCommand;
import com.collidacube.prison.prisoncore.commands.StatisticCommand;
import com.collidacube.prison.prisoncore.data.DataManager;
import com.collidacube.prison.prisoncore.data.economy.EconomyManager;
import com.collidacube.prison.prisoncore.data.economy.EconomySettings;
import com.collidacube.prison.prisoncore.data.economy.vault.VaultEconomyHook;
import com.collidacube.prison.prisoncore.listeners.JoinQuitEvent;
import com.collidacube.prison.prisoncore.papi.PrisonCorePapiExtension;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class PrisonCore extends JavaPlugin {

    private static PrisonCore instance = null;

    public static PrisonCore getInstance() {
        return instance;
    }
    public static boolean isPluginEnabled(String pluginName) {
        return Bukkit.getServer().getPluginManager().isPluginEnabled(pluginName);
    }
    public static boolean isVaultEnabled() {
        return isPluginEnabled("Vault");
    }
    public static boolean isPapiEnabled() {
        return isPluginEnabled("PlaceholderAPI");
    }

    @Override
    public void onEnable() {
        instance = this;

        if (isVaultEnabled()) loadVault();
        if (isPapiEnabled()) loadPAPI();

        Properties.load();

        initEconomies();
        initStatistics();

        initEvents();
        initCommands();

        DataManager.load();
    }

    public void loadVault() {
        System.out.println("Registering Vault");
        VaultEconomyHook cashEconomy = new VaultEconomyHook(new EconomySettings.Builder()
                .setEconomyName("cash")
                .setCurrencySingular("dollar")
                .setCurrencyPlural("dollars")
                .setFormat("$%.2f")
                .build());

        EconomyManager.registerEconomy(cashEconomy.getPrisonEconomy());

        getServer().getServicesManager().register(Economy.class, cashEconomy, this, ServicePriority.Highest);
    }

    public void initEconomies() {
        EconomyManager.registerEconomy(new EconomySettings.Builder()
                .setEconomyName("cash")
                .setCurrencySingular("dollar")
                .setCurrencyPlural("dollars")
                .setFormat("$%.2f")
                .build());
    }

    public void initStatistics() {

    }

    public void initEvents() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinQuitEvent(), this);
    }

    public void initCommands() {
        Utils.registerCommand("statistics", this, new StatisticCommand());
        Utils.registerCommand("economies", this, new EconomyCommand());
    }

    public void loadPAPI() {
        System.out.println("Registering papi");
        new PrisonCorePapiExtension().register();
    }

    @Override
    public void onDisable() {
        DataManager.save();
    }

}
