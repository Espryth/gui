package team.unnamed.gui.menu;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import team.unnamed.gui.menu.item.ItemClickable;

import java.util.List;
import java.util.function.Predicate;

public interface MenuInventory {

    @NotNull String getTitle();

    int getSlots();

    @NotNull List<ItemClickable> getItems();

    @Nullable Predicate<InventoryOpenEvent> getOpenAction();

    @Nullable Predicate<InventoryCloseEvent> getCloseAction();

    boolean canIntroduceItems();

    default @Nullable ItemClickable getItem(int slot) {
        return getItems().get(slot);
    }

}
