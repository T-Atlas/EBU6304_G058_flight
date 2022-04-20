package com.app.flight.util;

/**
 * @author zhenghan
 */
public class DataParser {

    public static String noToString(int no) {
        return switch (no) {
            case 1 -> "A";
            case 2 -> "B";
            case 3 -> "C";
            case 5 -> "D";
            case 6 -> "E";
            case 7 -> "F";
            default -> "";
        };
    }


    public static int stringToNo(String s) {
        return switch (s) {
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            case "D" -> 5;
            case "E" -> 6;
            case "F" -> 7;
            default -> 0;
        };
    }
}
