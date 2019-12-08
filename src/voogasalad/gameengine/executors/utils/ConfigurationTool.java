package voogasalad.gameengine.executors.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ConfigurationTool {
    public static Element convertNodeToElement(Node node) {
        Element element = null;
        if (node.getNodeType()==Node.ELEMENT_NODE) {
            element = (Element) node;
        }
        return element;
    }

    public static Document configureWithTestDocument(String testDocumentPath) throws GameEngineException {
        File testFile = new File(testDocumentPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder ;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(testFile);
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new GameEngineException(e, "ConfigurationFailedXML");
        }
    }
}
