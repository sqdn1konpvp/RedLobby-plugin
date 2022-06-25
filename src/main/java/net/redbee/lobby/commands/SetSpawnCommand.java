package net.redbee.lobby.commands;

import net.redbee.lobby.Lobby;
import net.redbee.lobby.commands.constructor.AbstractCommand;
import net.redbee.lobby.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.redbee.lobby.Lobby.PREFIX;

public class SetSpawnCommand extends AbstractCommand {

    public SetSpawnCommand() {
        super("setspawn");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        Player player = (Player) sender;
        onSetSpawn(player);

    }

    public void onSetSpawn (Player player) {

        if (!player.hasPermission("redlobby.setspawn")) {
            player.sendMessage(Lobby.getMessage("COMMON.NO_PERMS"));
            return;
        }

        Lobby.getInstance().getConfig().set("WORLD.SPAWN", LocationUtils.locationToString(player.getLocation()));
        Lobby.getInstance().saveConfig();
        player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.SETSPAWN_SUCCESS"));

    }

}
