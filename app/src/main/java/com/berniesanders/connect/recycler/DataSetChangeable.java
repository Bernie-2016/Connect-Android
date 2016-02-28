package com.berniesanders.connect.recycler;

import rx.Observable;

public interface DataSetChangeable {
    Observable<Void> onChange();
}
