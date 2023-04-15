package com.collidacube.prison.prisoncore.data;

import com.collidacube.prison.prisoncore.data.economy.EconomyManager;
import com.collidacube.prison.prisoncore.data.economy.PrisonEconomy;
import com.collidacube.prison.prisoncore.data.stats.PrisonStatistic;
import com.collidacube.prison.prisoncore.data.stats.StatisticManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DataManager {

    public static final HashMap<String, OfflinePlayer> playersByUsername = new HashMap<>();

    public static OfflinePlayer getPlayer(String username) {
        return playersByUsername.get(username);
    }

    @Deprecated
    public static OfflinePlayer getPlayerOrComputeIfAbsent(String username) {
        return playersByUsername.computeIfAbsent(username, Bukkit::getOfflinePlayer);
    }

    public static boolean registerPlayer(UUID uuid) {
        return registerPlayer(Bukkit.getOfflinePlayer(uuid));
    }

    public static boolean registerPlayer(OfflinePlayer player) {
        return playersByUsername.put(player.getName(), player) == null;
    }

    public static final File DATA_FOLDER = new File("./plugins/PrisonCore");
    public static final File PLAYER_DATA_FILE = new File(DATA_FOLDER, "player_data.txt");

    public static void load() {
        try {
            List<String> lines = Files.readAllLines(PLAYER_DATA_FILE.toPath());
            if (lines.size() >= 1) loadEconomies(lines.get(0));
            if (lines.size() >= 2) loadStatistics(lines.get(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadEconomies(String economiesData) {
        for (String economyData : economiesData.split("\\|")) {
            String[] economyNameAndData = economyData.split("~", 2);
            String economyName = economyNameAndData[0];

            PrisonEconomy economy = EconomyManager.getEconomy(economyName);
            if (economy == null) {
                System.out.println("The economy '" + economyName + "' doesn't exist!");
                continue;
            }

            if (economyNameAndData[1].length() == 0) continue;

            for (String balanceData : economyNameAndData[1].split(",")) {
                String[] uuidAndBalance = balanceData.split(":");
                UUID uuid = UUID.fromString(uuidAndBalance[0]);
                double amount = Double.parseDouble(uuidAndBalance[1]);
                economy.setBalance(uuid, amount);

                registerPlayer(uuid);
            }
        }
    }

    public static void loadStatistics(String statisticsData) {
        for (String statisticData : statisticsData.split("\\|")) {
            String[] statisticNameAndData = statisticData.split("~", 2);
            String statisticName = statisticNameAndData[0];

            PrisonStatistic statistic = StatisticManager.getStatistic(statisticName);
            if (statistic == null) {
                System.out.println("The statistic '" + statisticName + "' doesn't exist!");
                continue;
            }

            if (statisticNameAndData[1].length() == 0) continue;

            for (String balanceData : statisticNameAndData[1].split(",")) {
                String[] uuidAndValue = balanceData.split(":");
                UUID uuid = UUID.fromString(uuidAndValue[0]);
                int amount = Integer.parseInt(uuidAndValue[1]);
                statistic.setValue(uuid, amount);

                registerPlayer(uuid);
            }
        }
    }

    public static void save() {
        String ecoSaveString = saveEconomies();
        String statSaveString = saveStatistics();

        try {
            Files.writeString(PLAYER_DATA_FILE.toPath(), ecoSaveString + "\n" + statSaveString);
        } catch (IOException e) {
            System.out.println("Couldn't save economies! Save strings: \n- ECONOMY: " + ecoSaveString + "\n- STATISTICS: " + statSaveString);
        }
    }

    public static String saveEconomies() {
        return EconomyManager.getEconomies()
                .stream()
                .map(eco -> eco.getName() + "~" + eco.getPlayers()
                        .stream()
                        .map(uuid -> uuid + ":" + eco.getBalance(uuid))
                        .collect(Collectors.joining(","))
                )
                .collect(Collectors.joining("|"));
    }

    public static String saveStatistics() {
        return StatisticManager.getStatistics()
                .stream()
                .map(stat -> stat.getName() + "~" + stat.getPlayers()
                        .stream()
                        .map(uuid -> uuid + ":" + stat.getValue(uuid))
                        .collect(Collectors.joining(","))
                )
                .collect(Collectors.joining("|"));
    }

}
