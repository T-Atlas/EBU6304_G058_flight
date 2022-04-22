package com.app.flight.util;

import java.lang.reflect.Field;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.6
 */
public class Obj {
    public static String[] generateObjAttr(Object obj) {
        Class<?> classObj = obj.getClass();
        Field[] fields = classObj.getDeclaredFields();
        String[] title = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            title[i] = fields[i].getName();
        }
        return title;
    }
}
