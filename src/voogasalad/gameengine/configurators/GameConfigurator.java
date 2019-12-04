package voogasalad.gameengine.configurators;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import voogasalad.gameengine.LevelsController;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameConfigurator {

    public static final String LEVELS_NODES_TAG = "Level";
    public static final String LEVELS_SEQUENCE_NODE_TAG = "LevelSequence";

    private Element myRoot;
    private Document myDocument;

    public GameConfigurator(Document doc) {
        myDocument = doc;
        myRoot = myDocument.getDocumentElement();
    }

    public LevelsController loadLevelsFromXML() throws GameEngineException {
        List<Level> levels = loadLevels();
        List<Integer> levelsSequence = loadLevelsSequence();
        return new LevelsController(levels, levelsSequence);
    }

    private List<Level> loadLevels() throws GameEngineException {
        try {
            List<Level> levels;
            LevelConfigurator levelConfigurator = new LevelConfigurator();
            levels = levelConfigurator.configureLevels(myRoot.getElementsByTagName(LEVELS_NODES_TAG));
            return levels;
        } catch (NullPointerException e) {
            throw new GameEngineException(e, "NoLevelsSpecified");
        }
    }

    private List<Integer> loadLevelsSequence() {
        try {
            List<String> levelsSequenceAsStringList = Arrays.asList(myRoot.getElementsByTagName(LEVELS_SEQUENCE_NODE_TAG).item(0).getTextContent().split(" "));
            List<Integer> levelsSequence = new ArrayList<>();
            for (String levelId : levelsSequenceAsStringList) {
                levelsSequence.add(Integer.parseInt(levelId));
            }
            return levelsSequence;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
