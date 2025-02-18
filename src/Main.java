import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<NinjaEvent> ninjaEvents = readXml("src/ninja_events.xml");
        selectByNumber(ninjaEvents, 30);
        writeEventCountsToFile(ninjaEvents);
    }

    private static void selectByNumber(List<NinjaEvent> ninjaEvents, Integer givenNumber){
        for (NinjaEvent ninjaEvent : ninjaEvents){
            if (ninjaEvent.getPowerLevel() >= givenNumber){
                System.out.println(ninjaEvent.getCharacterName());
            }
        }

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

    private static void writeEventCountsToFile(List<NinjaEvent> ninjaEvents) {
        Map<String, Integer> eventCount = new HashMap<>();

        // Count occurrences of each diagnosis
        for (NinjaEvent ninjaEvent : ninjaEvents) {
            eventCount.put(String.valueOf(ninjaEvent.getRank()), eventCount.getOrDefault(ninjaEvent.getRank(), 0) + 1);
        }

        // Prepare content for file
        StringBuilder content = new StringBuilder();
        for (Map.Entry<String, Integer> entry : eventCount.entrySet()) {
            content.append(entry.getKey()).append("% ").append(entry.getValue()).append("\n");
        }

        // Write to file
        try {
            Files.write(Path.of("src/gesammtzahl.txt"), content.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben der Datei: " + e.getMessage());
        }
    }


}