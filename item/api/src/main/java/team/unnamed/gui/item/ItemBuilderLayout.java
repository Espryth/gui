package team.unnamed.gui.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static team.unnamed.bukkit.ServerVersionUtils.SERVER_VERSION_INT;
import static team.unnamed.validate.Validate.*;

abstract class ItemBuilderLayout<T extends ItemBuilder> 
        implements ItemBuilder {

    protected final Material material;
    private final int amount;
    private final byte data;

    private String name;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments;
    private List<ItemFlag> flags;
    private boolean unbreakable;

    protected ItemBuilderLayout(Material material, int amount, byte data) {
        this.material = material;
        this.amount = amount;
        this.data = data;
        this.lore = new ArrayList<>();
        this.enchantments = new HashMap<>();
        this.flags = new ArrayList<>();
    }

    @Override
    public ItemBuilder setName(String name) {
        this.name = isNotNull(name, "Item name cannot be null.");
        return back();
    }

    @Override
    public ItemBuilder setLore(List<String> lore) {
        this.lore = isNotNull(lore, "Item lore cannot be null.");
        return back();
    }

    @Override
    public ItemBuilder setLore(String... lines) {
        this.lore = Arrays.asList(isNotNull(lines, "Item lore cannot be null."));
        return back();
    }

    @Override
    public ItemBuilder setEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = isNotNull(enchantments, "Item enchantments cannot be null.");
        return back();
    }

    @Override
    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        this.enchantments.put(isNotNull(enchantment, "Item enchantment cannot be null."), level);
        return back();
    }

    @Override
    public ItemBuilder setFlags(List<ItemFlag> flags) {
        this.flags = isNotNull(flags, "Item flags cannot be null.");
        return back();
    }

    @Override
    public ItemBuilder addFlag(ItemFlag... flags) {
        this.flags.addAll(Arrays.asList(flags));
        return back();
    }

    @Override
    public ItemBuilder grow() {
        this.enchantments.put(Enchantment.DURABILITY, 3);
        this.flags.add(ItemFlag.HIDE_ENCHANTS);
        return back();
    }

    @Override
    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return back();
    }

    @Override
    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, amount, data);
        ItemMeta meta = itemStack.getItemMeta();

        enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));

        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.spigot().setUnbreakable(unbreakable);

        if (SERVER_VERSION_INT != 7) {
            List<org.bukkit.inventory.ItemFlag> itemFlags = new ArrayList<>();

            if (SERVER_VERSION_INT <= 13) {
                flags.remove(ItemFlag.HIDE_DYE);
            }

            for (ItemFlag itemFlag : flags) {
                itemFlags.add(org.bukkit.inventory.ItemFlag.valueOf(itemFlag.name()));
            }

            itemFlags.forEach(meta::addItemFlags);
        }

        itemStack.setItemMeta(meta);

        return itemStack;
    }
    
    protected abstract T back();
    
}
