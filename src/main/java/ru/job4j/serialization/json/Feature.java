package ru.job4j.serialization.json;

public class Feature {
    private final String feature;

    public Feature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "Feature{"
                + "feature='" + feature + '\''
                + '}';
    }
}
