package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        byte age = 33;
        boolean proger = true;
        char sex = 'M';
        int moneys = 500_000;
        short fine = 30_000;
        long favoriteNumber = 9_999_999_999L;
        float favFloatNumber = 36.6f;
        double height = 180.5;
        LOG.debug("""

                        User info
                        name : {}, age : {}
                        proger : {}, sex : {}
                        moneys : {}, fine : {}
                        favorite float number : {}
                        favorite number : {}, height : {}""",
                name, age, proger, sex, moneys, fine, favFloatNumber, favoriteNumber, height);
    }
}