package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.Arrays;

@XmlRootElement(name = "planet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Planet {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean terrestrialPlanet;
    @XmlAttribute
    private int diameter;
    private Feature feature;
    @XmlElementWrapper(name = "mainSatellites")
    @XmlElement(name = "satellite")
    private String[] mainSatellites;

    public Planet() { }
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

    public static void main(String[] args) throws Exception {
        final Planet planet = new Planet("Jupiter", false, 142_000,
                new Feature("the largest planet"), new String[] {"Io", "Ganymede", "Europa", "Kallysto"});

        JAXBContext context = JAXBContext.newInstance(Planet.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(planet, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Planet result = (Planet) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
