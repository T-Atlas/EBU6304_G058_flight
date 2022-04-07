package com.app.flight.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.app.flight.entity.Passenger;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author SongBo
 */
public class Csv {
    public static boolean addCsv(Object entity, String filePath){
        String data = JSON.toJSONString(entity, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNonStringValueAsString);
        JSONObject jsonObj = JSONObject.parseObject(data, Feature.OrderedField);
        String[] csvHeaders = Obj.generateObjAttr(entity);
        String[] csvContent = new String[csvHeaders.length];
        int i = 0;
        try {
            ArrayList<String[]> csvData = readCsv(filePath);
            for (String[] csvRowData : csvData) {
                for (int j = 0; j < csvData.size(); j++) {
                    if (csvRowData[0].equals(jsonObj.getString(csvHeaders[0]))) {
                        System.out.println("数据重复添加");
                        return false;
                    }
                }
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), StandardCharsets.UTF_8));
            CsvWriter csvWriter = new CsvWriter(bufferedWriter, ',');
            for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
                csvContent[i] = (String) entry.getValue();
                i++;
            }
            csvWriter.writeRecord(csvContent, false);
            System.out.println("添加csv成功");
            csvWriter.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String[]> readCsv(String filePath) {
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

    public static boolean updateCsv(Object entity, String filePath) {
        if (!deleteCsv(entity, filePath)) {
            System.out.println("数据不存在");
            return false;
        } else {
            if (addCsv(entity, filePath)) {
                System.out.println("数据更新成功");
                return true;
            }
            System.out.println("数据更新失败");
        }
        return false;
    }

    public static boolean deleteCsv(Object entity, String filePath) {
        String data = JSON.toJSONString(entity, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNonStringValueAsString);
        JSONObject jsonObj = JSONObject.parseObject(data, Feature.OrderedField);
        String[] csvHeaders = Obj.generateObjAttr(entity);
        ArrayList<String[]> csvData = readCsv(filePath);
        int i = 0;
        for (String[] csvRowData : csvData) {
            if (csvRowData[0].equals(jsonObj.getString(csvHeaders[0]))) {
                csvData.remove(i);
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
                    CsvWriter csvWriter = new CsvWriter(bufferedWriter, ',');
                    csvWriter.writeRecord(csvHeaders);
                    for (String[] newData : csvData) {
                        csvWriter.writeRecord(newData);
                    }
                    csvWriter.close();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("数据移除成功");
                return true;
            }
            i++;
        }
        System.out.println("数据移除失败");
        return false;
    }

    public static void main(String[] args) {
        Passenger passenger = new Passenger();
        passenger.setPassengerId("220802200005217777");
        passenger.setFirstName("笨蛋");
        passenger.setLastName("小");
        passenger.setTelephone("13104362121");
        passenger.setAge(5);
        String filePath = "src/main/resources/com/app/flight/data/csv/Passenger.csv";
        //Csv.addCsv(passenger, filePath);
        //Csv.deleteCsv(passenger, filePath);
        //Csv.updateCsv(passenger, filePath);
    }
}
