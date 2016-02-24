package com.berniesanders.connect.hook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public interface ActivityOnSaveInstanceState {
    void onSaveInstanceState(final AppCompatActivity activity, final Bundle outState);
}
