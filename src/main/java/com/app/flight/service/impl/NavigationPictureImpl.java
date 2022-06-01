package com.app.flight.service.impl;

import cn.hutool.core.util.StrUtil;
import com.app.flight.service.GetNavigationPicture;
import javafx.scene.image.Image;

import java.io.File;

/**
 * @author LianJunhong
 * @version 1.0
 * @date 2022.5.5
 */
public class NavigationPictureImpl implements GetNavigationPicture {
    public static final String PATH = "data/image/maps/";

    /**
     * This method is used to get the navigation picture
     *
     * @param type What type of gate
     * @return The navigation picture
     */
    @Override
    public Image getNavigationPicture(gateType type) {
        System.out.println(type);
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

    /**
     * This method is used to get the corresponding gate picture by the gate name
     * @param gateName The gate name in String
     * @return The corresponding gate picture
     */
    @Override
    public Image getNavigationPicture(String gateName) {
        return getNavigationPicture(convertStringToGateType(gateName));
    }

    /**
     * This method is used to convert the gate name to the gate type
     * @param gateName The gate name in String
     * @return The corresponding gate type
     */
    @Override
    public gateType convertStringToGateType(String gateName) {
        if (!StrUtil.isBlank(gateName)) {
            switch (gateName.substring(0, 1).toUpperCase()) {
                case "A" -> {
                    return gateType.GATE_A;
                }
                case "B" -> {
                    return gateType.GATE_B;
                }
                case "C" -> {
                    return gateType.GATE_C;
                }
                case "D" -> {
                    return gateType.GATE_D;
                }
                default -> {
                    return null;
                }
            }
        } else {
            return null;
        }
    }
}
