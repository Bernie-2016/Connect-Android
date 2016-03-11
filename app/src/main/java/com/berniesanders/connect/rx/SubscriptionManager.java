/*
 * Copyright 2014 Prateek Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.berniesanders.connect.rx;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

import static com.berniesanders.connect.rx.Assertions.assertUiThread;
import static rx.android.schedulers.AndroidSchedulers.mainThread;

/**
 * A class to help manage subscriptions. It will automatically unsubscribe any subscription if the
 * predicate does not validate.
 */
public abstract class SubscriptionManager<T> {
    private final T mContext;
    private final List<Subscription> mSubscriptions = new ArrayList<>();

    public SubscriptionManager(T mContext) {
        this.mContext = mContext;
    }

    public <O> Subscription subscribe(final Observable<O> source, final Observer<O> observer) {
        assertUiThread();

        Subscription subscription = source.observeOn(mainThread())
                .lift(new OperatorConditionalBinding<>(mContext, this::validate))
                .subscribe(observer);

        mSubscriptions.add(subscription);
        return subscription;
    }

    public void unsubscribe() {
        assertUiThread();

        Stream.of(mSubscriptions).forEach(Subscription::unsubscribe);
        mSubscriptions.clear();
    }

    /**
     * Return a {@link rx.functions.Func1} implementation that will be a predicate for {@link
     * OperatorConditionalBinding}. If the predicate fails to validate, the sequence unsubscribes
     * itself and releases the bound reference.
     */
    protected abstract boolean validate(final T object);
}