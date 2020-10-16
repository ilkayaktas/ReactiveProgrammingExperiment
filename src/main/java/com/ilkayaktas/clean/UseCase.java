package com.ilkayaktas.clean;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ilkayaktas on 16.10.2020 at 09:33.
 */

public class UseCase<T> {

    private final CompositeDisposable disposables;
    private Observable<T> observable;

    public UseCase(Observable<T> observable) {
        this.disposables = new CompositeDisposable();
        this.observable = observable;
    }

    public void execute(DisposableObserver<T> observer) {

        final Observable<T> observable = this.observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread());

        observable.subscribeWith(observer);

    }

    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
