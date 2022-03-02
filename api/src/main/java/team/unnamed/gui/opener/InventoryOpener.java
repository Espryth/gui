package team.unnamed.gui.opener;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Objects;

import static team.unnamed.gui.version.ServerVersionProvider.SERVER_VERSION_INT;

public final class InventoryOpener {

    private static final @Nullable Method PLAYER_GET_HANDLE_METHOD;
    private static final @Nullable Method LEGACY_OPEN_INVENTORY_METHOD;

    static {
        if (SERVER_VERSION_INT == 7) {
            // initialize v1.7 support
            try {
                Class<?> craftHumanEntityClass = Class.forName("org.bukkit.craftbukkit.v1_7_R4.entity.CraftHumanEntity");
                Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer");
                Class<?> entityPlayerClass = Class.forName("net.minecraft.server.v1_7_R4.EntityPlayer");

                PLAYER_GET_HANDLE_METHOD = craftPlayerClass.getDeclaredMethod("getHandle");
                LEGACY_OPEN_INVENTORY_METHOD = craftHumanEntityClass.getDeclaredMethod("openCustomInventory", Inventory.class, entityPlayerClass, int.class);

                PLAYER_GET_HANDLE_METHOD.setAccessible(true);
                LEGACY_OPEN_INVENTORY_METHOD.setAccessible(true);
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                throw new ExceptionInInitializerError(e);
            }
        } else {
            PLAYER_GET_HANDLE_METHOD = null;
            LEGACY_OPEN_INVENTORY_METHOD = null;
        }
    }

    public static void open(Player player, Inventory inventory) {
        if (LEGACY_OPEN_INVENTORY_METHOD == null) {
            // legacy method was not initialized
            player.openInventory(inventory);
        } else {
            // use legacy (v1.7)
            Objects.requireNonNull(PLAYER_GET_HANDLE_METHOD, "getHandle");
            try {
                Object handle = PLAYER_GET_HANDLE_METHOD.invoke(player);
                LEGACY_OPEN_INVENTORY_METHOD.invoke(player,inventory, handle, 0);
            } catch (ReflectiveOperationException e) {
                throw new IllegalArgumentException("An error has occurred while opening inventory " + inventory.getTitle(), e);
            }
        }
    }

}
