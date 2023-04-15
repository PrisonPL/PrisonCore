package com.collidacube.prison.prisoncore.papi;

import com.collidacube.prison.prisoncore.PrisonCore;
import com.collidacube.prison.prisoncore.data.economy.EconomyManager;
import com.collidacube.prison.prisoncore.data.economy.PrisonEconomy;
import com.collidacube.prison.prisoncore.data.stats.PrisonStatistic;
import com.collidacube.prison.prisoncore.data.stats.StatisticManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrisonCorePapiExtension extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "prisoncore";
    }

    @Override
    public @NotNull String getAuthor() {
        return "PrisonPL Developers";
    }

    @Override
    public @NotNull String getVersion() {
        return PrisonCore.getInstance().getDescription().getVersion();
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        for (PrisonEconomy economy : EconomyManager.getEconomies()) {
            if (params.equalsIgnoreCase("eco_" + economy.getName()))
                return economy.format(economy.getBalance(player));
        }

        for (PrisonStatistic statistic : StatisticManager.getStatistics()) {
            if (params.equalsIgnoreCase("stat_" + statistic.getName()))
                return statistic.format(statistic.getValue(player));
        }

        return null;
    }

}
