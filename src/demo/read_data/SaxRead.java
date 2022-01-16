package demo.read_data;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DecimalFormat;

public class SaxRead extends DefaultHandler {
    private String id;

    private boolean blName;
    private String name;

    private boolean blPosition;
    private String position;

    private boolean blArea;
    private String area;
    private String unit;

    private boolean blPopulation;
    private String population;

    public static String withLargeIntegers(String value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(Double.parseDouble(value));
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("Country")) {
            id = attributes.getValue("id");
        }

        if (qName.equals("Name")) {
            blName = true;
        }

        if (qName.equals("Position")) {
            blPosition = true;
        }

        if (qName.equals("Area")) {
            blArea = true;
            unit = attributes.getValue("unit");
        }

        if (qName.equals("Population")) {
            blPopulation = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);

        if (blName) {
            name = data;
            blName = false;
        }

        if (blPosition) {
            position = data;
            blPosition = false;
        }

        if (blArea) {
            area = withLargeIntegers(data);
            blArea = false;
        }

        if (blPopulation) {
            population = withLargeIntegers(data);

            System.out.println("- Country:");
            System.out.println("\tId: " + id);
            System.out.println("\tName: " + name);
            System.out.println("\tPosition: " + position);
            System.out.println("\tArea: " + area + " " + unit);
            System.out.println("\tPopulation: " + population);

            blPopulation = false;
        }
    }
}
