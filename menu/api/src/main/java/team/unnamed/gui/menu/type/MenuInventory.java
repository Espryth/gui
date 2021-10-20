package team.unnamed.gui.menu.type;

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

    void setItem(ItemClickable item);

    void removeItem(int slot);

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

    static AnimatedMenuInventoryBuilder animatedBuilder(String title) {
        return new AnimatedMenuInventoryBuilder(title);
    }

    static AnimatedMenuInventoryBuilder animatedBuilder(String title, int rows) {
        return new AnimatedMenuInventoryBuilder(title, rows);
    }

}
