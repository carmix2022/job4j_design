package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static int[][] multiple(int size) {
        int[][] rsl = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rsl[i][j] = (i + 1) * (j + 1);
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult2.txt")) {
            int[][] array = multiple(9);
            for (int[] i : array) {
                for (int j : i) {
                    out.write((j + "\t").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
