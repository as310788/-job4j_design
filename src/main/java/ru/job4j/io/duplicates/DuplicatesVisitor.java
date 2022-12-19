package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    public List<Path> getDuplicates() {
        List<Path> duplicatesList = new ArrayList<>();
        map.values()
                .stream()
                .filter(paths -> paths.size() > 1)
                .forEach(duplicatesList::addAll);
        return duplicatesList;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (map.containsKey(fileProperty)) {
            map.get(fileProperty).add(file);
        } else {
            map.put(fileProperty, new ArrayList<>(List.of(file)));
        }
        return super.visitFile(file, attrs);
    }
}