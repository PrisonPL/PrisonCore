package com.collidacube.prison.prisoncore.listeners;

import com.collidacube.prison.prisoncore.Properties;
import com.collidacube.prison.prisoncore.data.DataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        boolean firstJoin = DataManager.registerPlayer(event.getPlayer());
        String firstJoinMessage = Properties.format("first-join-message", event.getPlayer());
        String joinMessage = Properties.format("join-message", event.getPlayer());

        if (firstJoin && !(firstJoinMessage == null || firstJoinMessage.equalsIgnoreCase("none")))
            event.setJoinMessage(firstJoinMessage);
        else if (!(joinMessage == null || joinMessage.equals("none")))
            event.setJoinMessage(joinMessage);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        String quitMessage = Properties.format("quit-message", event.getPlayer());
        if (!(quitMessage == null || quitMessage.equals("none")))
            event.setQuitMessage(quitMessage);
    }

}
