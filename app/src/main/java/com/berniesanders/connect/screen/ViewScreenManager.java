package com.berniesanders.connect.screen;

import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.berniesanders.connect.dagger.ActivityScope;
import com.berniesanders.connect.dagger.Name;
import com.berniesanders.connect.hook.ActivityHook;
import com.berniesanders.connect.hook.ActivityHookBuilder;

import javax.inject.Inject;
import javax.inject.Named;

@ActivityScope
public class ViewScreenManager {
    private static final int DESTROYED = 0;
    private static final int CREATED = 1;
    private static final int SHOWN = 2;

    private final int mContainerId;

    private int mState = DESTROYED;
    private Optional<Screen<View>> mCurrentScreen = Optional.empty();
    private ViewGroup mContainer;

    @Inject
    public ViewScreenManager(@Named(Name.CONTAINER) final int containerId) {
        mContainerId = containerId;
    }

    public ActivityHook getActivityHook() {
        return new ActivityHookBuilder()
                .onCreate(((activity, savedInstanceState) -> {
                    mContainer = (ViewGroup) activity.findViewById(mContainerId);
                    create();
                }))
                .onDestroy(activity -> {
                    destroy();
                    mContainer = null;
                })
                .onResume(activity -> show())
                .onPause(activity -> hide())
                .build();
    }

    public void switchTo(final Screen<View> screen) {
        if (!mCurrentScreen.map(current -> current == screen).orElse(false)) {
            if (mState == SHOWN) {
                hide();
                destroy();
                mCurrentScreen = Optional.of(screen);
                create();
                show();
            } else if (mState == CREATED) {
                destroy();
                mCurrentScreen = Optional.of(screen);
                create();
            } else {
                mCurrentScreen = Optional.of(screen);
            }
        }
    }

    private void create() {
        if (mState == DESTROYED) {
            mCurrentScreen.ifPresent(screen -> {
                screen.create();
                mContainer.addView(screen.getView().create());
            });

            mState = CREATED;
        }
    }

    private void destroy() {
        if (mState == CREATED) {
            mCurrentScreen.ifPresent(screen -> {
                mContainer.removeAllViews();
                screen.destroy();
            });

            mState = DESTROYED;
        }
    }

    private void show() {
        if (mState == CREATED) {
            mCurrentScreen.ifPresent(current -> {
                Stream.of(current.getComponents()).forEach(ScreenComponent::show);
            });

            mState = SHOWN;
        }
    }

    public void hide() {
        if (mState == SHOWN) {
            mCurrentScreen.ifPresent(current -> {
                Stream.of(current.getComponents()).forEach(ScreenComponent::hide);
            });

            mState = CREATED;
        }
    }
}
