package ru.job4j.io;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(s -> !s.startsWith("#") && !s.isEmpty())
                    .map(s -> s.split("=", 2))
                    .filter(this::check)
                    .forEach(s -> values.put(s[0], s[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean check(String[] s) {
        if (s.length != 2 || s[0].isBlank() || s[1].isBlank()) {
            throw new IllegalArgumentException("file contains a key=value pattern violation");
        }
        return true;
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        //System.out.println(new Config("data/app.properties"));

        Config conf = new Config("data/app.properties");
        conf.load();
        String s = "rr";
        //Arrays.toString(s.split("="));
        System.out.println(Arrays.toString(s.split("=", 2)));
        System.out.println(s.length());
        System.out.println((s.split("=", 2)).length);
        System.out.println((s.split("=", 2))[0]);
    }

}