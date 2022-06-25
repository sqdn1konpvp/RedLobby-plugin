package net.redbee.lobby.listeners;

import net.redbee.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class SettingsListener implements Listener {

    private boolean isEnabledWorld (World world) {

        return Lobby.getInstance().getConfig().getStringList("WORLD.ENABLED_WORLDS").stream().map(Bukkit::getWorld).anyMatch(world1 -> world1.equals(world));

    }


    private boolean checkSetting (String path) {

        return Lobby.getInstance().getConfig().getBoolean(path);

    }

    @EventHandler
    public void onBlockPlace (BlockPlaceEvent event) {

        if (!checkSetting("DISABLE_BLOCK_PLACE")) return;

        Player player = event.getPlayer();

        event.setCancelled(!player.hasPermission("redlobby.world.place"));
    }

    @EventHandler
    public void onBlockBreak (BlockBreakEvent event) {

        if (!checkSetting("DISABLE_BLOCK_BREAK")) return;

        Player player = event.getPlayer();

        event.setCancelled(!player.hasPermission("redlobby.world.break"));
    }

    @EventHandler
    public void onPlayerDamage (EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        if (!checkSetting("WORLD.DISABLE_DAMAGE")) return;

        if (!isEnabledWorld(event.getEntity().getWorld())) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerHunger (FoodLevelChangeEvent event) {

        if (!(event.getEntity() instanceof Player)) return;

        if (!checkSetting("WORLD.DISABLE_FOOD_CHANGE")) return;

        if (!isEnabledWorld(event.getEntity().getWorld())) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onMobSpawn (EntitySpawnEvent event) {

        if (!checkSetting("WORLD.DISABLE_MOBS")) return;

        if (!isEnabledWorld(event.getEntity().getWorld())) return;

        event.setCancelled(true);
    }

}
