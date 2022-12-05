package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileWriter(target))) {
            boolean result = true;
            while (in.ready()) {
                String str = in.readLine();
                String[] items = str.split("\\s");
                String status = items[0];
                String data = items[1];
                if (("400".contains(status) || "500".contains(status)) && result) {
                    out.print(data + ";");
                    result = false;
                }
                if (("200".contains(status) || "300".contains(status)) && !result) {
                    out.println(data + ";");
                    result = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public static void main(String[] args) {
    Analysis analysis = new Analysis();
    analysis.unavailable("source.txt", "target.txt");
}
}