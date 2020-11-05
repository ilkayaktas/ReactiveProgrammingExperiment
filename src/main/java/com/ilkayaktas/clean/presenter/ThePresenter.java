package com.ilkayaktas.clean.presenter;

import com.ilkayaktas.clean.disposables.EmptyCompletableObserver;
import com.ilkayaktas.clean.disposables.EmptyObserver;
import com.ilkayaktas.clean.disposables.EmptySingleObserver;
import com.ilkayaktas.clean.executor.JobExecutor;
import com.ilkayaktas.clean.executor.PostExecutionThread;
import com.ilkayaktas.clean.executor.ThreadExecutor;
import com.ilkayaktas.clean.executor.UIThread;
import com.ilkayaktas.clean.model.Position;
import com.ilkayaktas.clean.usecases.DoCompletableUsecase;
import com.ilkayaktas.clean.usecases.DoContinuousUsecase;
import com.ilkayaktas.clean.usecases.DoOneThingUsecase;
import com.ilkayaktas.clean.usecases.DoSyncUsecase;

/**
 * Created by ilkayaktas on 5.11.2020 at 09:14.
 */

public class ThePresenter {

    ThreadExecutor threadExecutor = new JobExecutor();
    PostExecutionThread postExecutionThread = new UIThread();

    DoCompletableUsecase doCompletableUsecase = new DoCompletableUsecase(threadExecutor, postExecutionThread);
    DoContinuousUsecase doContinuousUsecase = new DoContinuousUsecase(threadExecutor, postExecutionThread);
    DoOneThingUsecase doOneThingUsecase = new DoOneThingUsecase(threadExecutor, postExecutionThread);
    DoSyncUsecase doSyncUsecase = new DoSyncUsecase();

    public void doDidDone(){

        doCompletableUsecase.execute(new WritingDBResult(), new Position(5,6));

        doContinuousUsecase.execute(new ContinuesResult(), new Position(50,60));

        doOneThingUsecase.execute(new PositionSentResult(), new Position(15,16));

        doSyncUsecase.execute(new Position());
    }

    class PositionSentResult extends EmptySingleObserver<Boolean> {

        @Override
        public void onSuccess(Boolean s) {

        }

        @Override
        public void onError(Throwable e) {

        }
    }

    class WritingDBResult extends EmptyCompletableObserver {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }
    }

    class ContinuesResult extends EmptyObserver<String>{

        @Override
        public void onNext(String s) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
