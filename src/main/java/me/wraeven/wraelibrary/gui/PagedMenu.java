package me.wraeven.wraelibrary.gui;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PagedMenu extends MenuHolder {
    public PagedMenu() {
        super();
    }

    protected int page = 0;
    @Setter
    @Getter
    protected int maxItemsPerPage = 45;
    protected int index = 0;

    private final List<Integer> acceptedMaxItems = Arrays.asList(9, 18, 27, 36, 45, 54);

    public boolean check(int items) {
        return acceptedMaxItems.contains(items);
    }

}
