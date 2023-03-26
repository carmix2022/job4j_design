package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private final ArgsName argsZip;
    private final List<File> list;

    public Zip(String[] args) throws IOException {
        argsZip = ArgsName.of(args);
        list = Search.search(Path.of(argsZip.get("d")),
                        p -> !p.toFile().getName().endsWith(argsZip.get("e"))).stream()
                .map(Path::toFile)
                .toList();
    }

    public void packFiles() {
        File target = new File(argsZip.get("o"));
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : list) {
                packSingleFile(zip, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void check(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }
        Path origin = Path.of(args[0].split("=", 2)[1]);
        if (!Files.exists(origin)) {
            throw new IllegalArgumentException(String.format("Not exist %s", origin.toAbsolutePath()));
        }
    }

    public void packSingleFile(ZipOutputStream zip, File source) throws IOException {
        int lengthRootPath = Path.of(argsZip.get("d")).getNameCount();
        String relativePath = source.toPath().subpath(lengthRootPath - 1, source.toPath().getNameCount()).toString();
        zip.putNextEntry(new ZipEntry(relativePath));
        try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
            zip.write(out.readAllBytes());
        }
        zip.closeEntry();
    }

    public static void main(String[] args) throws IOException {
        check(args);
        Zip zip = new Zip(args);
        zip.packFiles();
    }
}