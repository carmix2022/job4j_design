package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

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

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(planet));

        final String planetJson =
                "{"
                        + "\"name\":Earth,"
                        + "\"terrestrialPlanet\":true,"
                        + "\"diameter\":12742,"
                        + "\"feature\":"
                        + "{"
                        + "\"feature\":\"the planet with intelligent life\""
                        + "},"
                        + "\"mainSatellites\":"
                        + "[\"Moon\"]"
                        + "}";
        final Planet planetMod = gson.fromJson(planetJson, Planet.class);
        System.out.println(planetMod);
    }
}
