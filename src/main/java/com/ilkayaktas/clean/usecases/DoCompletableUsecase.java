package com.ilkayaktas.clean.usecases;

import com.ilkayaktas.clean.executor.PostExecutionThread;
import com.ilkayaktas.clean.executor.ThreadExecutor;
import com.ilkayaktas.clean.model.Position;
import com.ilkayaktas.clean.usecases.base.CompletableUseCase;
import com.ilkayaktas.clean.usecases.services.CustomCompletableService;
import io.reactivex.Completable;

/**
 * Created by ilkayaktas on 5.11.2020 at 09:06.
 */

public class DoCompletableUsecase extends CompletableUseCase<Position> {
    CustomCompletableService completableService = new CustomCompletableService();

    public DoCompletableUsecase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Completable buildUseCaseCompletable(Position params) {
        return completableService.doCompletableJob(params);
    }
}
