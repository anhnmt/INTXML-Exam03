package demo.filter_by_population_and_area;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public class FilterByPopulationAndArea extends XMLFilterImpl {
    public static boolean blCountry;

    private boolean blArea;
    private String area;

    private boolean blPopulation;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        if (qName.equals("Area")) {
            blArea = true;
        }

        if (qName.equals("Population")) {
            blPopulation = true;
        }

        super.startElement(uri, localName, qName, attrs);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);

        if (blArea) {
            area = data;
            blArea = false;
        }

        if (blPopulation) {
            blCountry = Double.parseDouble(area) > 300_000 && Double.parseDouble(data) > 50_000_000;

            blPopulation = false;
        }

        super.characters(ch, start, length);
    }
}
