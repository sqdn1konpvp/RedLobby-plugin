package net.redbee.lobby.commands;

import net.redbee.lobby.Lobby;
import net.redbee.lobby.commands.constructor.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.redbee.lobby.Lobby.PREFIX;

public class GmCommand extends AbstractCommand {

    public GmCommand() { super("gm"); }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        Player player = (Player) sender;

        // Проверить на права игрока
        if (args.length == 0 && player.hasPermission("redbee.gm")) {

            player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GM_HELP"));

            return;
        }

        if (args.length == 1) {
            if ((args[0].equalsIgnoreCase("0")) && player.hasPermission("redbee.gms")) {

                player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMS_SUCCESS"));
                player.setGameMode(GameMode.SURVIVAL);

                return;

            }

            // Установить режим креатив
            if ((args[0].equalsIgnoreCase("1")) && player.hasPermission("redbee.gmc")) {

                player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMC_SUCCESS"));
                player.setGameMode(GameMode.CREATIVE);

                return;

            }

            // Установить режим приключение
            if ((args[0].equalsIgnoreCase("2")) && player.hasPermission("redbee.gma")) {

                player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMA_SUCCESS"));
                player.setGameMode(GameMode.ADVENTURE);

                return;
            }

            // Установить режим наблюдатель
            if ((args[0].equalsIgnoreCase("3")) && player.hasPermission("redbee.gmsp")) {

                player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMSP_SUCCESS"));
                player.setGameMode(GameMode.SPECTATOR);

                return;
            }
        }

        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[1]);

            if (target == null || !target.isOnline()) {
                // игрок оффлайн
                return;
            }

            if ((args[0].equalsIgnoreCase("0")) && player.hasPermission("redbee.gmsc.other")) {

                player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMS_SUCCESS_PLAYER", target.getName()));
                target.setGameMode(GameMode.SURVIVAL);
                target.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMS_SUCCESS_TARGET", player.getName()));

                return;

            }

            // Установить режим креатив
            if ((args[0].equalsIgnoreCase("1")) && player.hasPermission("redbee.gmc.other")) {

                player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMC_SUCCESS_PLAYER", target.getName()));
                target.setGameMode(GameMode.CREATIVE);
                target.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMC_SUCCESS_TARGET", player.getName()));

                return;

            }

            // Установить режим приключение
            if ((args[0].equalsIgnoreCase("2")) && player.hasPermission("redbee.gma.other")) {

                player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMA_SUCCESS_PLAYER", target.getName()));
                target.setGameMode(GameMode.ADVENTURE);
                target.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMA_SUCCESS_TARGET", player.getName()));

                return;
            }

            // Установить режим наблюдатель
            if ((args[0].equalsIgnoreCase("3")) && player.hasPermission("redbee.gmsp.other")) {

                player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMSP_SUCCESS_PLAYER", target.getName()));
                target.setGameMode(GameMode.SPECTATOR);
                target.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMSP_SUCCESS_TARGET", player.getName()));

                return;
            }
        }

    }
}
