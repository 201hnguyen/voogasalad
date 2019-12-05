package voogasalad.gameengine.configurators;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.control.condition.level.LevelCondition;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ConditionsConfigurator {

    public static final String LEVEL_CONDITIONS_PACKAGE_PATH = "voogasalad.gameengine.executors.control.condition.level.";
    public static final String GAME_CONDITIONS_PACKAGE_PATH = "voogasalad.gameengine.executors.control.condition.game.";
    public static final String LEVEL_ACTIONS_PACKAGE_PATH = "voogasalad.gameengine.executors.control.action.level.";
    public static final String GAME_ACTIONS_PACKAGE_PATH = "voogasalad.gameengine.executors.control.action.game.";
    public static final String CONDITION_PARAMETERS_ROOT_TAG = "Parameters";
    public static final String CONDITION_ACTIONS_ROOT_TAG = "Actions";
    public static final String CONDITION_TYPE_ROOT_TAG = "Type";
    public static final String ASSOCIATED_ACTION_TYPE_TAG = "Type";

    public Collection<LevelCondition> buildLevelConditionsCollection(NodeList conditionNodeList) throws GameEngineException {
        Set<LevelCondition> levelConditions = new HashSet<>();
        for (int i=0; i< conditionNodeList.getLength(); i++) {
            if (conditionNodeList.item(i).getNodeType()== Node.ELEMENT_NODE) {
                Element definedCondition = ConfigurationTool.convertNodeToElement(conditionNodeList.item(i));
                Element parametersRoot = ConfigurationTool.convertNodeToElement(definedCondition.getElementsByTagName(CONDITION_PARAMETERS_ROOT_TAG).item(0));
                Element actionsRoot = ConfigurationTool.convertNodeToElement(definedCondition.getElementsByTagName(CONDITION_ACTIONS_ROOT_TAG).item(0));
                String conditionName = definedCondition.getElementsByTagName(CONDITION_TYPE_ROOT_TAG).item(0).getTextContent();
                try {
                    Map<String, String> parameters = setParameters(parametersRoot);
                    Set<LevelAction> actions = setLevelActions(actionsRoot);
                    levelConditions.add((LevelCondition) Class.forName(LEVEL_CONDITIONS_PACKAGE_PATH + conditionName).getConstructor(Map.class, Set.class).newInstance(parameters, actions));
                } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
                    throw new GameEngineException(e, "ConditionsInitializationFailed");
                }
            }
        }
        return levelConditions;
    }

    public Collection<GameCondition> buildGameConditionsCollection(NodeList conditionNodeList) throws GameEngineException {
        Set<GameCondition> gameConditions = new HashSet<>();
        for (int i=0; i< conditionNodeList.getLength(); i++) {
            if (conditionNodeList.item(i).getNodeType()== Node.ELEMENT_NODE) {
                Element definedCondition = ConfigurationTool.convertNodeToElement(conditionNodeList.item(i));
                Element parametersRoot = ConfigurationTool.convertNodeToElement(definedCondition.getElementsByTagName(CONDITION_PARAMETERS_ROOT_TAG).item(0));
                Element actionsRoot = ConfigurationTool.convertNodeToElement(definedCondition.getElementsByTagName(CONDITION_ACTIONS_ROOT_TAG).item(0));
                String conditionName = definedCondition.getElementsByTagName(CONDITION_TYPE_ROOT_TAG).item(0).getTextContent();
                try {
                    Map<String, String> parameters = setParameters(parametersRoot);
                    Set<GameAction> actions = setGameActions(actionsRoot);
                    gameConditions.add((GameCondition) Class.forName(GAME_CONDITIONS_PACKAGE_PATH + conditionName).getConstructor(Map.class, Set.class).newInstance(parameters, actions));
                } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
                    throw new GameEngineException(e, "ConditionsInitializationFailed");
                }
            }
        }
        return gameConditions;
    }


    private Map<String, String> setParameters(Element parametersRoot) {
        Map<String, String> parameters = new HashMap<>();
        NodeList childNodes = parametersRoot.getChildNodes();
        for (int j=0; j<childNodes.getLength(); j++) {
            Element parameter = ConfigurationTool.convertNodeToElement(childNodes.item(j));
            if (parameter != null) {
                parameters.put(parameter.getNodeName(), parameter.getTextContent());
            }
        }
        return parameters;
    }

    private Set<LevelAction> setLevelActions(Element actionsRoot) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Set<LevelAction> actions = new HashSet<>();
        NodeList childNodes = actionsRoot.getChildNodes();
        for (int j=0; j<childNodes.getLength(); j++) {
            Element action = ConfigurationTool.convertNodeToElement(childNodes.item(j));
            if (action != null) {
                String actionName = action.getElementsByTagName(ASSOCIATED_ACTION_TYPE_TAG).item(0).getTextContent();
                actions.add((LevelAction) Class.forName(LEVEL_ACTIONS_PACKAGE_PATH + actionName).getConstructor().newInstance());
            }
        }
        return actions;
    }

    private Set<GameAction> setGameActions(Element actionsRoot) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Set<GameAction> actions = new HashSet<>();
        NodeList childNodes = actionsRoot.getChildNodes();
        for (int j=0; j<childNodes.getLength(); j++) {
            Element action = ConfigurationTool.convertNodeToElement(childNodes.item(j));
            if (action != null) {
                String actionName = action.getElementsByTagName(ASSOCIATED_ACTION_TYPE_TAG).item(0).getTextContent();
                actions.add((GameAction) Class.forName(GAME_ACTIONS_PACKAGE_PATH + actionName).getConstructor().newInstance());
            }
        }
        return actions;
    }
}
