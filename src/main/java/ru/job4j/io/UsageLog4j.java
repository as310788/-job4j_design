package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        double kg = 100.55;
        boolean black = true;
        char category = 'A';
        float height = 180.6f;
        byte legs = 45;
        short number = 5687;
        long phone = 999999999;
        LOG.debug("User info name : {}, age : {}, kg : {}, black : {}"
                       + ", category : {}, height : {}, legs : {}, number : {}"
                + "phone : {}",
                name, age, kg, black, category, height, legs, number, phone);
    }
}