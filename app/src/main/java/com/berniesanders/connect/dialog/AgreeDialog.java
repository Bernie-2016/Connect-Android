package com.berniesanders.connect.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;

import com.berniesanders.connect.R;
import com.berniesanders.connect.dagger.ActivityScope;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;

@ActivityScope
public class AgreeDialog {
    private final Activity mActivity;
    private final PublishSubject<Boolean> mTermsSubject = PublishSubject.create();
    private final PublishSubject<Boolean> mPrivacySubject = PublishSubject.create();

    @Inject
    public AgreeDialog(final Activity activity) {
        mActivity = activity;
    }

    public Observable<Boolean> onAgreeToTerms() {
        return mTermsSubject;
    }

    public Observable<Boolean> onAgreeToPrivacy() {
        return mTermsSubject;
    }

    public Dialog makeTerms() {
        return makeDialog(R.string.terms_and_conditions, R.string.terms_and_conditions_content, mTermsSubject);
    }

    public Dialog makePrivacy() {
        return makeDialog(R.string.privacy_policy, R.string.privacy_policy_content, mPrivacySubject);
    }

    private Dialog makeDialog(final int title, final int message, final PublishSubject<Boolean> onAgree) {
        return new AlertDialog.Builder(mActivity)
                .setCancelable(false)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.i_agree, (dialog, which) -> onAgree.onNext(true))
                .setNegativeButton(R.string.close_app, (dialog, which) -> onAgree.onNext(false))
                .create();
    }
}
