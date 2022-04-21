package com.app.flight.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.app.flight.entity.Admin;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * @author SongBo
 */
public class Csv {
    /**
     * 添加一行csv数据
     *
     * @param entity   添加数据对应的实体
     * @param filePath 添加数据的文件目录
     * @param unique   该实体是否有唯一字段
     * @return 是否添加成功
     */
    public static boolean addCsv(Object entity, String filePath, boolean unique) {
        String data = JSON.toJSONString(entity, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNonStringValueAsString);
        JSONObject jsonObj = JSONObject.parseObject(data, Feature.OrderedField);
        String[] csvHeaders = Obj.generateObjAttr(entity);
        int i = 0;
        try {
            if (unique) {
                ArrayList<String[]> csvData = readCsv(filePath);
                for (String[] csvRowData : csvData) {
                    for (int j = 0; j < csvData.size(); j++) {
                        if (csvRowData[0].equals(jsonObj.getString(csvHeaders[0]))) {
                            System.out.println("Duplicate data addition");
                            return false;
                        }
                    }
                }
            }
            String[] csvContents = getCsvContents(jsonObj, csvHeaders, i);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), StandardCharsets.UTF_8));
            CsvWriter csvWriter = new CsvWriter(bufferedWriter, ',');
            //csvWriter.writeRecord(csvHeaders);
            csvWriter.writeRecord(csvContents);
            System.out.println("CSV added successfully");
            csvWriter.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 读取所有csv数据
     *
     * @param filePath 读取csv数据的文件目录
     * @return 读取到的数据集
     */
    public static ArrayList<String[]> readCsv(String filePath) {
        ArrayList<String[]> csvList = new ArrayList<>();
        try {
            CsvReader reader = new CsvReader(filePath, ',', StandardCharsets.UTF_8);
            reader.readHeaders();
            while (reader.readRecord()) {
                csvList.add(reader.getValues());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvList;
    }

    /**
     * 更新某一行csv数据并且该实体必须有唯一字段
     *
     * @param entity   更新数据对应的实体
     * @param filePath 更新数据的文件目录
     * @return 是否更新成功
     */
    public static boolean updateCsv(Object entity, String filePath) {
        if (!deleteCsv(entity, filePath, true)) {
            System.out.println("Data does not exist");
        } else {
            if (addCsv(entity, filePath, false)) {
                System.out.println("Data update succeeded");
                return true;
            }
        }
        System.out.println("Data update failed");
        return false;
    }

    /**
     * 删除某一行csv数据
     *
     * @param entity   删除数据对应的实体
     * @param filePath 删除数据的文件目录
     * @param unique   该实体是否有唯一字段
     * @return 是否删除成功
     */
    public static boolean deleteCsv(Object entity, String filePath, boolean unique) {
        String data = JSON.toJSONString(entity, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNonStringValueAsString);
        JSONObject jsonObj = JSONObject.parseObject(data, Feature.OrderedField);
        String[] csvHeaders = Obj.generateObjAttr(entity);
        ArrayList<String[]> csvData = readCsv(filePath);
        int i = 0;
        for (String[] csvRowData : csvData) {
            if (unique) {
                if (csvRowData[0].equals(jsonObj.getString(csvHeaders[0]))) {
                    return rewriteCsv(filePath, csvHeaders, csvData, i);
                }
            } else {
                boolean only = true;
                String[] csvContents = getCsvContents(jsonObj, csvHeaders, i);
                for (int j = 0; j < csvHeaders.length; j++) {
                    if (!csvRowData[j].equals(csvContents[j])) {
                        only = false;
                        break;
                    }
                }
                if (only) {
                    return rewriteCsv(filePath, csvHeaders, csvData, i);
                }
            }
            i++;
        }
        System.out.println("Data removal failed");
        return false;
    }

    private static String[] getCsvContents(JSONObject jsonObj, String[] csvHeaders, int i) {
        String[] csvContent = new String[csvHeaders.length];
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            if (entry.getValue() instanceof String) {
                csvContent[i] = (String) entry.getValue();
            } else {
                JSONObject innerJsonObj = jsonObj.getJSONObject(entry.getKey());
                Iterator<Map.Entry<String, Object>> iterator = innerJsonObj.entrySet().iterator();
                csvContent[i] = (String) iterator.next().getValue();
            }
            i++;
        }
        return csvContent;
    }

    private static boolean rewriteCsv(String filePath, String[] csvHeaders, ArrayList<String[]> csvData, int i) {
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
        System.out.println("Data removed successfully");
        return true;
    }

    public static Object checkCsv(Object entity, String filePath) {
        if (entity == null) {
            return null;
        } else {
            return new Admin();
        }
    }


}

