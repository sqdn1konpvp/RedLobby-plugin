package net.redbee.lobby.commands;

import net.redbee.lobby.Lobby;
import net.redbee.lobby.commands.constructor.AbstractCommand;
import net.redbee.lobby.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.redbee.lobby.Lobby.PREFIX;

public class SpawnCommand extends AbstractCommand {

    public SpawnCommand() {
        super("spawn");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0 && player.hasPermission("redlobby.spawn")) {

            player.teleport(LocationUtils.stringToLocation(Lobby.getMessage("WORLD.SPAWN")));
            player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.SPAWN_SUCCESS"));

            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline() && player.hasPermission("redlobby.spawn.other")) {

            player.sendMessage(Lobby.getMessage("COMMON.PLAYER_OFFLINE"));

            return;
        }

        target.teleport(LocationUtils.stringToLocation(Lobby.getMessage("WORLD.SPAWN")));
        target.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.SPAWN_SUCCESS_TARGET", player.getName()));
        player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.SPAWN_SUCCESS_PLAYER", target.getName()));
    }
}
