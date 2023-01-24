package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if ((value) == null) {
            throw new IllegalArgumentException("Параметров нет");
        }
        return value;
    }

    private void validate(String arg) {
        String[] args = arg.split("=", 2);
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("Не соответствует шаблону -ключ=значение");
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("Не соответствует шаблону -ключ=значение");
        }
        if (args[0].substring(1).isEmpty()) {
            throw new IllegalArgumentException("Не соответствует шаблону -ключ=значение");
        }
        if (args[1].isEmpty()) {
            throw new IllegalArgumentException("Не соответствует шаблону -ключ=значение");
        }
    }

    private void parse(String[] args) {
        for (String arg : args) {
            validate(arg);
            String[] lines = arg.split("=", 2);
            values.put(lines[0].substring(1), lines[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Не допустимый параметр!!!");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}