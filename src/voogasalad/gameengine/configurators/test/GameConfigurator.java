package voogasalad.gameengine.configurators.test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;

import java.util.ArrayList;
import java.util.List;

public class GameConfigurator {

    public static final String LEVELS_NODES_TAG = "Level";

    private Element myRoot;
    List<Level> myLevels;

    public List<Level> loadLevelsFromXML(Document doc) throws GameEngineException {
        myRoot = doc.getDocumentElement();
        myLevels = new ArrayList<>();
        LevelConfigurator levelConfigurator = new LevelConfigurator();
        myLevels = levelConfigurator.configureLevels(myRoot.getElementsByTagName(LEVELS_NODES_TAG));
        return myLevels;
    }
}
