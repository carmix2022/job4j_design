package ru.job4j.generics;

public class Tiger extends Predator {
    private int countOfStripes;

    @Override
    public String toString() {
        return "Tiger{"
                + "countOfStripes=" + countOfStripes
                + '}';
    }
}
