package net.redbee.lobby.commands;

import net.redbee.lobby.commands.constructor.AbstractCommand;
import net.redbee.lobby.menus.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainCommand extends AbstractCommand {

    public MainCommand() {
        super("menu");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        Player player = (Player) sender;

        if (player.hasPermission("redbee.menu.test")) {

            new Main().apply(player);

        }

    }
}
