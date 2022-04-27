package com.app.flight.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.app.flight.entity.Flight;
import com.app.flight.entity.Passenger;
import com.app.flight.entity.Reservation;
import com.app.flight.service.GetFlight;
import com.app.flight.service.GetReservation;
import com.app.flight.util.Csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Jia Boran
 * @version 1.1
 * @date 2022.4.11
 */
public class GetReservationImpl implements GetReservation {
    private static final String CSV_PATH = "src/main/resources/com/app/flight/data/csv/Reservation.csv";
    private static final String JSON_PATH = "src/main/resources/com/app/flight/data/json/Reservation.json";

    /**
     * 测试读取用户预定信息
     */
    public static void main(String[] args) {
        String id = "220802200005217774";
        GetReservationImpl getReservation = new GetReservationImpl();
        ArrayList<Reservation> reservations = getReservation.lookupReservations(id);
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    /**
     * 通过身份证查找用户预定航班
     *
     * @param passengerId id
     * @return ArrayList<Reservation>
     */
    @Override
    public ArrayList<Reservation> lookupReservations(String passengerId) {
        ArrayList<String[]> csvList = Csv.readCsv(CSV_PATH);
        ArrayList<Reservation> reservations = new ArrayList<>();
        boolean flag = false;
        for (String[] csvData : csvList) {
            if (csvData[1].equals(passengerId)) {
                flag = true;
                String[] reservationData = csvData.clone();
                Reservation reservation = new Reservation();
                reservation.setReservationId(reservationData[0]);

                Passenger passenger = new Passenger();
                passenger.setPassengerId(reservationData[1]);
                String pId = passenger.getPassengerId();
                GetPassengerImpl getPassenger = new GetPassengerImpl();
                passenger = getPassenger.lookupPassengerById(pId);
                reservation.setPassenger(passenger);

                Flight flight = new Flight();
                flight.setFlightId(reservationData[2]);
                String fId = flight.getFlightId();
                GetFlight getFlight = new GetFlightImpl();
                flight = getFlight.lookupFlight(fId);
                reservation.setFlight(flight);
                //ArrayList<Flight> flights = getFlight.lookupFlight(fId);
                //LocalDateTime localDateTime = LocalDateTime.now();
                //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
                //String dateTime = localDateTime.format(dateTimeFormatter);
                //System.out.println(dateTime);
                //LocalDateTime localDateTime = flight.getDepartureTime();

                //LocalDateTime localDateTime = flight.getDepartureTime();
                //LocalDate localDate = localDateTime.toLocalDate();
                //System.out.println("LocalDate 类型输出：" + localDate);
                //Date date = Date.from(flight.getDepartureTime().atZone(ZoneOffset.ofHours(8)).toInstant());
                //if (localDate.equals(LocalDate.now())) {

                reservation.setSeatLevel(Reservation.seatClass.valueOf(reservationData[3]));
                reservation.setMealsAvailable(Boolean.parseBoolean(reservationData[4]));
                reservation.setHandBaggageNum(Integer.parseInt(reservationData[5]));
                reservation.setCheckedBaggageNum(Integer.parseInt(reservationData[6]));
                reservations.add(reservation);
                //System.out.println("数据查找成功");
            }
        }
        if (flag) {
            try (FileWriter reservationJson = new FileWriter(JSON_PATH); PrintWriter out = new PrintWriter(reservationJson)) {
                String reservationString = JSON.toJSONString(reservations, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
                out.write(reservationString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("数据查找成功");
            return reservations;
        } else {
            System.out.println("数据查找失败");
            return null;
        }
    }
}

