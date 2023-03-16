package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = null;
        try (BufferedReader buff = new BufferedReader(new FileReader("data/log.txt"))) {
            rsl = buff.lines()
                    .map(s -> s.split(" "))
                    .filter(k -> "404".equals(k[k.length - 2]))
                    .map(l -> String.join(" ", l))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        System.out.println(log);

    }
}