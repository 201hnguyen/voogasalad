package voogasalad.gameengine.configurators.test;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.SpriteBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PrototypesConfigurator {

    public static final String PROTOTYPE_CONFIG_METHOD_CALLS_PATH = "resources/engine/PrototypeConfiguratorMethodCalls";
    public static final ResourceBundle PROTOTYPE_CONFIG_BUNDLE = ResourceBundle.getBundle(PROTOTYPE_CONFIG_METHOD_CALLS_PATH);
    public static final String SPRITE_PROPERTIES_NODE_TAG = "Properties";
    public static final String SPRITE_STRATEGIES_NODE_TAG = "Strategies";

    private NodeList myPrototypesNodesList;

    public List<Sprite> buildPrototypesList(NodeList prototypesNodeList) throws GameEngineException {
        myPrototypesNodesList = prototypesNodeList; // this is all the defined prototypes in a level in the xml
        List<Sprite> prototypesForLevel = new ArrayList<>();
        for (int i = 0; i< myPrototypesNodesList.getLength(); i++) {
            SpriteBuilder spriteBuilder = new SpriteBuilder();
            Element definedPrototype = ConfigurationTool.convertNodeToElement(myPrototypesNodesList.item(i));

            Element propertiesRoot = ConfigurationTool.convertNodeToElement(definedPrototype.getElementsByTagName(SPRITE_PROPERTIES_NODE_TAG).item(0));
            Element strategiesRoot = ConfigurationTool.convertNodeToElement(definedPrototype.getElementsByTagName(SPRITE_STRATEGIES_NODE_TAG).item(0));

            setPropertiesForSpriteBuilder(spriteBuilder, propertiesRoot);
            setStrategiesForSpriteBuilder(spriteBuilder, strategiesRoot);
            prototypesForLevel.add(spriteBuilder.build());
        }
        System.out.println("Current number of prototypes created for level: " + prototypesForLevel.size());
        return prototypesForLevel;
    }

    private void setPropertiesForSpriteBuilder(SpriteBuilder spriteBuilder, Element root) {
        NodeList listOfSpriteProperties = root.getChildNodes(); // this will return both element nodes and text nodes; we don't care about text nodes.
        for (int j=0; j<listOfSpriteProperties.getLength(); j++) {
            Element property = ConfigurationTool.convertNodeToElement(listOfSpriteProperties.item(j)); //filter to elements nodes only.
            if (property != null) {
                setSinglePropertyInBuilder(spriteBuilder, property);
            }
        }
    }

    private void setSinglePropertyInBuilder(SpriteBuilder builder, Node property) {
        String value = property.getTextContent();
        try {
            String methodName = PROTOTYPE_CONFIG_BUNDLE.getString(property.getNodeName());
            builder.getClass().getMethod(methodName, String.class).invoke(builder, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //FIXME
        }
    }

    private void setStrategiesForSpriteBuilder(SpriteBuilder builder, Element root) {

    }
}
