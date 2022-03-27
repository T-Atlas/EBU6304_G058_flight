package com.app.flight.entity;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.3.17
 */
public class Voucher {

    /**
     * Serial number of the voucher
     */
    private int voucherNo;

    /**
     * Counter number for checked baggage
     */
    private int counterNo;

    /**
     * Number of checked baggage
     */
    private int checkedBaggageNum;

    public Voucher() {
    }

    public int getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(int voucherNo) {
        this.voucherNo = voucherNo;
    }

    public int getCounterNo() {
        return counterNo;
    }

    public void setCounterNo(int counterNo) {
        this.counterNo = counterNo;
    }

    public int getCheckedBaggageNum() {
        return checkedBaggageNum;
    }

    public void setCheckedBaggageNum(int checkedBaggageNum) {
        this.checkedBaggageNum = checkedBaggageNum;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "voucherNo=" + voucherNo +
                ", counterNo=" + counterNo +
                ", checkedBaggageNum=" + checkedBaggageNum +
                '}';
    }
}
