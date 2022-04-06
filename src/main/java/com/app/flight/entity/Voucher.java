package com.app.flight.entity;

import lombok.Data;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.3.27
 */
@Data
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

}
