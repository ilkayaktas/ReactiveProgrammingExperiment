package com.ilkayaktas.clean.usecases;

import com.ilkayaktas.clean.model.Position;
import com.ilkayaktas.clean.usecases.base.SynchronousUseCase;
import com.ilkayaktas.clean.usecases.services.CustomSyncService;

/**
 * Created by ilkayaktas on 5.11.2020 at 09:43.
 */

public class DoSyncUsecase implements SynchronousUseCase<Position, Double> {
    CustomSyncService customSyncService = new CustomSyncService();
    @Override
    public Double execute(Position position) {
        Double d = customSyncService.calculate(position);

        System.out.println("Sync: " + d);
        return d;
    }
}
