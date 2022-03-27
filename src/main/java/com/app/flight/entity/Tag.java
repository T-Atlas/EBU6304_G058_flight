package com.app.flight.entity;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.3.27
 */
public class Tag {

    /**
     * Serial number of the tag
     */
    private int tagNo;

    /**
     * Number of hand baggage
     */
    private int handBaggageNum;

    /**
     * Serial number of hand baggage
     */
    private int baggageNo;

    public Tag() {
    }

    public int getTagNo() {
        return tagNo;
    }

    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    public int getHandBaggageNum() {
        return handBaggageNum;
    }

    public void setHandBaggageNum(int handBaggageNum) {
        this.handBaggageNum = handBaggageNum;
    }

    public int getBaggageNo() {
        return baggageNo;
    }

    public void setBaggageNo(int baggageNo) {
        this.baggageNo = baggageNo;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagNo=" + tagNo +
                ", handBaggageNum=" + handBaggageNum +
                ", baggageNo=" + baggageNo +
                '}';
    }
}
