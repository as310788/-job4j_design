package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().
                getName().
                endsWith(args[1])).
                forEach(System.out::println);
    }

    public static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Здесь должно быть два параметра!");
        }

        if (!Files.exists(Paths.get(args[0]))) {
            throw new IllegalArgumentException("Значение отсутствует в каталоге");
        }
        if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException("Файл не является каталогом");
        }
        if (args[1].startsWith(".") && args[1].length() > 1) {
            throw new IllegalArgumentException("Символ не является расширением");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
