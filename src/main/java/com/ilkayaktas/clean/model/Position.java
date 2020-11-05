package com.ilkayaktas.clean.model;

/**
 * Created by ilkayaktas on 5.11.2020 at 08:22.
 */

public class Position {
    public double latitude;
    public double longitude;

    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Position() {
    }
}
