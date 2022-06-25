package net.redbee.lobby.listeners;

import net.redbee.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PadListener implements Listener {

    @EventHandler
    public void onJumpPad(PlayerMoveEvent e) {

        Player p = e.getPlayer();
        if (p.getLocation().getWorld().getBlockAt(p.getLocation().clone().add(0, -1, 0)).getType() == Material.SLIME_BLOCK) {
            Vector v = p.getLocation().getDirection().multiply(Lobby.getInstance().getConfig().getDouble("PAD.MULTIPLIER_X")).setY(Lobby.getInstance().getConfig().getDouble("PAD.MULTIPLIER_Y"));
            p.setVelocity(v);

            p.playSound(p.getLocation(), Sound.WITHER_SHOOT, 0.25f, 1f);

        }
    }
}
