package ru.job4j.serialization.json.org.json;

public class Feature {

    private final String feature;

    public Feature(String feature) {
        this.feature = feature;
    }

    public String getFeature() {
        return feature;
    }

    @Override
    public String toString() {
        return "Feature{"
                + "feature='" + feature + '\''
                + '}';
    }
}
