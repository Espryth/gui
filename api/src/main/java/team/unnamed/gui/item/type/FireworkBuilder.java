package team.unnamed.gui.item.type;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static team.unnamed.validate.Validate.notNull;
import static team.unnamed.validate.Validate.state;

public class FireworkBuilder extends AbstractItemBuilder<FireworkBuilder> {

    private List<FireworkEffect> fireworkEffects = new ArrayList<>();

    private int power = 3;

    protected FireworkBuilder(Material material, int amount) {
        super(material, amount, (short) 0);
    }

    public FireworkBuilder setFireworkEffects(List<FireworkEffect> fireworkEffects) {
        this.fireworkEffects = notNull(fireworkEffects, "Firework effects can't be null.");

        return this;
    }

    public FireworkBuilder setFireworkEffects(FireworkEffect... fireworkEffects) {
        return setFireworkEffects(Arrays.asList(fireworkEffects));
    }

    public FireworkBuilder addFireworkEffect(FireworkEffect fireworkEffect) {
        notNull(fireworkEffect, "Firework effect can't be null.");

        fireworkEffects.add(fireworkEffect);

        return this;
    }

    public FireworkBuilder setPower(int power) {
        state(power > 0, "Power must be higher than 0.");

        this.power = power;

        return this;
    }

    @Override
    public ItemStack build() {
        state(material.name().startsWith("FIREWORK"), "Material must be firework!");

        ItemStack itemStack = super.build();

        FireworkMeta fireworkMeta = (FireworkMeta) itemStack.getItemMeta();

        fireworkEffects.forEach(fireworkMeta::addEffect);
        fireworkMeta.setPower(power);

        itemStack.setItemMeta(fireworkMeta);

        return itemStack;
    }

    @Override
    protected FireworkBuilder self() {
        return this;
    }

}