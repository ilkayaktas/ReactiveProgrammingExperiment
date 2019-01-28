package com.ilkayaktas;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.TimerTask;

/**
 * Created by aselsan on 28.01.2019 at 09:44.
 */

public class AsyncJob extends TimerTask {
    private Observable<String> observable;

    public AsyncJob(Observable<String> observable) {
        this.observable = observable;
    }

    @Override
    public void run() {
        observable.subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .map(s -> Thread.currentThread().getName()+" "+s)
                .subscribe(System.out::println);
    }
}
