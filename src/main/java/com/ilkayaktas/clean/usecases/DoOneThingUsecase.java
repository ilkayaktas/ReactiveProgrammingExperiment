package com.ilkayaktas.clean.usecases;

import com.ilkayaktas.clean.executor.PostExecutionThread;
import com.ilkayaktas.clean.executor.ThreadExecutor;
import com.ilkayaktas.clean.model.Position;
import com.ilkayaktas.clean.usecases.base.SingleUseCase;
import com.ilkayaktas.clean.usecases.services.CustomSingleService;
import io.reactivex.Single;

/**
 * Created by ilkayaktas on 5.11.2020 at 08:21.
 */

public class DoOneThingUsecase extends SingleUseCase<Position, Boolean> {

    CustomSingleService customSingleService = new CustomSingleService();

    public DoOneThingUsecase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Single<Boolean> buildUseCaseSingle(Position params) {
        return customSingleService.doSingleJob(params).doAfterSuccess(aBoolean -> System.out.println("Başarılı"));
    }
}
