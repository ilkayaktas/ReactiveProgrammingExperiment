package com.ilkayaktas.clean.disposables;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by ilkayaktas on 4.11.2020 at 11:50.
 */

public abstract class EmptyObserver<T> extends DisposableObserver<T> {
    public abstract void onNext(T t);
    public abstract void onError(Throwable e);
    public abstract void onComplete();
}
