package com.berniesanders.connect.controller;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.berniesanders.connect.R;
import com.berniesanders.connect.util.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingTextController implements HasSettingText {
    private final Context mContext;

    @Bind(R.id.text_title)
    TextView mTitle;

    @Bind(R.id.text_description)
    TextView mDescription;

    public SettingTextController(final View view) {
        mContext = view.getContext();
        ButterKnife.bind(this, view);
        mTitle.setVisibility(View.GONE);
        mDescription.setVisibility(View.GONE);
    }

    @Override
    public void setTitle(final int resid) {
        setTitle(mContext.getString(resid));
    }

    @Override
    public void setTitle(final String text) {
        setText(mTitle, text);
    }

    @Override
    public void setDescription(final int resid) {
        setDescription(mContext.getString(resid));
    }

    @Override
    public void setDescription(final String text) {
        setText(mDescription, text);
    }

    private static void setText(final TextView textView, final String text) {
        if (StringUtil.isEmpty(text)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
        }
    }
}
