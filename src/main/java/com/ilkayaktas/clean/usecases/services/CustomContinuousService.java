package com.ilkayaktas.clean.usecases.services;


import com.ilkayaktas.clean.model.Position;
import io.reactivex.Observable;

/**
 * Created by ilkayaktas on 5.11.2020 at 08:56.
 */

public class CustomContinuousService {
    public Observable<String> doContinuousJob(Position position){
        return Observable.create(emitter -> {
            for (int i = 0; i < 5 ; i++) {
                emitter.onNext(position.latitude + " " + position.longitude);
            }

            emitter.onComplete();
        });
    }
}
