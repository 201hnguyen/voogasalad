package voogasalad.gameengine.executors.utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ConfigurationTool {
    public static Element convertNodeToElement(Node node) {
        Element element = null;
        if (node.getNodeType()==Node.ELEMENT_NODE) {
            element = (Element) node;
        }
        return element;
    }
}
