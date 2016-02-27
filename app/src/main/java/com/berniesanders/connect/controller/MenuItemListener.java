package com.berniesanders.connect.controller;

public interface MenuItemListener {
    /**
     * @return true if the item should be displayed as selected
     */
    boolean onSelected(final int id);
}
