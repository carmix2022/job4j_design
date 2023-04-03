package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        boolean stop = false;
        boolean exit = false;
        while (!exit) {
            String s = scanner.nextLine();
            log.add(s);
            if (OUT.equals(s)) {
                exit = true;
                stop = true;
            }
            if (STOP.equals(s)) {
                stop = true;
            }
            if (CONTINUE.equals(s)) {
                stop = false;
            }
            if (!stop) {
                int i = rand.nextInt(answers.size());
                String answr = answers.get(i);
                System.out.println(answr);
                log.add(answr);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            rsl = br.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/log_1.txt", "data/answer.txt");
        cc.run();
    }
}