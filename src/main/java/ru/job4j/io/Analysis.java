package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
        PrintWriter writer = new PrintWriter(target)) {
            List<String[]> list = reader.lines()
                    .map(s -> s.split(" "))
                    .toList();
            boolean server = true;
            for (String[] s : list) {
                if (server && ("400".equals(s[0])  || "500".equals(s[0]))) {
                    server = false;
                    writer.printf("%s;", s[1]);
                }
                if (!server && ("200".equals(s[0])  || "300".equals(s[0]))) {
                    server = true;
                    writer.printf("%s;%n", s[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}