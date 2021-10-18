package team.unnamed.gui.menu.item;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Predicate;

public class ItemClickable {

    private final int slot;
    private final ItemStack itemStack;
    private final Predicate<InventoryClickEvent> action;

    private ItemClickable(int slot, ItemStack itemStack,
                          Predicate<InventoryClickEvent> action) {
        this.slot = slot;
        this.itemStack = itemStack;
        this.action = action;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Predicate<InventoryClickEvent> getAction() {
        return action;
    }

    public ItemClickable clone(int slot) {
        return new ItemClickable(slot, itemStack, action);
    }

    public static ItemClickable item(ItemStack itemStack) {
        return onlyItem(itemStack, event -> true);
    }

    public static ItemClickable onlyItem(ItemStack itemStack, Predicate<InventoryClickEvent> action) {
        return of(-1, itemStack, action);
    }

    public static ItemClickable of(int slot, ItemStack itemStack) {
        return of(slot, itemStack, event -> true);
    }

    public static ItemClickable of(int slot, ItemStack itemStack,
                                   Predicate<InventoryClickEvent> action) {
        return new ItemClickable(slot, itemStack, action);
    }

}
