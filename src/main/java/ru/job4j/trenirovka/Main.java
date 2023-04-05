package ru.job4j.trenirovka;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        List.of(1, 2, 3, 4).stream()
                .dropWhile(v -> v <= 300 && v <= 0)
                .map(v -> "Результат: " + v)
                .forEach(System.out::println);
    }
}
