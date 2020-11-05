package com.ilkayaktas.clean.executor;


import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ilkayaktas on 5.11.2020 at 09:20.
 */

public class UIThread implements PostExecutionThread {

    @Override
    public Scheduler getScheduler() {
        //return AndroidSchedulers.mainThread();
        return Schedulers.newThread();
    }
}
