package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "feature")
public class Feature {
    @XmlAttribute
    private String feature;

    public Feature() { }
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
