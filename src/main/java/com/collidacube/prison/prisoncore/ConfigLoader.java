package com.collidacube.prison.prisoncore;

import com.collidacube.prison.prisoncore.data.economy.EconomyManager;
import com.collidacube.prison.prisoncore.data.economy.EconomySettings;
import com.collidacube.prison.prisoncore.data.stats.StatisticManager;
import com.collidacube.prison.prisoncore.data.stats.StatisticSettings;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class ConfigLoader {

    public static void loadEco(ConfigurationSection config) {
        Set<String> ecoLabels = config.getKeys(false);
        for (String ecoLabel : ecoLabels) {
            ConfigurationSection settingsConfig = config.getConfigurationSection(ecoLabel);
            EconomySettings settings = buildEcoSettings(ecoLabel, settingsConfig);
            EconomyManager.registerEconomy(settings);
        }
    }

    public static EconomySettings buildEcoSettings(String label, ConfigurationSection config) {
        EconomySettings.Builder settings = new EconomySettings.Builder();
        settings.setEconomyName(label);

        if (config != null) {
            Utils.ifNotNull(config.getString("currency-singular"), settings::setCurrencySingular);
            Utils.ifNotNull(config.getString("currency-plural"), settings::setCurrencyPlural);
            Utils.ifNotNull(config.getString("display-format"), settings::setFormat);
            Utils.ifNotNull(config.getDouble("default-amount"), settings::setInitialAmount);
        }

        return settings.build();
    }

    public static void loadStats(ConfigurationSection config) {
        Set<String> statsLabels = config.getKeys(false);
        for (String statLabel : statsLabels) {
            ConfigurationSection settingsConfig = config.getConfigurationSection(statLabel);
            StatisticSettings settings = buildStatsSettings(statLabel, settingsConfig);
            StatisticManager.registerStatistic(settings);
        }
    }

    public static StatisticSettings buildStatsSettings(String label, ConfigurationSection config) {
        StatisticSettings.Builder settings = new StatisticSettings.Builder();
        settings.setStatisticName(label);

        if (config != null) {
            Utils.ifNotNull(config.getString("value-singular"), settings::setValueSingular);
            Utils.ifNotNull(config.getString("value-plural"), settings::setValuePlural);
            Utils.ifNotNull(config.getString("display-format"), settings::setFormat);
            Utils.ifNotNull(config.getInt("default-amount"), settings::setInitialAmount);
        }

        return settings.build();
    }

}
