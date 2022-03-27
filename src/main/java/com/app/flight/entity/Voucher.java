package com.app.flight.entity;

/**
 * @author SongBo
 * @version 0.1
 * @date 2022.3.26
 */
public class Voucher {

    private int voucherId;
    private int counterNo;
    private int handBaggageNum;

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public int getCounterNo() {
        return counterNo;
    }

    public void setCounterNo(int counterNo) {
        this.counterNo = counterNo;
    }

    public int getHandBaggageNum() {
        return handBaggageNum;
    }

    public void setHandBaggageNum(int handBaggageNum) {
        this.handBaggageNum = handBaggageNum;
    }
}
