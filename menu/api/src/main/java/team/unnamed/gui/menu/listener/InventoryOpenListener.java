package team.unnamed.gui.menu.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.menu.MenuInventory;
import team.unnamed.gui.menu.MenuInventoryWrapper;
import team.unnamed.gui.menu.util.MenuUtils;

import java.util.function.Predicate;

public class InventoryOpenListener
        implements Listener {

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();

        if (MenuUtils.isCustomMenu(inventory)) {
            MenuInventoryWrapper wrapper = MenuUtils.getAsWrapper(inventory);
            MenuInventory menuInventory = wrapper.getMenuInventory();
            Predicate<Inventory> action = menuInventory.getOpenAction();

            if (action == null) {
                return;
            }

            event.setCancelled(action.test(inventory));
        }
    }

}