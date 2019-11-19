package gae_gui;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AddToXML {

    private DocumentBuilder builder;
    private String gameObjectName;
    private String[] properties;
    private String GAME_CONFIG = "GameConfig";
    private String SCREEN_WIDTH = "ScreenWidth";
    private String ROWS = "Rows";
    private String ID = "ID";
    private static Map<String, Map<String,String>> sendToXML = new HashMap<>();


    public Document createXML() throws ParserConfigurationException {
        System.out.println(sendToXML);
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.newDocument();
        Element gameConfig = doc.createElement(GAME_CONFIG);
        doc.appendChild(gameConfig);
        for (String gameObj : sendToXML.keySet()){
            String elementName = gameObj.split(",")[0];
            Element myElement = doc.createElement(elementName);
            gameConfig.appendChild(myElement);
            String id = gameObj.split(",")[1];
            Element myElementID = doc.createElement(ID);
            myElementID.setNodeValue(id);
            myElement.appendChild(myElementID);
            for(String objProperties : sendToXML.get(gameObj).keySet()){
                Element property = doc.createElement(objProperties);
                property.setNodeValue(sendToXML.get(gameObj).get(objProperties));
                myElement.appendChild(property);
            }
        }
        return doc;
    }

    public void addToSendToXMLMap(Map myMap, String gameObjectName){
        int i = 0;
        while(true){
            String putInMap = String.join(",", gameObjectName, Integer.toString(i));
            if(!(sendToXML.containsKey(putInMap))){
                sendToXML.putIfAbsent(putInMap, myMap);
                break;
            }
            i++;
        }
    }


}
