package voogasalad.gameengine.configurators;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.control.gamecontrol.controllers.GameLevelsController;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GameConfigurator {

    public static final String LEVELS_NODES_TAG = "Level";
    public static final String LEVELS_SEQUENCE_NODE_TAG = "LevelSequence";
    public static final String PROTOTYPES_NODES_TAG = "SpritePrototype";
    public static final String GAME_CONDITIONS_NODES_TAG = "GameCondition";

    private Element myRoot;
    private Document myDocument;
    private List<Sprite> myGamePrototypes;

    public GameConfigurator(Document doc) throws GameEngineException {
        myDocument = doc;
        myRoot = myDocument.getDocumentElement();
        myGamePrototypes = configurePrototypes();
    }

    public GameLevelsController loadLevelsFromXML() throws GameEngineException {
        List<Level> levels = loadLevels();
        List<Integer> levelsSequence = loadLevelsSequence();
        return new GameLevelsController(levels, levelsSequence);
    }

    private List<Level> loadLevels() throws GameEngineException {
        try {
            List<Level> levels;
            LevelConfigurator levelConfigurator = new LevelConfigurator();
            levels = levelConfigurator.configureLevels(myRoot.getElementsByTagName(LEVELS_NODES_TAG), myGamePrototypes);
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

    private List<Sprite> configurePrototypes() throws GameEngineException {
        PrototypesConfigurator prototypesConfigurator = new PrototypesConfigurator();
        NodeList prototypeNodes = myRoot.getElementsByTagName(PROTOTYPES_NODES_TAG);
        return prototypesConfigurator.buildPrototypesList(prototypeNodes);
    }

    public Collection<GameCondition> configureGameConditions() throws GameEngineException {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        NodeList conditionNodes = myRoot.getElementsByTagName(GAME_CONDITIONS_NODES_TAG);
        return conditionsConfigurator.buildGameConditionsCollection(conditionNodes);
    }
}