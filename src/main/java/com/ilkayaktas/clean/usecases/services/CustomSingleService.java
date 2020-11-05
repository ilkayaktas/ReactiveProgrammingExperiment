package com.ilkayaktas.clean.usecases.services;

import com.ilkayaktas.clean.model.Position;
import io.reactivex.Single;

/**
 * Created by ilkayaktas on 5.11.2020 at 08:56.
 */

public class CustomSingleService {
    public Single<Boolean> doSingleJob(Position position){
        return Single.create(emitter -> {
            System.out.println(position.latitude + " "+ position.longitude);

            emitter.onSuccess(true);
        });
    }
}
