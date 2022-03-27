package com.app.flight.entity;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.17
 */
public class Tag {

    private int tagNo;
    private int checkedBaggageNum;
    private int baggageId;
    private double weight;

    public int getTagNo() {
        return tagNo;
    }

    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    public int getCheckedBaggageNum() {
        return checkedBaggageNum;
    }

    public void setCheckedBaggageNum(int checkedBaggageNum) {
        this.checkedBaggageNum = checkedBaggageNum;
    }

    public int getBaggageId() {
        return baggageId;
    }

    public void setBaggageId(int baggageId) {
        this.baggageId = baggageId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
