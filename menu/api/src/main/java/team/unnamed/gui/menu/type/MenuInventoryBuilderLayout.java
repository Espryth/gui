package team.unnamed.gui.menu.type;

import org.bukkit.inventory.Inventory;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.util.MenuUtils;

import java.util.Arrays;
import java.util.function.Predicate;

import static team.unnamed.validate.Validate.isNotNull;
import static team.unnamed.validate.Validate.isState;

abstract class MenuInventoryBuilderLayout<T extends MenuInventoryBuilder>
        implements MenuInventoryBuilder {

    protected final String title;
    protected final int slots;
    private final int rows;
    
    protected ItemClickable[] items;
    protected Predicate<Inventory> openAction;
    protected Predicate<Inventory> closeAction;
    protected boolean canIntroduceItems;

    protected MenuInventoryBuilderLayout(String title) {
        this(title, 6);
    }

    protected MenuInventoryBuilderLayout(String title, int rows) {
        isState(rows > 0 && rows <= 6,
                "Rows must be major than 0 and minor than 6");
        this.title = isNotNull(title, "Title cannot be null.");
        this.slots = rows * 9;
        this.rows = rows;
        this.items = new ItemClickable[this.slots];
    }

    @Override
    public MenuInventoryBuilder fillItem(ItemClickable item, int from, int to) {
        isNotNull(item, "Item cannot be null.");

        for (int i = from; i < to; i++) {
            this.items[i] = item.clone(slots);
        }

        return back();
    }

    @Override
    public MenuInventoryBuilder fillRow(ItemClickable item, int row) {
        isState(row > 0 && row <= 6,
                "Row must be major than 0 and minor than 6");
        isNotNull(item, "Item cannot be null.");

        int indexStart = row == 1 ? 0 : (row - 1) * 9;

        for (int slot = indexStart; slot < indexStart + 9; slot++) {
            items[slot] = item.clone(slot);
        }

        return back();
    }

    @Override
    public MenuInventoryBuilder fillColumn(ItemClickable item, int column) {
        isState(column > 0 && column <= 9,
                "Column must be major than 0 and minor than 9");
        isNotNull(item, "Item cannot be null.");

        int indexStart = column - 1;
        int indexEnd = (slots - 9) + column;

        for (int slot = indexStart; slot <= indexEnd; slot += 9) {
            items[slot] = item.clone(slot);
        }

        return back();
    }

    @Override
    public MenuInventoryBuilder fillBorders(ItemClickable item) {
        isNotNull(item, "Item cannot be null.");
        isState(rows >= 3, "Cannot fill borders if rows are minor than 3.");

        fillRow(item, 1);
        fillRow(item, rows);
        fillColumn(item, 1);
        fillColumn(item, 9);

        return back();
    }

    @Override
    public MenuInventoryBuilder addItem(ItemClickable item, int... slots) {
        isNotNull(item, "Item cannot be null.");

        for (int slot : slots) {
            items[slot] = item.clone(slot);
        }

        return back();
    }

    @Override
    public MenuInventoryBuilder addItem(ItemClickable item) {
        isNotNull(item, "Item cannot be null.");

        items[item.getSlot()] = item;

        return back();
    }

    @Override
    public MenuInventoryBuilder setOpenAction(Predicate<Inventory> action) {
        isNotNull(action, "Open action cannot be null.");
        this.openAction = action;

        return back();
    }

    @Override
    public MenuInventoryBuilder setCloseAction(Predicate<Inventory> action) {
        isNotNull(action, "Close action cannot be null.");
        this.closeAction = action;

        return back();
    }

    @Override
    public MenuInventoryBuilder setIntroduceItems(boolean canIntroduceItems) {
        this.canIntroduceItems = canIntroduceItems;
        return back();
    }

    @Override
    public Inventory build() {
        return internalBuild(new DefaultMenuInventory(
                title, slots, Arrays.asList(items),
                openAction, closeAction, canIntroduceItems
        ));
    }

    protected Inventory internalBuild(MenuInventory menuInventory) {
        Inventory inventory = MenuUtils.parseToInventory(menuInventory);

        for (ItemClickable itemClickable : items) {
            if (itemClickable == null) {
                continue;
            }

            inventory.setItem(itemClickable.getSlot(), itemClickable.getItemStack());
        }

        return inventory;
    }
    
    protected abstract T back();
    
}