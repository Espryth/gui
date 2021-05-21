package team.unnamed.gui.core.gui.factory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import team.unnamed.gui.abstraction.menu.GUIData;
import team.unnamed.gui.abstraction.menu.GUIInventory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class GUIFactory {

  public static final String SERVER_VERSION =
      Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);

  private static final Constructor<?> GUI_CONSTRUCTOR;

  static {

    try {
      GUI_CONSTRUCTOR = Class.forName(
          "team.unnamed.gui.v" + SERVER_VERSION + ".CraftGUIInventory" + SERVER_VERSION
      )
          .getConstructor(InventoryHolder.class, GUIData.class);
    } catch (NoSuchMethodException | ClassNotFoundException e) {
      throw new IllegalArgumentException("Your server version isn't supported to unnamed gui!");
    }

  }

  public static Inventory create(GUIData guiData) {
    try {
      GUIInventory guiInventory = (GUIInventory) GUI_CONSTRUCTOR
          .newInstance(null, guiData);

      return guiInventory.getMenuInventory();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(
          "An error has occurred while creating the menu " + guiData.getTitle(), e
      );
    }
  }

}