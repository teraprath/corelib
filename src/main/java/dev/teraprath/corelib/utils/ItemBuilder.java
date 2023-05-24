package dev.teraprath.corelib.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Arrays;

public class ItemBuilder implements Listener {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(@Nonnull Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(@Nonnull ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setAmount(@Nonnegative int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(@Nonnull String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(@Nonnull String... lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder toUnbreakable(boolean enabled) {
        this.itemMeta.setUnbreakable(enabled);
        return this;
    }

    public ItemBuilder setFlags(boolean hide) {
        if (hide) {
            this.itemMeta.addItemFlags(ItemFlag.values());
        } else {
            this.itemMeta.removeItemFlags(ItemFlag.values());
        }
        return this;
    }

    public ItemBuilder setFlags(boolean hide, ItemFlag... flags) {
        if (hide) {
            itemMeta.addItemFlags(flags);
        } else {
            itemMeta.removeItemFlags(flags);
        }
        return this;
    }

    public ItemBuilder withEnchantment(@Nonnull Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }

}
