package ru.job4j.io.searching;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Stream.of(args).filter(this::check)
                .map(s -> s.substring(1).split("=", 2))
                .forEach(s -> values.put(s[0], s[1]));
    }

    private boolean check(String s) {
        if (s.matches("^-=.+")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", s));
        }
        if (s.matches("[^=]+=$")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", s));
        }
        if (!s.contains("=")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", s));
        }
        if (!s.matches("^-.+")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", s));
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}