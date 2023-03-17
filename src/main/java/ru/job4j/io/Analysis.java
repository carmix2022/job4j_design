package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
        PrintWriter writer = new PrintWriter(target)) {
            boolean server = true;
            String s;
            while ((s = reader.readLine()) != null) {
                String[] ss = s.split(" ");
                if (server && ("400".equals(ss[0])  || "500".equals(ss[0]))) {
                    server = false;
                    writer.printf("%s;", ss[1]);
                }
                if (!server && ("200".equals(ss[0])  || "300".equals(ss[0]))) {
                    server = true;
                    writer.printf("%s;%n", ss[1]);
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