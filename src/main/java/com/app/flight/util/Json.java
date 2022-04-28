package com.app.flight.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 */
public class Json {
    public static boolean writeJson(String jsonFilePath, Object obj) {
        try (FileWriter jsonWriter = new FileWriter(jsonFilePath); PrintWriter out = new PrintWriter(jsonWriter)) {
            String objString = JSON.toJSONString(obj, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
            out.write(objString);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
