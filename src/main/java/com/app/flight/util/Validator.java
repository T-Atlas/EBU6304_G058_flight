package com.app.flight.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JiaBoran
 * @version 1.0
 * @date 2022.5.5
 */
public class Validator {
    final static Map<Integer, String> zoneNum = new HashMap<Integer, String>();
    final static int[] PARITY_BIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    final static int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    static {
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "宁夏");
        zoneNum.put(65, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "外国");
    }

    /**
     * Validate passengerId
     *
     * @param id passengerId
     * @return whether valid or not, null and "" are both false
     */
    public static boolean idValidator(String id) {
        if (id == null || (id.length() != 15 && id.length() != 18))
            return false;
        final char[] cs = id.toUpperCase().toCharArray();
        //check bit
        int power = 0;
        for (int i = 0; i < cs.length; i++) {
            if (i == cs.length - 1 && cs[i] == 'X')
                break;//The last digit can be X or x
            if (cs[i] < '0' || cs[i] > '9')
                return false;
            if (i < cs.length - 1) {
                power += (cs[i] - '0') * POWER_LIST[i];
            }
        }

        //check zoneNum
        if (!zoneNum.containsKey(Integer.valueOf(id.substring(0, 2)))) {
            return false;
        }

        //check year
        String year = null;
        year = id.length() == 15 ? getIdCardCalendar(id) : id.substring(6, 10);


        final int iYear = Integer.parseInt(year);
        if (iYear < 1900 || iYear > Calendar.getInstance().get(Calendar.YEAR))
            return false;//pass year before 1900 and after this year

        //check month
        String month = id.length() == 15 ? id.substring(8, 10) : id.substring(10, 12);
        final int iMonth = Integer.parseInt(month);
        if (iMonth < 1 || iMonth > 12) {
            return false;
        }

        //check day
        String day = id.length() == 15 ? id.substring(10, 12) : id.substring(12, 14);
        final int iDay = Integer.parseInt(day);
        if (iDay < 1 || iDay > 31)
            return false;

        //check PARITY_BIT
        if (id.length() == 15)
            return true;
        return cs[cs.length - 1] == PARITY_BIT[power % 11];
    }

    /**
     * @param id passengerId
     * @return year
     */
    private static String getIdCardCalendar(String id) {
        //Get birth year,month, day
        String birthday = id.substring(6, 12);
        SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
        Date birthdate = null;
        try {
            birthdate = ft.parse(birthday);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        Calendar cday = Calendar.getInstance();
        assert birthdate != null;
        cday.setTime(birthdate);
        return String.valueOf(cday.get(Calendar.YEAR));
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
}
