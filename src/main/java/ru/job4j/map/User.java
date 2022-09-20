package ru.job4j.map;

import java.util.Calendar;

public class User {
    private String name;
    private int data;
    private Calendar birthday;

    public User(String name, int data, Calendar birthday) {
        this.name = name;
        this.data = data;
        this.birthday = birthday;
    }
}
