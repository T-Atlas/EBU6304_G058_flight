package com.app.flight.service.temp;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.app.flight.util.Json;
import lombok.Data;

/**
 * @author Mason
 */
public class BooleanTest {
    public static final String RES_JSON_PATH = "src/main/resources/com/app/flight/data/json/Res.json";
    Res reservation;

    public static void main(String[] args) {
        BooleanTest booleanTest = new BooleanTest();
        booleanTest.reservation();
    }

    public void reservation() {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        String id = snowflake.nextIdStr();
        reservation = new Res();
        reservation.setReservationId(id);
        reservation.setCheckedBaggageNum(0);
        reservation.setHandBaggageNum(1);
        reservation.setMealsAvailable(true);
        String s = JSON.toJSONString(reservation);
        System.out.println(s);
        Json.writeJson(RES_JSON_PATH, reservation);
    }

    @Data
    public static class Res {
        /**
         * The unique identification of particular reservation.
         */
        @JSONField(ordinal = 1)
        private String reservationId;

        /**
         * Determine whether meals are included in the scheduled flight.
         */
        @JSONField(ordinal = 2)
        private boolean mealsAvailable;

        /**
         * Number of passengers' hand baggage.
         */
        @JSONField(ordinal = 3)
        private int handBaggageNum;

        /**
         * Number of passengers' checked baggage.
         */
        @JSONField(ordinal = 4)
        private int checkedBaggageNum;

    }
}
