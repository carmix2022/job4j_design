package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(ArgsName argsName, List<File> list) {
        File target = new File(argsName.get("o"));
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            int lengthRootPath = Path.of(argsName.get("d")).getNameCount();
            for (File file : list) {
                String relativePath = file.toPath().subpath(lengthRootPath - 1, file.toPath().getNameCount())
                        .toString();
                zip.putNextEntry(new ZipEntry(relativePath));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
                zip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void check(ArgsName argsName) {
        Path origin = Path.of(argsName.get("d"));
        if (!Files.exists(origin)) {
            throw new IllegalArgumentException(String.format("Not exist %s", origin.toAbsolutePath()));
        }
        String extension = argsName.get("e");
        if (!extension.matches("\\.\\w+")) {
            throw new IllegalArgumentException("Incorrect file extension");
        }
        String target = argsName.get("o");
        if (!target.matches(".+\\.zip$")) {
            throw new IllegalArgumentException("Incorrect target name format");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        check(argsName);
        Predicate<Path> condition = p -> !p.toFile().getName().endsWith(argsName.get("e"));
        List<File> list = Search.search(Path.of(argsName.get("d")), condition).stream()
                .map(Path::toFile)
                .toList();
        Zip zip = new Zip();
        zip.packFiles(argsName, list);
    }
}