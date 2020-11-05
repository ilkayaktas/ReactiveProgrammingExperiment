package com.ilkayaktas.clean.usecases.services;

import com.ilkayaktas.clean.model.Position;

/**
 * Created by ilkayaktas on 5.11.2020 at 09:43.
 */

public class CustomSyncService {
    public Double calculate(Position position){
        double d = 0;
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("Sync operation keep moving!!!");
                d += i;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return d;
    }
}
