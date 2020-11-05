package com.ilkayaktas.clean.usecases;

import com.ilkayaktas.clean.executor.PostExecutionThread;
import com.ilkayaktas.clean.executor.ThreadExecutor;
import com.ilkayaktas.clean.model.Position;
import com.ilkayaktas.clean.usecases.base.ObservableUseCase;
import com.ilkayaktas.clean.usecases.services.CustomContinuousService;
import io.reactivex.Observable;

/**
 * Created by ilkayaktas on 5.11.2020 at 09:00.
 */

public class DoContinuousUsecase extends ObservableUseCase<Position, String> {
    CustomContinuousService customContinuousService = new CustomContinuousService();

    public DoContinuousUsecase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable<String> buildUseCaseObservable(Position params) {
        return customContinuousService.doContinuousJob(params);
    }
}
