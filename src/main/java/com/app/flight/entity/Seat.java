package com.app.flight.entity;

public enum Seat {
    /**
     * Three types of aircraft accommodation
     */
    ECONOMY_CLASS(100, 10), BUSINESS_CLASS(200, 5), FIRST_CLASS(400, 5);

    private int price;
    private int row;

    Seat(int price, int row) {
        this.price = price;
        this.row = row;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
