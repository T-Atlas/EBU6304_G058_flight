package com.app.flight.util;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public static String getSelectType() {
        String typeStr = Json.extractJsonData(Json.TYPE_JSON_PATH);
        if (typeStr != null) {
            JSONPath typePath = JSONPath.of("$.type");
            return (String) typePath.extract(JSONReader.of(typeStr));
        } else {
            return null;
        }
    }

    public static void setSelectType(String selectType) {
        try (FileWriter typeJson = new FileWriter(Json.TYPE_JSON_PATH); PrintWriter out = new PrintWriter(typeJson)) {
            String type = "{\n" +
                    "\t\"type\":\"" + selectType + "\"\n" +
                    "}";
            out.write(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
