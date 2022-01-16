package demo.filter_by_population;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public class FilterByPopulation extends XMLFilterImpl {
    public static int count;
    public static boolean blCountry;
    private boolean blPopulation;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        if (qName.equals("Population")) {
            blPopulation = true;
        }
        super.startElement(uri, localName, qName, attrs);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);

        if (blPopulation) {
            blCountry = Double.parseDouble(data) > 50_000_000;
            if (blCountry) {
                count++;
            }

            blPopulation = false;
        }
        super.characters(ch, start, length);
    }
}
