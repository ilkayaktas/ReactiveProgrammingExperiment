package com.ilkayaktas.clean.usecases.base;

import com.ilkayaktas.clean.executor.PostExecutionThread;
import com.ilkayaktas.clean.executor.ThreadExecutor;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ilkayaktas on 4.11.2020 at 15:38.
 */

public abstract class BaseReactiveUseCase {

    protected final Scheduler threadExecutorScheduler;
    protected final Scheduler postExecutionThreadScheduler;
    private CompositeDisposable disposables = new CompositeDisposable();

    public BaseReactiveUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        threadExecutorScheduler = Schedulers.from(threadExecutor);
        postExecutionThreadScheduler = postExecutionThread.getScheduler();
    }

    public void dispose(){
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}
