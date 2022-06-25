package net.redbee.lobby.commands;

import net.redbee.lobby.Lobby;
import net.redbee.lobby.commands.constructor.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.redbee.lobby.Lobby.PREFIX;

public class HealCommand extends AbstractCommand {

    public HealCommand() { super("heal"); }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length != 1 && player.hasPermission("redlobby.heal")) {

            player.setHealth(20);
            player.setFoodLevel(20);
            player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.HEAL_SUCCESS"));

            return;

        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target != null && player.hasPermission("redlobby.heal.other")) {

            target.setHealth(20);
            target.setFoodLevel(20);
            target.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.HEAL_SUCCESS_TARGET", player.getName()));
            player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.HEAL_SUCCESS_PLAYER", target.getName()));

            return;

        }

        if (target == null && player.hasPermission("redlobby.heal.other")) {

            player.sendMessage(PREFIX + Lobby.getMessage("COMMON.PLAYER_OFFLINE"));

            return;

        }

        player.sendMessage(PREFIX + Lobby.getMessage("COMMON.NO_PERMS"));

    }
}
