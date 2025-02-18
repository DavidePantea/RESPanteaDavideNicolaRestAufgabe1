import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List<NinjaEvent> ninjaEvents = readXml("src/ninja_events.xml");
    }

    private static List<NinjaEvent> readXml(String filepath) {
        List<NinjaEvent> ninjaEvents = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Paths.get(filepath).toFile());
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("NinjaEvent");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    NinjaEvent ninjaEvent = new NinjaEvent(
                            Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()),
                            element.getElementsByTagName("Charaktername").item(0).getTextContent(),
                            NinjaRank.valueOf(element.getElementsByTagName("Stufe").item(0).getTextContent()),
                            element.getElementsByTagName("Beschreibung").item(0).getTextContent(),
                            element.getElementsByTagName("Datum").item(0).getTextContent(),
                            Double.parseDouble(element.getElementsByTagName("Kraftpunkte").item(0).getTextContent())
                    );
                    ninjaEvents.add(ninjaEvent);
                }
            }
        } catch (Exception e) {
            System.err.println("Fehler beim Lesen der XML-Datei: " + e.getMessage());
        }
        return ninjaEvents;
    }


}