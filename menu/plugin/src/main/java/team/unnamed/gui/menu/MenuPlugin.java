package team.unnamed.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import team.unnamed.gui.menu.animated.AnimatedMenuRegistry;
import team.unnamed.gui.menu.listener.InventoryClickListener;
import team.unnamed.gui.menu.listener.InventoryCloseListener;
import team.unnamed.gui.menu.listener.InventoryOpenListener;
import team.unnamed.gui.menu.menu.AnimatedMenuInventoryCreator;
import team.unnamed.gui.menu.menu.DefaultMenuInventoryCreator;

public class MenuPlugin extends JavaPlugin {

    private final MenuInventoryCreator defaultInventoryCreator;
    private final MenuInventoryCreator animatedInventoryCreator;

    public MenuPlugin() {
        this.defaultInventoryCreator = new DefaultMenuInventoryCreator();
        this.animatedInventoryCreator = new AnimatedMenuInventoryCreator();
    }

    public void onEnable() {
        AnimatedMenuRegistry animatedMenuRegistry = new AnimatedMenuRegistry();
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryOpenListener(animatedMenuRegistry), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(this, animatedMenuRegistry), this);
        animatedMenuRegistry.initializeTask(this);
        getCommand("gui").setExecutor((sender, command, label, args) -> {
            Player player = (Player) sender;

            switch (args[0]) {
                case "default": {
                    player.openInventory(defaultInventoryCreator.create(player));
                    break;
                }
                case "animated": {
                    player.openInventory(animatedInventoryCreator.create(player));
                    break;
                }
            }
            return true;
        });
    }

    public void onDisable() {

    }

}