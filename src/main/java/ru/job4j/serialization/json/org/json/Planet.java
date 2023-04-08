package ru.job4j.serialization.json.org.json;

import org.json.JSONObject;
import java.util.*;

public class Planet {
    private final String name;
    private final boolean terrestrialPlanet;
    private final int diameter;
    private final Feature feature;
    private final String[] mainSatellites;

    public Planet(String name, boolean terrestrialPlanet, int diameter, Feature feature, String[] mainSatellites) {
        this.name = name;
        this.terrestrialPlanet = terrestrialPlanet;
        this.diameter = diameter;
        this.feature = feature;
        this.mainSatellites = mainSatellites;
    }

    public String getName() {
        return name;
    }

    public boolean isTerrestrialPlanet() {
        return terrestrialPlanet;
    }

    public int getDiameter() {
        return diameter;
    }

    public Feature getFeature() {
        return feature;
    }

    public String[] getMainSatellites() {
        return mainSatellites;
    }

    @Override
    public String toString() {
        return "Planet{"
                + "name=" + name
                + ", terrestrialPlanet=" + terrestrialPlanet
                + ", diameter=" + diameter
                + ", feature=" + feature
                + ", mainSatellites=" + Arrays.toString(mainSatellites)
                + '}';
    }

    public static void main(String[] args) {
        final Planet planet = new Planet("Jupiter", false, 142_984,
                new Feature("the largest planet"), new String[] {"Io", "Ganymede", "Europa", "Kallysto"});

        JSONObject jsonFeature = new JSONObject(planet.getFeature());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", planet.getName());
        jsonObject.put("terrestrialPlanet", planet.isTerrestrialPlanet());
        jsonObject.put("diameter", planet.getDiameter());
        jsonObject.put("feature", jsonFeature);
        jsonObject.put("mainSatellites", planet.getMainSatellites());

        System.out.println(jsonObject);
        System.out.println(new JSONObject(planet));
    }
}
