package me.wraeven.wraelibrary.gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.nio.Buffer;
import java.util.List;

public abstract class MenuHolder implements InventoryHolder {
    protected Inventory inventory;
    protected ItemStack FILLER = createItem(Material.GRAY_STAINED_GLASS_PANE, Component.text(""), null);
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public abstract String getMenuName();
    public abstract int getSlots();
    public abstract void handleMenu(InventoryClickEvent event);
    public abstract void setMenuItems();

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), Component.text(getMenuName()));
        this.setMenuItems();

    }
    protected ItemStack createItem(final Material material, Component name, List<Component> lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(name.style(style -> style.decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)));
        if(lore != null) {
            meta.lore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }
    public void setGlass() {
        for (int i = 0; i < getSlots(); i++) {
            if(inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER);
            }
        }
    }
}
