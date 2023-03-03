package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        var file = Path.of(argsName.get("path")).toFile();
        var target = "stdout".equals(argsName.get("out"))
                ? Path.of("plug.csv")
                : Path.of(argsName.get("out"));
        Integer[] indexOf = getIndexes(argsName, file);
        try (PrintWriter print = new PrintWriter(target.toFile());
             Scanner scanner = new Scanner(file)) {
            StringBuilder string = new StringBuilder();
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                String[] spl = s.split(argsName.get("delimiter"));
                String[] inOrder = new String[spl.length];
                for (int i = 0; i < spl.length; i++) {
                    if (indexOf[i] >= 0) {
                        inOrder[indexOf[i]] = spl[i];
                    }
                }
                for (var str : inOrder) {
                    if (str != null) {
                        string.append(str).append(";");
                    }
                }
                String toPrint = string.substring(0, string.length() - 1);
                if (Path.of("plug.csv").equals(target)) {
                    System.out.println(toPrint);
                } else {
                    print.println(toPrint);
                }
                string = new StringBuilder();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Integer[] getIndexes(ArgsName argsName, File file) {
        Integer[] rsl = new Integer[0];
        try (Scanner scanner = new Scanner(file)) {
            String[] fields = scanner.nextLine().split(argsName.get("delimiter"));
            List<String> filters = List.of(argsName.get("filter").split(","));
            Integer[] indexes = new Integer[fields.length];
            for (int i = 0; i < fields.length; i++) {
                if (filters.contains(fields[i])) {
                    indexes[i] = filters.indexOf(fields[i]);
                } else {
                    indexes[i] = -1;
                }
            }
            rsl = indexes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void validate(ArgsName argsName) {
        if (!Paths.get(argsName.get("path")).toFile().exists()) {
            throw new IllegalArgumentException("The file does not exist");
        }
        if (argsName.get("filter").isBlank()) {
            throw new IllegalArgumentException("Filter doesn't exist");
        }
        if (!";".equals(argsName.get("delimiter")) || argsName.get("delimiter").isBlank()) {
            throw new IllegalArgumentException("Wrong delimiter");
        }
        if (!"stdout".equals(argsName.get("out")) && argsName.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong out parameter");
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("args.length != 4");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}