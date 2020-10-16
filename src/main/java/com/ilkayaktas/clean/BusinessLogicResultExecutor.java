package com.ilkayaktas.clean;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by ilkayaktas on 16.10.2020 at 09:47.
 */

public class BusinessLogicResultExecutor<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
        System.out.println("This is output of business logic " + t);
    }

    @Override
    public void onError(Throwable e) {
        System.err.println("This is error from business logic");
    }

    @Override
    public void onComplete() {
        System.out.println("This is completion of business logic");
    }
}
