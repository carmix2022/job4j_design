package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    public Map<FileProperty, List<Path>> getMap() {
        return map;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(attrs.size(), file.getFileName().toString());
        List<Path> pathsFile = map.putIfAbsent(newFile, new ArrayList<>(List.of(file.toAbsolutePath())));
        if (pathsFile != null) {
            pathsFile.add(file.toAbsolutePath());
            map.put(newFile, pathsFile);
        }
        return super.visitFile(file, attrs);
    }
}