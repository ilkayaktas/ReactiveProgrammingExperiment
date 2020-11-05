package com.ilkayaktas.clean.usecases.base;

import com.ilkayaktas.clean.disposables.EmptySingleObserver;
import com.ilkayaktas.clean.executor.PostExecutionThread;
import com.ilkayaktas.clean.executor.ThreadExecutor;
import io.reactivex.Single;

/**
 * Created by ilkayaktas on 4.11.2020 at 15:46.
 */

public abstract class SingleUseCase<P ,R> extends BaseReactiveUseCase{
    public SingleUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    /**
     * Builds an [Single] which will be used when executing the current [SingleUseCase].
     */
    protected abstract Single<R> buildUseCaseSingle(P params);

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableSingleObserver] which will be listening to the observer build
     * by [buildUseCaseSingle] method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(EmptySingleObserver<R> observer, P params) {
        Single<R> single = buildUseCaseSingleWithSchedulers(params);
        addDisposable(single.subscribeWith(observer));
    }

    /**
     * Builds a [Single] which will be used when executing the current [SingleUseCase].
     * With provided Schedulers
     */
    private Single<R> buildUseCaseSingleWithSchedulers(P params) {
        return buildUseCaseSingle(params)
                .subscribeOn(threadExecutorScheduler)
                .observeOn(postExecutionThreadScheduler);
    }
}
