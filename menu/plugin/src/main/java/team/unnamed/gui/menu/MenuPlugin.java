package team.unnamed.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.listener.InventoryClickListener;
import team.unnamed.gui.menu.listener.InventoryCloseListener;
import team.unnamed.gui.menu.listener.InventoryOpenListener;

public class MenuPlugin extends JavaPlugin {

    private int counter;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryOpenListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(this), this);
        getCommand("gui").setExecutor((sender, command, label, args) -> {
            Player player = (Player) sender;

            player.openInventory(MenuInventory.builder("Test")
                    .fillBorders(ItemClickable.onlyItem(new ItemStack(Material.STAINED_GLASS_PANE)))
                    .addItem(ItemClickable.builder(22)
                            .setItem(new ItemStack(Material.ENDER_PEARL))
                            .setAction(inventory -> {
                                player.sendMessage("Testing");
                                player.closeInventory();
                                return true;
                            })
                            .build())
                    .setOpenAction(inventory -> {
                        player.sendMessage("Opening...");
                        return false;
                    })
                    .setCloseAction(inventory -> {
                        player.sendMessage("Trying to close...");

                        return counter++ < 3;
                    })
                    .build());

            return true;
        });
    }

    public void onDisable() {

    }

}