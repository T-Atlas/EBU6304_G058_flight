package com.app.flight.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.app.flight.entity.Passenger;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author SongBo
 */
public class Csv {
    public static boolean addEntityToCsv(Object entity, String filePath){
        String data = JSON.toJSONString(entity, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNonStringValueAsString);
        JSONObject jsonObj = JSONObject.parseObject(data, Feature.OrderedField);
        String[] csvHeaders = Obj.generateObjAttr(entity);
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

    public static ArrayList<String[]> readCsvFile(String filePath) {
        ArrayList<String[]> csvList = new ArrayList<>();
        try {
            CsvReader reader = new CsvReader(filePath,',', StandardCharsets.UTF_8);
            reader.readHeaders();
            while(reader.readRecord()){
                csvList.add(reader.getValues());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvList;
    }

    public static void main(String[] args) {
        Passenger passenger = new Passenger();
        passenger.setPassengerId("220802200005217777");
        passenger.setFirstName("Test");
        passenger.setLastName("Lian");
        passenger.setTelephone("13104368888");
        passenger.setAge(18);
        String filePath = "src/main/resources/com/app/flight/data/csv/Passenger.csv";
        if (Csv.addEntityToCsv(passenger, filePath)) {
            System.out.println("添加csv成功");
        }
    }
}
