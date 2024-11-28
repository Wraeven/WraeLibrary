package me.wraeven.wraelibrary.gui;

import java.util.Arrays;
import java.util.List;

public abstract class PagedMenu extends MenuHolder {
    public PagedMenu(int maxItemsPerPage) {
        super();
        if(checkQuantity(maxItemsPerPage)) {
            this.maxItemsPerPage = maxItemsPerPage;
        }
    }

    protected int page = 0;
    protected int maxItemsPerPage;
    protected int index = 0;

    private final List<Integer> acceptedMaxItems = Arrays.asList(9, 18, 27, 36, 45, 54);

    private boolean checkQuantity(int items) {
        return acceptedMaxItems.contains(items);
    }
    public int getPage() {return page;}
    public int getMaxItemsPerPage() {return maxItemsPerPage;}
    public void setMaxItemsPerPage(int maxItemsPerPage) {this.maxItemsPerPage = maxItemsPerPage;}

}
