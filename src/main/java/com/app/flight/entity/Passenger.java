package com.app.flight.entity;

import java.util.ArrayList;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.17
 */
public class Passenger {
    /**
     * The first name of passenger.
     */
    private String firstName;

    /**
     * The last name of passenger.
     */
    private String lastName;

    /**
     * The unique identification of particular passenger.
     */
    private int passengerId;

    /**
     * The age of passenger.
     */
    private int age;
    private String telephone;

    public Passenger(int passengerId) {
        this.passengerId = passengerId;
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

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
