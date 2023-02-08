package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> chat = new ArrayList<>();
        Scanner userIn = new Scanner(System.in);
        String userFraze;
        String condition = CONTINUE;
        Random rand = new Random();
        String botFraze = readPhrases().get(rand.nextInt(readPhrases().size()));
        while (!OUT.equals(condition)) {
            if (CONTINUE.equals(condition)) {
                userFraze = userIn.nextLine();
                chat.add(userFraze);
                if (OUT.equals(userFraze) || STOP.equals(userFraze)) {
                    condition = userFraze;
                } else {
                    System.out.println(botFraze);
                    chat.add(botFraze);
                }
            } else {
                userFraze = userIn.nextLine();
                chat.add(userFraze);
                if (CONTINUE.equals(userFraze)) {
                    condition = userFraze;
                    System.out.println(botFraze);
                    chat.add(botFraze);
                } else if (OUT.equals(userFraze)) {
                    condition = userFraze;
                }
            }
        }
        saveLog(chat);
    }

    private List<String> readPhrases() {
        List<String> listOfAnswers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            br.lines().forEach(listOfAnswers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfAnswers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/answer.txt", "./data/botAnswer.txt");
        cc.run();
    }
}