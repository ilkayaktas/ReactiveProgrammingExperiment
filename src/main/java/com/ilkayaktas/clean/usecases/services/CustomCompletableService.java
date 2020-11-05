package com.ilkayaktas.clean.usecases.services;

import com.ilkayaktas.clean.model.Position;
import io.reactivex.Completable;

/**
 * Created by ilkayaktas on 5.11.2020 at 08:54.
 */

public class CustomCompletableService {
    public Completable doCompletableJob(Position position){
        return Completable.create(emitter -> {
            System.out.println(position.latitude + " " + position.longitude);
            emitter.onComplete();
        });
    }
}
