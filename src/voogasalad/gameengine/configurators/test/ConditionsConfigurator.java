package voogasalad.gameengine.configurators.test;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import voogasalad.gameengine.executors.gamecontrol.condition.LevelCondition;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ConditionsConfigurator {

    public static final String CONDITIONS_PACKAGE_PATH = "voogasalad.gameengine.executors.gamecontrol.condition.";
    public static final String ACTIONS_PACKAGE_PATH = "voogasalad.gameengine.executors.gamecontrol.action.";

    private NodeList myConditionsNodeList;

    public Collection<LevelCondition> buildConditionsCollection(NodeList conditionNodeList) {
        Set<LevelCondition> levelConditions = new HashSet<>();
        myConditionsNodeList = conditionNodeList;
        for (int i=0; i< myConditionsNodeList.getLength(); i++) {
            if (conditionNodeList.item(i).getNodeType()== Node.ELEMENT_NODE) {
                Element definedCondition = convertNodeToElement(conditionNodeList.item(i));
                Element parametersRoot = convertNodeToElement(definedCondition.getElementsByTagName("Parameters").item(0));
                Element actionsRoot = convertNodeToElement(definedCondition.getElementsByTagName("Actions").item(0));
                String conditionName = definedCondition.getElementsByTagName("Type").item(0).getTextContent();
                Map<String, String> parameters = setParameters(parametersRoot);
                Set<LevelAction> actions = setActions(actionsRoot);
                try {
                    levelConditions.add((LevelCondition) Class.forName(CONDITIONS_PACKAGE_PATH + conditionName).getConstructor(Map.class, Set.class).newInstance(parameters, actions));
                } catch (InstantiationException e) {
                    e.printStackTrace(); //FIXME
                } catch (InvocationTargetException e) {
                    e.printStackTrace(); //FIXME
                } catch (NoSuchMethodException e) {
                    e.printStackTrace(); //FIXME
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); //FIXME
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
                System.out.println(conditionName);
            }
        }
        return levelConditions;
    }

    private Map<String, String> setParameters(Element parametersRoot) {
        Map<String, String> parameters = new HashMap<>();
        NodeList childNodes = parametersRoot.getChildNodes();
        for (int j=0; j<childNodes.getLength(); j++) {
            Element parameter = convertNodeToElement(childNodes.item(j));
            if (parameter != null) {
                parameters.put(parameter.getNodeName(), parameter.getTextContent());
            }
        }
        if (! parameters.keySet().contains("classification")) {
            parameters.put("classification", "ONETIME");
        }
        return parameters;
    }

    private Set<LevelAction> setActions(Element actionsRoot) {
        Set<LevelAction> actions = new HashSet<>();
        NodeList childNodes = actionsRoot.getChildNodes();
        for (int j=0; j<childNodes.getLength(); j++) {
            Element action = convertNodeToElement(childNodes.item(j)); //filter to elements nodes only.
            if (action != null) {
                String actionName = action.getElementsByTagName("type").item(0).getTextContent();
                try {
                    actions.add((LevelAction) Class.forName(ACTIONS_PACKAGE_PATH + actionName).getConstructor().newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace(); //FIXME
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); //FIXME
                } catch (InvocationTargetException e) {
                    e.printStackTrace(); //FIXME
                } catch (NoSuchMethodException e) {
                    e.printStackTrace(); //FIXME
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            }
        }
        return actions;
    }

    private Element convertNodeToElement(Node node) {
        Element element = null;
        if (node.getNodeType()==Node.ELEMENT_NODE) {
            element = (Element) node;
        }
        return element;
    }
}
