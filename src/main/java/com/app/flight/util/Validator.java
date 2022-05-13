package com.app.flight.util;

import java.time.LocalDateTime;

/**
 * @author JiaBoran
 * @version 1.0
 * @date 2022.5.5
 */
public class Validator {

    /**
     * Validate bankId 19 digits
     *
     * @param id bankId
     * @return valid or not
     */
    public static boolean bankIdValidator(String id) {
        if (id.length() == 19) {
            if (isDigit(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validate reservationId 19 digits
     *
     * @param reservationId
     * @return valid or not
     */
    public static boolean reservationIdValidator(String reservationId) {
        if (reservationId.length() == 19) {
            if (isDigit(reservationId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validate boardingGate
     *
     * @param boardingGate
     * @return valid or not
     */
    public static boolean boardingGateValidator(String boardingGate) {
        if (boardingGate.length() == 3) {
            if (boardingGate.charAt(0) >= 65 && boardingGate.charAt(0) <= 68) {
                return isDigit(boardingGate.substring(1, 3));
            }
        }
        return false;
    }

    /**
     * Validate flightId: two uppercase words and four digits
     *
     * @param flightId
     * @return valid or not
     */
    public static boolean flightIdValidator(String flightId) {
        if (flightId.length() == 6) {
            return isUpperCase(flightId.substring(0, 2)) && isDigit(flightId.substring(2, 6));
        }
        return false;
    }

    /**
     * Check whether the char is digit
     *
     * @param string string
     * @return
     */
    public static boolean isDigit(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) < 48 || string.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether the char is UpperCase
     *
     * @param string string
     * @return
     */
    public static boolean isUpperCase(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) < 65 || string.charAt(i) > 90) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validate whether the flight time is within 24h of the current time
     *
     * @return valid or not
     */
    public static boolean timeValidator(LocalDateTime time) {

        return false;
    }

    public static boolean visaIdValidator(String visaId) {
        //TODO:验证银行卡号，自己编写规则
        return true;
    }
}
