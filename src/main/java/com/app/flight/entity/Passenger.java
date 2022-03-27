package com.app.flight.entity;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.3.17
 */
public class Passenger {

    /**
     * The unique identification of particular passenger.
     */
    private int passengerId;

    /**
     * The first name of passenger.
     */
    private String firstName;

    /**
     * The last name of passenger.
     */
    private String lastName;

    /**
     * The age of passenger.
     */
    private int age;

    /**
     * The telephone of passenger.
     */
    private String telephone;

    public Passenger() {
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
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

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
