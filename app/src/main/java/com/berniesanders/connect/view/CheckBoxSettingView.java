package com.berniesanders.connect.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.annimon.stream.function.Consumer;
import com.berniesanders.connect.R;
import com.berniesanders.connect.controller.HasSettingText;
import com.berniesanders.connect.controller.SettingTextController;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckBoxSettingView extends FrameLayout implements HasSettingText {
    private SettingTextController mSettingTextController;

    @Bind(R.id.setting_item)
    View mItem;

    @Bind(R.id.checkbox)
    CheckBox mCheckBox;

    public CheckBoxSettingView(final Context context) {
        super(context);
        init();
    }

    public CheckBoxSettingView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_checkbox_setting, this, true);
        ButterKnife.bind(this);
        mSettingTextController = new SettingTextController(this);
        mItem.setOnClickListener(view -> mCheckBox.toggle());
    }

    public void configure(final boolean isEnabled, final Consumer<Boolean> setEnabled) {
        mCheckBox.setChecked(isEnabled);
        mCheckBox.setOnCheckedChangeListener((view, isChecked) -> setEnabled.accept(isChecked));
    }

    @Override
    public void setTitle(final int resid) {
        mSettingTextController.setTitle(resid);
    }

    @Override
    public void setTitle(final String text) {
        mSettingTextController.setTitle(text);
    }

    @Override
    public void setDescription(final int resid) {
        mSettingTextController.setDescription(resid);
    }

    @Override
    public void setDescription(final String text) {
        mSettingTextController.setDescription(text);
    }
}
