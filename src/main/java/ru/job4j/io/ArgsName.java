package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if ((value) == null) {
            throw new IllegalArgumentException("Параметров нет");
        }
        return value;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Не допустимый параметр");
        }
        Stream.of(args)
                .peek(s -> {
                    if (!s.startsWith("-")
                            || s.startsWith("-=")
                            || s.startsWith("==")) {
                        throw new IllegalArgumentException("Не соответствует шаблону -ключ=значение");
                    }
                })
                .map(el -> el.split("=", 2))
                .forEach(els -> {
                    if (els.length != 2 || els[0].isEmpty() || els[1].isEmpty()) {
                        throw new IllegalArgumentException(
                                "Один из параметров неверный");
                    }
                    values.put(els[0].substring(1), els[1]);
                });
    }

    public static ArgsName of(String[] args) {
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
