package com.app.flight.entity;

import java.util.ArrayList;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.17
 */
public class Passenger {
    private String firstName;
    private String lastName;
    private int idNumber;
    private int age;
    private final ArrayList<Flight> flights = new ArrayList<>();

    public Passenger() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber=" + idNumber +
                ", age=" + age +
                ", flights=" + flights +
                '}';
    }
}
