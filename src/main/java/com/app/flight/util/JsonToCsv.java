package com.app.flight.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.csvreader.CsvWriter;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author SongBo
 */
public class JsonToCsv {
    public static boolean addEntityToCsv(Object entities, String filePath){
        String data = JSON.toJSONString(entities, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNonStringValueAsString);
        JSONObject jsonObj = JSONObject.parseObject(data, Feature.OrderedField);
        String[] csvHeaders = Obj.generateObjAttr(entities);
        String[] csvContent = new String[csvHeaders.length];
        int i = 0;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), StandardCharsets.UTF_8));
            CsvWriter csvWriter = new CsvWriter(bufferedWriter, ',');
            for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
                csvContent[i] = (String) entry.getValue();
                i++;
            }
            csvWriter.writeRecord(csvContent, false);
            csvWriter.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
