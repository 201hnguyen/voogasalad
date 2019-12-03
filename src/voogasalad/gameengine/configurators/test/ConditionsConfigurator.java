package voogasalad.gameengine.configurators.test;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import voogasalad.gameengine.executors.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ConditionsConfigurator {

    public static final String CONDITIONS_PACKAGE_PATH = "voogasalad.gameengine.executors.gamecontrol.condition.";
    public static final String ACTIONS_PACKAGE_PATH = "voogasalad.gameengine.executors.gamecontrol.action.";
    public static final String CONDITION_PARAMETERS_ROOT_TAG = "Parameters";
    public static final String CONDITION_ACTIONS_ROOT_TAG = "Actions";
    public static final String CONDITION_TYPE_ROOT_TAG = "Type";
    public static final String ASSOCIATED_ACTION_TYPE_TAG = "type";

    private NodeList myConditionsNodeList;

    public Collection<LevelCondition> buildConditionsCollection(NodeList conditionNodeList) {
        Set<LevelCondition> levelConditions = new HashSet<>();
        myConditionsNodeList = conditionNodeList;
        for (int i=0; i< myConditionsNodeList.getLength(); i++) {
            if (conditionNodeList.item(i).getNodeType()== Node.ELEMENT_NODE) {
                Element definedCondition = ConfigurationTool.convertNodeToElement(conditionNodeList.item(i));
                Element parametersRoot = ConfigurationTool.convertNodeToElement(definedCondition.getElementsByTagName(CONDITION_PARAMETERS_ROOT_TAG).item(0));
                Element actionsRoot = ConfigurationTool.convertNodeToElement(definedCondition.getElementsByTagName(CONDITION_ACTIONS_ROOT_TAG).item(0));
                String conditionName = definedCondition.getElementsByTagName(CONDITION_TYPE_ROOT_TAG).item(0).getTextContent();
                try {
                    Map<String, String> parameters = setParameters(parametersRoot);
                    Set<LevelAction> actions = setActions(actionsRoot);
                    levelConditions.add((LevelCondition) Class.forName(CONDITIONS_PACKAGE_PATH + conditionName).getConstructor(Map.class, Set.class).newInstance(parameters, actions));
                } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            }
        }
        return levelConditions;
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

    private Set<LevelAction> setActions(Element actionsRoot) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Set<LevelAction> actions = new HashSet<>();
        NodeList childNodes = actionsRoot.getChildNodes();
        for (int j=0; j<childNodes.getLength(); j++) {
            Element action = ConfigurationTool.convertNodeToElement(childNodes.item(j)); //filter to elements nodes only.
            if (action != null) {
                String actionName = action.getElementsByTagName(ASSOCIATED_ACTION_TYPE_TAG).item(0).getTextContent();
                actions.add((LevelAction) Class.forName(ACTIONS_PACKAGE_PATH + actionName).getConstructor().newInstance());
            }
        }
        return actions;
    }
}
