package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    public void printDoublicates() {
        map.entrySet().stream()
                .filter(s -> s.getValue().size() > 1)
                .forEach(s -> System.out.printf("%s%n%s%n", s.getKey(),
                        s.getValue().stream()
                                .map(Path::toString)
                                .reduce((a, b) -> a + "\n" + b).get()));
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(attrs.size(), file.getFileName().toString());
        map.putIfAbsent(newFile, new ArrayList<>());
        map.get(newFile).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }
}