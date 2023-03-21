package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dp = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), dp);
        dp.getMap().entrySet().stream()
                .filter(s -> s.getValue().size() > 1)
                .forEach(s -> System.out.printf("%s%n%s%n", s.getKey(),
                        s.getValue().stream()
                                .map(Path::toString)
                                .reduce((a, b) -> a + "\n" + b).get()));
    }
}