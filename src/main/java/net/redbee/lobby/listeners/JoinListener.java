package net.redbee.lobby.listeners;

import net.redbee.lobby.Lobby;
import net.redbee.lobby.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {

        Player player = event.getPlayer();
        event.setJoinMessage(null);

        player.teleport(LocationUtils.stringToLocation(Lobby.getMessage("WORLD.SPAWN")));

        for (String s : Lobby.getInstance().getConfig().getStringList("JOIN_MESSAGES.MOTD")) {

            player.sendMessage(s);

        }

        Bukkit.broadcastMessage(Lobby.getMessage("JOIN_MESSAGES.DEFAULT", player.getName()));

    }

    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent event) {

        Player player = event.getPlayer();
        event.setQuitMessage(null);

    }
}
