package com.app.flight.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.app.flight.entity.Flight;
import com.app.flight.service.GetFlight;
import com.app.flight.util.Csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author 贾博然
 * @version 1.0
 * @date 2022.4.11
 */
public class GetFlightImpl implements GetFlight {
    private static final String CSV_PATH = "src/main/resources/com/app/flight/data/csv/Flight.csv";
    private static final String JSON_PATH = "src/main/resources/com/app/flight/data/json/Flight.json";


    /**
     * 通过航班号查找航班信息
     * @param id
     * @return
     */
    @Override
    public Flight lookupFlight(String id){
        ArrayList<String[]> csvList = Csv.readCsv(CSV_PATH);
        Flight flight = new Flight();
        //ArrayList<Flight> flights = new ArrayList<>();
        boolean flag = false;
        for (String[] csvData : csvList){
            if (csvData[0].equals(id)){
                flag = true;
                String[] flightData = csvData.clone();
                //Flight flight = new Flight();
                flight.setFlightId(flightData[0]);
                flight.setDeparture(flightData[1]);
                flight.setDestination(flightData[2]);
                flight.setBoardingGate(flightData[3]);

                String strLocalDate1 = flightData[4];
                DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String str1 = strLocalDate1;
                LocalDateTime localDateTime1 = LocalDateTime.parse(str1, fmt1);

                /*LocalDateTime start = LocalDateTime.now();
                LocalDateTime end = localDateTime1;
                Duration duration = Duration.between(start,end);
                long hours = duration.toHours();*/
                //if (hours <= 24){
                flight.setBoardingTime(localDateTime1);

                String strLocalDate2 = flightData[5];
                DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String str2 = strLocalDate2;
                LocalDateTime localDateTime2 = LocalDateTime.parse(str2, fmt2);
                flight.setDepartureTime(localDateTime2);

                String strLocalDate3 = flightData[6];
                DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String str3 = strLocalDate3;
                LocalDateTime localDateTime3 = LocalDateTime.parse(str3, fmt3);
                flight.setArrivalTime(localDateTime3);

                //flights.add(flight);
                System.out.println("数据查找成功");
                //}
                /* {
                    System.out.println("There is no flights in 24h");
                    return null;
                }*/
            }
        }
        if (flag){
            try (FileWriter flightJson = new FileWriter(JSON_PATH); PrintWriter out = new PrintWriter(flightJson)) {
                String flightString = JSON.toJSONString(flight, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
                out.write(flightString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return flight;
        }
        else {
            System.out.println("数据查找失败");
            return null;
        }
    }

    public static void main(String[] args){
        String id = "MU1122";
        GetFlightImpl getFlight = new GetFlightImpl();
        //ArrayList<Flight> flights = getFlight.lookupFlight(id);
        /*for (Flight flight : flights) {
            System.out.println(flight);
        }*/
        Flight flight = getFlight.lookupFlight(id);
        System.out.println(flight);
    }
}
