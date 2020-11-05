package com.ilkayaktas.clean.disposables;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by ilkayaktas on 4.11.2020 at 11:52.
 */

public abstract class EmptySingleObserver<T> extends DisposableSingleObserver<T> {
    public abstract void onSuccess(T t) ;
    public abstract void onError(Throwable e);
}
