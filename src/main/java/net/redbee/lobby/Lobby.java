package net.redbee.lobby;

import lombok.SneakyThrows;
import net.redbee.lobby.commands.*;
import net.redbee.lobby.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lobby extends JavaPlugin {

    private static Lobby instance;
    
    @SneakyThrows
    private void registerListeners(Class<?>... classes) {
        for (Class<?> aClass : classes) {
            try {
                Bukkit.getPluginManager().registerEvents((Listener) aClass.newInstance(), this);
            } catch (ClassCastException exception) {
                throw new IllegalArgumentException("E6aHbko? 3To He JLuCTeHep");
            }
        }
    }

    public static String PREFIX = null;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        removeAllMobs();

        makeAlwaysDay();

        getLogger().info("Loading commands...");

        new SpawnCommand();
        new GmCommand();
        new HealCommand();
        new MainCommand();
        new LobbyCommand();
        new FlyCommand();
        new GmsCommand();
        new GmcCommand();
        new GmaCommand();
        new GmspCommand();
        new SetSpawnCommand();

        getLogger().info("Loading listeners...");
        registerListeners(new InteractListener(),
                new JoinListener(),
                new MenuListener(),
                new PadListener(),
                new SettingsListener());

        PREFIX = getConfig().getString("PREFIX");

        getLogger().info("The plugin was successfully enabled!");

    }

    public static Lobby getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getMessage (String key, Object... format) {

        return ChatColor.translateAlternateColorCodes('&', String.format(getInstance().getConfig().getString(key), format));

    }

    public static String getList (String key, Object... format) {

        return ChatColor.translateAlternateColorCodes('&', String.format(String.valueOf(getInstance().getConfig().getStringList(key)), format));

    }

    private void removeAllMobs () {

        if (!getConfig().getBoolean("WORLD.DISABLE_MOBS")) return;

        getConfig().getStringList("WORLD.ENABLED_WORLDS").stream().map(Bukkit::getWorld).forEach(world -> {
            world.getEntities().forEach(Entity::remove);
        });

    }

    private void makeAlwaysDay () {

        if (!getConfig().getBoolean("WORLD.ALWAYS_DAY")) return;

        getConfig().getStringList("WORLD.ENABLED_WORLDS").stream().map(Bukkit::getWorld).forEach(world -> {
            world.setTime(1000);
            world.setGameRuleValue("dodaylightcycle", "false");
        });

    }

}
