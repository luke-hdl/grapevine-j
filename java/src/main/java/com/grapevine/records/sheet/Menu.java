package com.grapevine.records.sheet;

import java.util.List;

public class Menu {
    //For now - just wraps MenuItem.
    //In the future - Menus in Grapevine do some other stuff.
    protected String name;
    protected List<MenuItem> menuItems;

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name != null ? name : "").append("\r\n");
        if (menuItems != null && !menuItems.isEmpty()) {
            sb.append("Menu Items:\r\n");
            for (MenuItem item : menuItems) {
                sb.append(item.toDetailedString());
            }
        }
        return sb.toString();
    }
}
