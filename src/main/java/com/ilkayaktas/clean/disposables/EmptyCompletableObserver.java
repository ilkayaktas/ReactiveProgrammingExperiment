package com.ilkayaktas.clean.disposables;

import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by ilkayaktas on 4.11.2020 at 11:48.
 */

public abstract class EmptyCompletableObserver extends DisposableCompletableObserver {
    public abstract void onComplete();
    public abstract void onError(Throwable e);
}
