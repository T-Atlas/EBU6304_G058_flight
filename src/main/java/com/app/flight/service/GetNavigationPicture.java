package com.app.flight.service;

import javafx.scene.image.Image;

public interface GetNavigationPicture {
    Image getNavigationPicture(gateType type);

    enum gateType {
        /**
         * 4 types of gate
         */
        GATE_A, GATE_B, GATE_C, GATE_D;
    }
}
