package voogasalad.gameauthoringenvironment.gui.XMLcreation;

import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

public class FileCreator {
    public static final String SAVE_PATH = "/Users/sumervardhan/Desktop/Backup October 2019/DesktopBackup/Fall 2019/CS308/testing.xml";
    public static final String LEVEL_PROPERTIES = "resources.gae.XMLCreatorProperties.LevelAttributes";
    public static final String SPRITE_PROTOTYPE_PROPERTIES = "resources.gae.XMLCreatorProperties.SpritePrototypeAttributes";
    public static final String LEVEL_TAG = "Level";
    private static final String SPRITE_PROTOTYPE_TAG = "SpritePrototype";
    private String[] tag_types = {LEVEL_PROPERTIES, SPRITE_PROTOTYPE_PROPERTIES};
    private String[] tag_names = {LEVEL_TAG, SPRITE_PROTOTYPE_TAG};

    private DocumentBuilder myBuilder;
    private Document myDocument;
    private String myFileSavePath;

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        FileCreator f = new FileCreator("s");
        f.createLevelElement();
        f.createSpritePrototypeElement();
        f.printForTesting();
    }

    public FileCreator(String savePath) throws ParserConfigurationException {
        this.myFileSavePath = savePath;
        myBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        myDocument = myBuilder.newDocument();
        myDocument.appendChild(myDocument.createElement("GameConfiguration"));
    }

    public void createLevelElement(){
        createElementByTagType(LEVEL_TAG, LEVEL_PROPERTIES);
    }

    public void createSpritePrototypeElement(){
        createElementByTagType(SPRITE_PROTOTYPE_TAG, SPRITE_PROTOTYPE_PROPERTIES);
    }

    private void createElementByTagType(String tagName, String propertiesPath){
        ResourceBundle levelResources = ResourceBundle.getBundle(propertiesPath);
        HashMap<String, String> outerMap = new HashMap<>();
        HashMap<String, String> innerMap = new HashMap<>();
        List<Element> nestedElements = new ArrayList<>();
        for (String att : levelResources.keySet()) {
            String[] nestedAttributes = levelResources.getString(att).split(":");
            if (nestedAttributes.length == 0) {
                outerMap.put(att, "");
            }
            else {
                for (String nestedAtt : nestedAttributes) {
                    innerMap.put(nestedAtt, "");
                }
                nestedElements.add(createNewElement(att, innerMap));
                innerMap.clear();
            }
        }
        Element element = createNewElement(tagName, outerMap);
        for (Element e : nestedElements) {
            element.appendChild(e);
        }
    }


    private Element createNewElement(String name, Map<String, String> attributeMap){
        Element element = myDocument.createElement(name);
        myDocument.getDocumentElement().appendChild(element);
        for(String att : attributeMap.keySet()){
            Element toAdd = myDocument.createElement(att);
            toAdd.setTextContent("Testing");
            element.appendChild(toAdd);
        }
        return element;
    }

    private void printForTesting() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(myDocument);
        StreamResult streamResult = new StreamResult(new File(SAVE_PATH));
        transformer.transform(domSource, streamResult);
    }
}
