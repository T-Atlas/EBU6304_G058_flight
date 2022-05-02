package com.app.flight.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
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
 * @version 2.0
 * @date 2022.4.16
 */
public class Csv {
    public static final String FLIGHT_CSV_PATH = "src/main/resources/com/app/flight/data/csv/Flight.csv";
    public static final String FOOD_CSV_PATH = "src/main/resources/com/app/flight/data/csv/Food.csv";
    public static final String PASSENGER_CSV_PATH = "src/main/resources/com/app/flight/data/csv/Passenger.csv";
    public static final String RESERVATION_CSV_PATH = "src/main/resources/com/app/flight/data/csv/Reservation.csv";
    public static final String BOARDING_PASS_CSV_PATH = "src/main/resources/com/app/flight/data/csv/BoardingPass.csv";

    /**
     * Add a row of csv data
     *
     * @param entity   Add the entity corresponding to the data
     * @param filePath File directory for adding data
     * @param unique   Does the entity have a unique field
     * @return Whether added successfully or not
     */
    public static boolean addCsv(Object entity, String filePath, boolean unique) {
        String data = JSON.toJSONString(entity, JSONWriter.Feature.WriteNonStringValueAsString, JSONWriter.Feature.WriteEnumsUsingName);
        JSONObject jsonObj = JSON.parseObject(data);
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
     * Read all csv data
     *
     * @param filePath Directory of files for reading csv data
     * @return Retrieved data sets
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
     * Update a row of csv data and the entity must have a unique field
     *
     * @param entity   Update the entity corresponding to the data
     * @param filePath File directory for updating data
     * @return Whether updated successfully or not
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
     * Delete a row of csv data
     *
     * @param entity   Delete the entity corresponding to the data
     * @param filePath File directory for deleted data
     * @param unique   Does the entity have a unique field
     * @return Whether deleted successfully or not
     */
    public static boolean deleteCsv(Object entity, String filePath, boolean unique) {
        String data = JSON.toJSONString(entity, JSONWriter.Feature.WriteNonStringValueAsString);
        JSONObject jsonObj = JSON.parseObject(data);
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
        System.out.println("Data removed failed");
        return false;
    }

    private static String[] getCsvContents(JSONObject jsonObj, String[] csvHeaders, int i) {
        String[] csvContent = new String[csvHeaders.length];
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            if (entry.getValue() instanceof String) {
                csvContent[i] = (String) entry.getValue();
            } else {
                JSONObject innerJsonObj = jsonObj.getJSONObject(entry.getKey());
                System.out.println(entry.getKey());
                Iterator<Map.Entry<String, Object>> iterator = innerJsonObj.entrySet().iterator();
                csvContent[i] = (String) iterator.next().getValue();
            }
            i++;
        }

        /*for (; i<csvHeaders.length; i++) {
            if (jsonObj.get(csvHeaders[i]) instanceof String) {
                System.out.println(jsonObj.get(csvHeaders[i]));
                csvContent[i] = (String) jsonObj.get(csvHeaders[i]);
            } else if (jsonObj.get(csvHeaders[i]) instanceof Boolean) {
                System.out.println(jsonObj.get(csvHeaders[i]));
                csvContent[i] = String.valueOf(jsonObj.get(csvHeaders[i]));
            } else if (jsonObj.get(csvHeaders[i]) instanceof Double) {
                System.out.println(jsonObj.get(csvHeaders[i]));
                csvContent[i] = String.valueOf(jsonObj.get(csvHeaders[i]));
            }
            else {
                JSONObject innerJsonObj = jsonObj.getJSONObject(csvHeaders[i]);
                System.out.println(csvHeaders[i]);
                System.out.println(innerJsonObj);
                Iterator<Map.Entry<String, Object>> iterator = innerJsonObj.entrySet().iterator();
                csvContent[i] = (String) iterator.next().getValue();
            }
        }*/
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

