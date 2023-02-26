package ru.job4j.generics;

public class Predator extends Animal {
    int countOfTeeth;

    @Override
    public String toString() {
        return "Predator{"
                + "countOfTeeth=" + countOfTeeth
                + '}';
    }
}
