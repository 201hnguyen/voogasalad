package voogasalad.gameengine.configurators;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.HealthBuilder;
import voogasalad.gameengine.executors.objectcreators.MovementBuilder;
import voogasalad.gameengine.executors.objectcreators.SpriteBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PrototypesConfigurator {

    public static final String PROTOTYPE_CONFIG_METHOD_CALLS_PATH = "resources/engine/PrototypeConfiguratorMethodCalls";
    public static final String STRATEGY_CONFIG_METHOD_CALLS_PATH = "resources/engine/StrategyConfiguratorMethodCalls";
    public static final ResourceBundle PROTOTYPE_CONFIG_BUNDLE = ResourceBundle.getBundle(PROTOTYPE_CONFIG_METHOD_CALLS_PATH);
    public static final ResourceBundle STRATEGY_CONFIG_BUNDLE = ResourceBundle.getBundle(STRATEGY_CONFIG_METHOD_CALLS_PATH);
    public static final String SPRITE_PROPERTIES_NODE_TAG = "Properties";
    public static final String SPRITE_STRATEGIES_NODE_TAG = "Strategies";
    public static final String SPRITE_STRATEGIES_TYPE_NODE_TAG = "Type";
    public static final String SPRITE_STRATEGIES_PARAMETERS_NODE_TAG = "Parameters";

    //TODO: Refactor all the different strategy builders into one builder.

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

    private void setPropertiesForSpriteBuilder(SpriteBuilder spriteBuilder, Element root) throws GameEngineException {
        NodeList listOfSpriteProperties = root.getChildNodes();
        for (int j=0; j<listOfSpriteProperties.getLength(); j++) {
            Element property = ConfigurationTool.convertNodeToElement(listOfSpriteProperties.item(j));
            if (property != null) {
                setSinglePropertyInBuilder(spriteBuilder, property);
            }
        }
    }

    private void setSinglePropertyInBuilder(SpriteBuilder builder, Node property) throws GameEngineException {
        String value = property.getTextContent();
        try {
            String methodName = PROTOTYPE_CONFIG_BUNDLE.getString(property.getNodeName());
            builder.getClass().getMethod(methodName, String.class).invoke(builder, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new GameEngineException(e, "SpriteProductionFailed");
        }
    }

    private void setStrategiesForSpriteBuilder(SpriteBuilder builder, Element root) throws GameEngineException {
        NodeList listOfSpriteStrategies = root.getChildNodes();
        for (int i=0; i<listOfSpriteStrategies.getLength(); i++) {
            Element strategy = ConfigurationTool.convertNodeToElement(listOfSpriteStrategies.item(i));
            if (strategy != null) {
                try {
                    String methodName = STRATEGY_CONFIG_BUNDLE.getString(strategy.getNodeName());
                    String type = strategy.getElementsByTagName(SPRITE_STRATEGIES_TYPE_NODE_TAG).item(0).getTextContent();
                    this.getClass().getDeclaredMethod(methodName, String.class, SpriteBuilder.class, Element.class).invoke(this, type, builder, strategy);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new GameEngineException(e, "SpriteProductionFailed");
                }
            }
        }
    }

    private void setHealthStrategy(String type, SpriteBuilder builder, Element healthStrategyNode) throws GameEngineException {
        NodeList parametersNodeList = healthStrategyNode.getElementsByTagName(SPRITE_STRATEGIES_PARAMETERS_NODE_TAG).item(0).getChildNodes();
        HealthBuilder healthBuilder = new HealthBuilder().setHealthType(type);
        for (int i=0; i<parametersNodeList.getLength();i++) {
            Element parameter = ConfigurationTool.convertNodeToElement(parametersNodeList.item(i));
            if (parameter!= null) {
                try {
                    healthBuilder.getClass().getMethod(STRATEGY_CONFIG_BUNDLE.getString(parameter.getNodeName()), String.class).invoke(healthBuilder, parameter.getTextContent());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new GameEngineException(e, "SpriteProductionFailed");
                }
            }
        }
        builder.setHealthStrategy(healthBuilder.build());
    }

    private void setMovementStrategy(String type, SpriteBuilder builder, Element movementStrategyNode) throws GameEngineException {
        NodeList parametersNodeList = movementStrategyNode.getElementsByTagName(SPRITE_STRATEGIES_PARAMETERS_NODE_TAG).item(0).getChildNodes();
        MovementBuilder movementBuilder = new MovementBuilder().setMovementType(type);
        for (int i=0; i<parametersNodeList.getLength();i++) {
            Element parameter = ConfigurationTool.convertNodeToElement(parametersNodeList.item(i));
            if (parameter!= null) {
                try {
                    movementBuilder.getClass().getMethod(STRATEGY_CONFIG_BUNDLE.getString(parameter.getNodeName()), String.class).invoke(movementBuilder, parameter.getTextContent());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new GameEngineException(e, "SpriteProductionFailed");
                }
            }
        }
        builder.setMovementStrategy(movementBuilder.build());
    }
}
