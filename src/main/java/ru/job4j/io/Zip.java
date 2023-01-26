package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Exception validation(String[] args) throws IllegalArgumentException {
        if (!args[0].contains("=")) {
            throw new IllegalArgumentException("Первый аргумент не существует " + args[0]);
        }
        if (!args[1].contains("=")) {
            throw new IllegalArgumentException("Второй аргумент не существует " + args[1]);
        }
        if (!args[2].contains("=")) {
            throw new IllegalArgumentException("Третий аргумент не существует " + args[2]);
        }
        if (!Paths.get(args[0].substring(args[0].indexOf("=") + 1)).toFile().exists()) {
            throw new IllegalArgumentException("Корневая папка не существует " + args[0]);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        validation(args);
        ArgsName argsName = ArgsName.of(args);
        Path mainSource = Paths.get(argsName.get("d"));
        List<Path> sources = Search.search(mainSource, p -> !p.toFile().getName().endsWith(argsName.get("e")));
        packFiles(
                sources,
                new File(argsName.get("o"))
        );
    }
}