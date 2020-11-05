package com.ilkayaktas.clean.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by ilkayaktas on 5.11.2020 at 09:18.
 */

public class JobExecutor implements ThreadExecutor {
    private final int THREAD_POOL = 3;

    Executor executor = Executors.newFixedThreadPool(THREAD_POOL);

    @Override
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}
