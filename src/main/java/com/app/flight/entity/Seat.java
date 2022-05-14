package com.app.flight.entity;

public enum Seat {
    /**
     * Three types of aircraft accommodation
     */
    ECONOMY_CLASS(100, 15), BUSINESS_CLASS(200, 3), FIRST_CLASS(400, 2);

    private double price;
    private int row;

    Seat(double price, int row) {
        this.price = price;
        this.row = row;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
