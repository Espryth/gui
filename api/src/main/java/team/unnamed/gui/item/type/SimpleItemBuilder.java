package team.unnamed.gui.item.type;

import org.bukkit.Material;

public class SimpleItemBuilder extends AbstractItemBuilder<SimpleItemBuilder> {

    protected SimpleItemBuilder(Material material, int amount, short data) {
        super(material, amount, data);
    }

    @Override
    protected SimpleItemBuilder self() {
        return this;
    }
}