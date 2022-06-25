package net.redbee.lobby.commands;

import net.redbee.lobby.Lobby;
import net.redbee.lobby.commands.constructor.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.redbee.lobby.Lobby.PREFIX;

public class GmcCommand extends AbstractCommand {

    public GmcCommand() {
        super("gmc");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        // Присваиваем игроку отправителя.
        Player player = (Player) sender;

        // Проверяем наличие у игрока режима, который он хочет получить.
        if (player.getGameMode() == GameMode.CREATIVE && player.hasPermission("redlobby.gmc")) {

            // Отсылаем сообщение об уже установленном режиме игры.
            player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GM_ALREADY"));

            return;
        }

        // Проверяем наличие права у игрока
        if (player.hasPermission("redlobby.gmc")) {

            // Выдаём ему игровой режим
            player.setGameMode(GameMode.CREATIVE);
            // Отсылаем сообщение об успешной выдаче
            player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMC_SUCCESS"));

            return;

        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target != null && player.hasPermission("redlobby.gmc.other")) {

            target.setGameMode(GameMode.CREATIVE);
            target.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMC_SUCCESS_TARGET", player.getName()));
            player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.GMC_SUCCESS_PLAYER", target.getName()));

        }

        player.sendMessage(PREFIX + Lobby.getMessage("COMMON.NO_PERMS"));

    }
}

