package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        System.out.println("Поиск дубликатов файлов...");
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
        List<Path> list = duplicatesVisitor.getDuplicates();
        if (list.isEmpty()) {
            System.out.println("Дубликаты не найдены");
        } else {
            System.out.println("Найдены следующие дубликаты:");
            list.forEach(System.out::println);
        }
    }
}