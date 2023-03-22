package dev.teraprath.corelib.item;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ItemRandomizer {

    private final Map<Integer, ArrayList<ItemStack>> items;

    public ItemRandomizer() {
        this.items = new HashMap<>();
    }

    public void addItem(@Nonnull ItemStack itemStack) {
        addItem(1, itemStack);
    }

    public void addItem(@Nonnegative int tier, @Nonnull ItemStack itemStack) {
        items.computeIfAbsent(tier, k -> new ArrayList<>());
        ArrayList<ItemStack> items = this.items.get(tier);
        items.add(itemStack);
        this.items.put(tier, items);
    }

    public ItemStack getRandomItem() {

        // 10% chance -> Tier 3
        if (percentChance(0.10)) {
            return getRandomItem(3);
        }

        // 25% chance -> Tier 2
        if (percentChance(0.25)) {
            return getRandomItem(2);
        }

        // 65% chance -> Tier 1
        return getRandomItem(1);

    }

    public ItemStack getRandomItem(@Nonnegative int tier) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(this.items.size());
        return this.items.get(tier).get(randomIndex);
    }

    private boolean percentChance(double chance) {
        return Math.random() <= chance;
    }

}
