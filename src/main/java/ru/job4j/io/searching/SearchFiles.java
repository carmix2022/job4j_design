package ru.job4j.io.searching;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;



public class SearchFiles extends SimpleFileVisitor<Path> {

    private Predicate<Path> condition;
    private List<Path> paths = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void writeRsl(String output) throws IOException {
        try (PrintStream ps = new PrintStream(output)) {
            this.getPaths().forEach(ps :: println);
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            paths.add(file);
        }
        return super.visitFile(file, attrs);
    }
}
