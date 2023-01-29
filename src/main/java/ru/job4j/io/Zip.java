package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Zip {
    private static Path directory;
    private static String exclude;
    private static Path output;


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

    private static List<File> searching(String[] args) throws IOException {
        Search search = new Search();
        return search.search(Paths.get(ArgsName.of(args).get("d")),
                        f -> !f.toFile()
                                .getName()
                                .endsWith(ArgsName.of(args).get("e")))
                .stream()
                .map(p -> p.toFile())
                .collect(Collectors.toList());
    }

    private static void validation(String[] args)throws IllegalArgumentException {
        ArgsName argsName = ArgsName.of(args);
        String d = argsName.get("d");
        String e = argsName.get("e");
        String o = argsName.get("o");
        directory = Path.of(d);
        if (!Files.exists(directory)) {
            throw new IllegalArgumentException("Каталог не существует");
        }
        if (!e.contains(".") || e.endsWith(".") || !o.endsWith(".zip")) {
            throw new IllegalArgumentException("Недопустимый формат файла");
        }
        exclude = e.substring(e.indexOf("."));
        output = Path.of(o);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Недостаточно аргументов");
        }
        List<File> list;
        try {
            validation(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = searching(args);
        list.forEach(System.out::println);
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}