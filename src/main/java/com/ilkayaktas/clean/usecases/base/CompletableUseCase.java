package com.ilkayaktas.clean.usecases.base;

import com.ilkayaktas.clean.disposables.EmptyCompletableObserver;
import com.ilkayaktas.clean.executor.PostExecutionThread;
import com.ilkayaktas.clean.executor.ThreadExecutor;
import io.reactivex.Completable;

/**
 * Created by ilkayaktas on 4.11.2020 at 15:45.
 */

public abstract class CompletableUseCase<P> extends  BaseReactiveUseCase{
    public CompletableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    protected abstract Completable buildUseCaseCompletable(P params);

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableCompletableObserver] which will be listening to the observer build
     * by [buildUseCaseCompletable] method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(EmptyCompletableObserver observer, P params) {
        Completable completable = buildUseCaseCompletableWithSchedulers(params);
        addDisposable(completable.subscribeWith(observer));
    }

    /**
     * Builds a [Completable] which will be used when executing the current [CompletableUseCase].
     * With provided Schedulers
     */
    private Completable buildUseCaseCompletableWithSchedulers(P params) {
        return buildUseCaseCompletable(params)
                .subscribeOn(threadExecutorScheduler)
                .observeOn(postExecutionThreadScheduler);
    }
}
