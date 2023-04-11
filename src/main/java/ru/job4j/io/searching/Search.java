package ru.job4j.io.searching;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        check(argsName);
        Path start = Path.of(argsName.get("d"));
        SearchFiles searcher = new SearchFiles(condition(argsName));
        Files.walkFileTree(start, searcher);
        searcher.writeRsl(argsName.get("o"));
    }

    public static Predicate<Path> condition(ArgsName argsName) {
        final var name = argsName.get("n");
        return p -> FileSystems.getDefault()
                .getPathMatcher("mask".equals(argsName.get("t"))
                        ? "glob:" + name
                        : "regex:" + name)
                .matches(p.getFileName());
    }

    public static void check(ArgsName argsName) {
        Path origin = Path.of(argsName.get("d"));
        if (!Files.exists(origin)) {
            throw new IllegalArgumentException(String.format("Not exist %s", origin.toAbsolutePath()));
        }
        if (!Files.isDirectory(origin)) {
            throw new IllegalArgumentException(String.format("Not directory %s", origin.toAbsolutePath()));
        }
        Set<String> typeSearch = new HashSet<>(List.of("name", "regex", "mask"));
        if (!typeSearch.contains(argsName.get("t"))) {
            throw new IllegalArgumentException("Incorrect searching type");
        }
        argsName.get("n");
        argsName.get("o");

    }
}