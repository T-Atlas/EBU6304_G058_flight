package com.app.flight.util;

import com.csvreader.CsvWriter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author SongBo
 */
public class Seat {
    public static void generateSeatMap(String flightId, int columnSize) {
        ArrayList<String[]> csvList = Csv.readCsv("src/main/resources/com/app/flight/data/csv/Flight.csv");
        for (String[] csvData : csvList) {
            if (csvData[0].equals(flightId)) {
                String[] flightData = csvData.clone();
                String[] date = flightData[5].split(" ");
                String filePath = "src/main/resources/com/app/flight/data/csv/flightSeat/" + flightData[0] + "_" + date[0] + ".csv";
                Map<String, Boolean> rowMap = new HashMap<>(6);
                String[] rowString = new String[]{"A", "B", "C", "D", "E", "F"};
                for (String s : rowString) {
                    rowMap.put(s, true);
                }
                Map<Integer, Map<String, Boolean>> seatMap = new HashMap<>(columnSize);
                for (int i = 1; i < columnSize + 1; i++) {
                    seatMap.put(i, rowMap);
                }
                try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
                    CsvWriter csvWriter = new CsvWriter(bufferedWriter, ',');
                    csvWriter.writeRecord(rowString);
                    for (Map<String, Boolean> rowSeat : seatMap.values()) {
                        Iterator<Map.Entry<String, Boolean>> iterator = rowSeat.entrySet().iterator();
                        String[] seatStatus = new String[rowSeat.size()];
                        for (int i = 0; i < rowSeat.size(); i++) {
                            seatStatus[i] = String.valueOf(iterator.next().getValue());
                        }
                        csvWriter.writeRecord(seatStatus);
                    }
                    csvWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateSeatMap(String flightId, String row, int column) {

    }
}
