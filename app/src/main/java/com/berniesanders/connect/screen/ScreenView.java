package com.berniesanders.connect.screen;

public interface ScreenView<T> extends ScreenComponent {
    T create();
}
