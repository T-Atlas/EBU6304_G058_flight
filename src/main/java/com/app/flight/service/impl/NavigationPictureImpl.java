package com.app.flight.service.impl;

import com.app.flight.service.GetNavigationPicture;
import javafx.scene.image.Image;

import java.io.File;

public class NavigationPictureImpl implements GetNavigationPicture {
    private final String PATH = "src/main/resources/com/app/flight/image/maps/";

    /**
     * @param type
     * @return
     */
    @Override
    public Image getNavigationPicture(gateType type) {
        switch (type) {
            case GATE_A -> {
                return new Image(new File(PATH + "A.png").toURI().toString());
            }
            case GATE_B -> {
                return new Image(new File(PATH + "B.png").toURI().toString());
            }
            case GATE_C -> {
                return new Image(new File(PATH + "C.png").toURI().toString());
            }
            case GATE_D -> {
                return new Image(new File(PATH + "D.png").toURI().toString());
            }
            default -> {
                return null;
            }
        }
    }
}
