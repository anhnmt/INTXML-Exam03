package demo.filter_by_population;

import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class RunSaxFilterByPopulation {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setNamespaceAware(true);
        SAXParser parser = parserFactory.newSAXParser();
        XMLReader parent = parser.getXMLReader();
        XMLFilter filter = new FilterByPopulation();

        var objRead = new SaxRead();

        filter.setParent(parent);
        filter.setContentHandler(objRead);

        filter.parse("src/data/Countries.xml");

        System.out.println("- Total countries: " + FilterByPopulation.count);
    }
}
