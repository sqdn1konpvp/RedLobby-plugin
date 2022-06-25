package net.redbee.lobby.commands;

import com.google.common.collect.Lists;
import net.redbee.lobby.Lobby;
import net.redbee.lobby.commands.constructor.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static net.redbee.lobby.Lobby.PREFIX;

public class LobbyCommand extends AbstractCommand {

    public LobbyCommand() { super("redlobby"); }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {

        Player player = (Player) sender;

        // Если аргументов в команде нету, то выводим сообщение с помощью
        if (args.length == 0 && player.hasPermission("redlobby.lobby")) {

            for (String s : Lobby.getInstance().getConfig().getStringList("COMMANDS.REDLOBBY_HELP")) {

                player.sendMessage(s);

            }
            return;
        }

        // Если есть аргумент reload, то делаем действия
        if (args[0].equalsIgnoreCase("reload") && player.hasPermission("redlobby.core.reload")) {

            // Перезагрузка конфига
            Lobby.getInstance().reloadConfig();
            PREFIX = Lobby.getInstance().getConfig().getString("PREFIX");

            // Вывод сообщения игроку из конфига
            player.sendMessage(PREFIX + Lobby.getMessage("COMMANDS.REDLOBBY_RELOADED"));
            return;

        } else {

            player.sendMessage(PREFIX + Lobby.getMessage("COMMON.NO_PERMS"));

        }


    }

    private List<String> filter(List<String> list, String[] args) {

        if (args.length == 1) return Lists.newArrayList("reload");
        return Lists.newArrayList();

    }
}
