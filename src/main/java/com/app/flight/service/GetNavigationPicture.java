package com.app.flight.service;

import com.app.flight.service.impl.NavigationPictureImpl;
import javafx.scene.image.Image;

/**
 * @author LianJunhong
 * @version 3.5
 * @see NavigationPictureImpl
 */
public interface GetNavigationPicture {
    Image getNavigationPicture(gateType type);

    Image getNavigationPicture(String gateName);

    gateType convertStringToGateType(String type);

    enum gateType {
        /**
         * 4 types of gate
         */
        GATE_A, GATE_B, GATE_C, GATE_D;
    }
}
