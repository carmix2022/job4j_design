package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String delimiter = argsName.get("delimiter");
        String output = argsName.get("out");
        Path origin = Path.of(argsName.get("path"));
        List<String> filter = Arrays.asList(argsName.get("filter").split(","));
        try (Scanner scanner = new Scanner(origin);
             PrintStream ps = "stdout".equals(output) ? System.out : new PrintStream(output)) {
            List<Integer> indices = new ArrayList<>();
            StringJoiner rsl = new StringJoiner(System.lineSeparator());
            boolean firstLine = true;
            while (scanner.hasNextLine()) {
                List<String> line = Arrays.asList(scanner.nextLine().split(delimiter));
                if (firstLine) {
                    indices = filter.stream()
                            .map(line::indexOf)
                            .toList();
                    firstLine = false;
                }
                String rslString = indices.stream()
                        .map(line::get)
                        .collect(Collectors.joining(delimiter));
                rsl.add(rslString);
            }
            ps.println(rsl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void check(ArgsName argsName) {
        Path origin = Path.of(argsName.get("path"));
        if (!Files.exists(origin)) {
            throw new IllegalArgumentException(String.format("Not exist %s", origin.toAbsolutePath()));
        }
        String delimeter = argsName.get("delimiter");
        Set<String> setDelim = new HashSet<>(List.of(";", " ", ","));
        if (!setDelim.contains(delimeter)) {
            throw new IllegalArgumentException("Incorrect delimiter");
        }
        argsName.get("out");
        argsName.get("filter");
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        check(argsName);
        handle(argsName);
    }
}
