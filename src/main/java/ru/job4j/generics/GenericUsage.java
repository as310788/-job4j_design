package ru.job4j.generics;

import java.util.*;
import java.util.Date;

public class GenericUsage {
    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
    public void printInfo(Collection<? extends Person> col) {
        for (Iterator<? extends Person> it = col.iterator(); it.hasNext();) {
            Person next = it.next();
            System.out.println(next);
        }
    }
    public void addAll(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        for (Object o : list) {
            System.out.println("Текущий элемент: " + o);
        }
    }
    public static void main(String[] args) {
        List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
        new GenericUsage().printInfo(per);
        List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913716000000L)));
        new GenericUsage().printInfo(pro);
        List<Integer> l = List.of(1, 2, 3, 4, 5);
        new GenericUsage().printRsl(l);
        List<? super Integer> list = new ArrayList<>();
        new GenericUsage().addAll(list);
    }
}