package team.unnamed.gui.menu;

import org.bukkit.inventory.Inventory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import team.unnamed.gui.menu.item.ItemClickable;

import java.util.List;
import java.util.function.Predicate;

public interface MenuInventory {

    @NotNull String getTitle();

    int getSlots();

    @NotNull List<ItemClickable> getItems();

    @Nullable Predicate<Inventory> getOpenAction();

    @Nullable Predicate<Inventory> getCloseAction();

    boolean canIntroduceItems();

    default @Nullable ItemClickable getItem(int slot) {
        return getItems().get(slot);
    }

    static MenuInventoryBuilder builder(String title) {
        return new DefaultMenuInventoryBuilder(title);
    }

    static MenuInventoryBuilder builder(String title, int rows) {
        return new DefaultMenuInventoryBuilder(title, rows);
    }

}
