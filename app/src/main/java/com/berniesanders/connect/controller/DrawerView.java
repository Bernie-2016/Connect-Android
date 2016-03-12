package com.berniesanders.connect.controller;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

public class DrawerView {
    private final DrawerLayout mDrawerLayout;
    private final NavigationView mNavigationView;

    public DrawerView(final DrawerLayout drawerLayout, final NavigationView navigationView) {
        mDrawerLayout = drawerLayout;
        mNavigationView = navigationView;
    }

    public void open() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    public void close() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    public void toggle() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            close();
        } else {
            open();
        }
    }

    public void setMenuItemListener(final MenuItemListener listener) {
        mNavigationView.setNavigationItemSelectedListener(menuItem -> listener.onSelected(menuItem.getItemId()));
    }
}
