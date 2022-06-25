package net.redbee.lobby.commands.constructor;

import net.redbee.lobby.Lobby;
import org.bukkit.command.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {

    public AbstractCommand(String command) {

        PluginCommand pluginCommand = Lobby.getInstance().getCommand(command);
        if (pluginCommand != null) {

            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(this);

        }

    }

    public abstract void execute (CommandSender sender, String label, String[] args);

    public List<String> complete(CommandSender sender, String[] args) {

        return null;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        execute(sender, label, args);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        return filter(complete(sender, args), args);

    }

    private List<String> filter(List<String> list, String[] args) {

        if (list == null) return null;
        String last = args[args.length - 1];
        List<String> result = new ArrayList<>();
        for (String arg : list) {
            if (arg.toLowerCase().startsWith(last.toLowerCase())) result.add(arg);
        }

        return result;

    }

}
