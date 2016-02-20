package com.berniesanders.connect;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Inject
    Context mContext;

    @Bind(value = R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectApplication.component().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mText.setText(mContext.getPackageName());
    }
}
