package demo.filter_by_population_and_area;

import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class RunSaxFilterByPopulationAndArea {
    public static void main(String[] args) throws SAXException, IOException {
        XMLReader parent = XMLReaderFactory.createXMLReader();
        XMLFilter filter = new FilterByPopulationAndArea();

        var objRead = new SaxRead();

        filter.setParent(parent);
        filter.setContentHandler(objRead);

        filter.parse("src/data/Countries.xml");
    }
}
