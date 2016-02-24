package com.berniesanders.connect.hook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public interface ActivityOnCreate {
    void onCreate(final AppCompatActivity activity, final Bundle savedInstanceState);
}
