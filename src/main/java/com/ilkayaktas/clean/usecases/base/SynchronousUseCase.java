package com.ilkayaktas.clean.usecases.base;

/**
 * Created by ilkayaktas on 4.11.2020 at 15:47.
 */

public interface SynchronousUseCase<P,R> {
    // Executes the current use case and returns the result immediately.
    R execute(P p);
}
