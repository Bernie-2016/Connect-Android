package com.berniesanders.connect.recycler;

import rx.Observable;

public interface CanChangeData {
    Observable<Void> getDataChanges();
}
