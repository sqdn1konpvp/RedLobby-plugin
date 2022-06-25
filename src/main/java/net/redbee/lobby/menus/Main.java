package net.redbee.lobby.menus;

import net.redbee.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;

public class Main {

    public void apply(Player player) {

        Inventory menuInventory = Bukkit.createInventory (player, 54, "Игровое меню");

        ItemStack stick = new ItemStack(Material.WORKBENCH);
        ItemMeta stickmeta = stick.getItemMeta();
        stickmeta.setDisplayName(Lobby.getMessage("MENUS.SLOT1_NAME"));
        ArrayList<String> sticklore = new ArrayList<>();
        stickmeta.setLore(Lobby.getInstance().getConfig().getStringList("MENUS.SLOT1_LORE"));
        stick.setItemMeta(stickmeta);
        menuInventory.setItem(13, stick);

        player.openInventory(menuInventory);

    }

}
