package voogasalad.gameengine.configurators.test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;

import java.util.ArrayList;
import java.util.List;

public class GameConfigurator {

    private Element myRoot;
    List<Level> myLevels;

    public List<Level> loadLevelsFromXML(Document doc) throws GameEngineException {
        myRoot = doc.getDocumentElement();
        myLevels = new ArrayList<>();
        LevelConfigurator levelConfigurator = new LevelConfigurator();
        myLevels.add(levelConfigurator.configureLevel(myRoot)); //TODO: Later this will be list of levels so level root is not myRoot
        return myLevels;
    }
}
