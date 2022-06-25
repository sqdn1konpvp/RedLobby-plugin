package net.redbee.lobby.commands;

import net.redbee.lobby.Lobby;
import net.redbee.lobby.commands.constructor.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.persistence.Lob;

import static net.redbee.lobby.Lobby.PREFIX;

public class FlyCommand extends AbstractCommand {

    public FlyCommand() {
        super("fly");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        Player player = (Player) sender;

        // Проверяем наличие прав у игрока
        if (args.length != 1 && player.hasPermission("redlobby.fly")) {

            player.setAllowFlight(!player.getAllowFlight());
            player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.FLY_SUCCESS") + (player.getAllowFlight() ? "§aВкл" : "§cВыкл"));

            return;

        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target != null && player.hasPermission("redlobby.fly.other")) {

            target.setAllowFlight(!target.getAllowFlight());
            target.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.FLY_SUCCESS_TARGET", player.getName()) + (player.getAllowFlight() ? "§aВкл" : "§cВыкл"));
            player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.FLY_SUCCESS_PLAYER", target.getName()) + (player.getAllowFlight() ? "§aВкл" : "§cВыкл"));

            return;

        }

        if (target == null && player.hasPermission("redlobby.fly.other")) {

            player.sendMessage(PREFIX + Lobby.getMessage("COMMON.PLAYER_OFFLINE"));

            return;

        }

        player.sendMessage(PREFIX + Lobby.getMessage("COMMON.NO_PERMS"));

    }
}
