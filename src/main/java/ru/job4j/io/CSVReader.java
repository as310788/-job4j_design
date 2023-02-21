package ru.job4j.io;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static List<Integer> filter(List<String> list) {
        List<Integer> result = new ArrayList<>();
        for (String s : list) {
            if ("name".equals(s)) {
                result.add(0);
            }
            if ("age".equals(s)) {
                result.add(1);
            }
            if ("last_name".equals(s)) {
                result.add(2);
            }
            if ("education".equals(s)) {
                result.add(3);
            }
        }
        return result;
    }

    public static void handle(ArgsName argsName) throws Exception {
        List<String> filter = List.of(argsName.get("filter").split(","));
        List<Integer> result = filter(filter);
        String delimiter = argsName.get("delimiter");
        try (Scanner scanner =
                     new Scanner(new File(argsName.get("path")));
             PrintWriter out = new PrintWriter(new FileOutputStream(argsName.get("out")))) {
            while (scanner.hasNext()) {
                String[] temp = scanner.nextLine().split(delimiter);
                String s = "";
                for (int i = 0; i < result.size(); i++) {
                    if (i == result.size() - 1) {
                        s = s + temp[result.get(i)];
                        break;
                    }
                    s = s + temp[result.get(i)] + ";";
                }
                writer(s, argsName, out);
            }
        }
    }

    public static void writer(String s, ArgsName argsName, PrintWriter out) {
        String value = argsName.get("out");
        if ("stdout".equals(value)) {
            System.out.println(s);
        } else {
            out.println(s);
        }
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

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("args.length != 4");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}