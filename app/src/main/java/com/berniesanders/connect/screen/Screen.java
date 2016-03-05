package com.berniesanders.connect.screen;

import java.util.Collection;

public interface Screen<T> {
    void create();
    void destroy();
    ScreenView<T> getView();
    Collection<ScreenComponent> getComponents();
}
