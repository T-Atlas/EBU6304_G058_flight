package com.app.flight.service.temp;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author Mason
 */
public class OrdinalTest {
    public static void main(String[] args) {
        ordinalTest();
    }

    public static void ordinalTest() {
        People people = new People();
        people.setAge(20);
        people.setAdult(true);
        people.setWeight(145.5);
        people.setName("MASON");
        people.setHeight(185.5f);
        String peopleStr = JSON.toJSONString(people);
        System.out.println(peopleStr);
    }

    @Data
    public static class People {

        @JSONField(ordinal = 5)
        public double weight;

        @JSONField(ordinal = 4)
        public boolean adult;

        @JSONField(ordinal = 3)
        public int age;

        @JSONField(ordinal = 2)
        public String name;

        @JSONField(ordinal = 1)
        public float height;

    }
}
