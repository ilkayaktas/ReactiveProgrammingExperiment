package com.ilkayaktas.clean.usecases.base;

import com.ilkayaktas.clean.disposables.EmptyObserver;
import com.ilkayaktas.clean.executor.PostExecutionThread;
import com.ilkayaktas.clean.executor.ThreadExecutor;
import io.reactivex.Observable;

/**
 * Created by ilkayaktas on 4.11.2020 at 15:46.
 */

public abstract class ObservableUseCase<P, R> extends BaseReactiveUseCase {
    public ObservableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    protected abstract Observable<R> buildUseCaseObservable(P params);

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableObserver] which will be listening to the observer build
     * by [buildUseCaseObservable] method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(EmptyObserver<R> observer, P params) {
        Observable<R> observable = buildUseCaseObservableWithSchedulers(params);
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Builds an [Observable] which will be used when executing the current [ObservableUseCase].
     * With provided Schedulers
     */
    private Observable<R> buildUseCaseObservableWithSchedulers(P params) {
        return buildUseCaseObservable(params)
                .subscribeOn(threadExecutorScheduler)
                .observeOn(postExecutionThreadScheduler);
    }
}
