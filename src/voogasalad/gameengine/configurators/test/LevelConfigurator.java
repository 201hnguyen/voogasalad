package voogasalad.gameengine.configurators.test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.List;

public class LevelConfigurator {

    private Document myDocument;
    private Element myRoot;
    private PrototypesConfigurator myPrototypesConfigurator;
    List<Sprite> myPrototypesList;

    public void loadXML(Document doc) throws GameEngineException {
        myPrototypesConfigurator = new PrototypesConfigurator();
        myDocument = doc;
        myRoot = doc.getDocumentElement();
        System.out.println(myRoot.getNodeName());
        configurePrototypes();
    }

    private void configurePrototypes() throws GameEngineException {
        NodeList prototypeNodes = myRoot.getElementsByTagName("SpritePrototype");
        myPrototypesList = myPrototypesConfigurator.buildPrototypesList(prototypeNodes);
    }
}
