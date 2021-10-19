package team.unnamed.gui.menu;

import org.bukkit.inventory.Inventory;

import org.jetbrains.annotations.NotNull;

public interface MenuInventoryWrapper {

    @NotNull MenuInventory getMenuInventory();

    @NotNull Inventory getRawInventory();

}