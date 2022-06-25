package net.redbee.lobby.listeners;

import net.redbee.lobby.Lobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static net.redbee.lobby.Lobby.PREFIX;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick (InventoryClickEvent event) {

        if (event.getInventory().getName().equals("Игровое меню") && event.getCurrentItem() != null) {

            if (event.getSlot() == 11) {

                event.getWhoClicked().sendMessage(PREFIX + Lobby.getMessage("MENUS.SLOT1"));

            } else if (event.getSlot() == 13) {

                event.getWhoClicked().sendMessage(PREFIX + Lobby.getMessage("MENUS.SLOT2"));

            } else if (event.getSlot() == 15) {

                event.getWhoClicked().sendMessage(PREFIX + Lobby.getMessage("MENUS.SLOT3"));

            }

            event.setCancelled(true);

        }

    }

}
