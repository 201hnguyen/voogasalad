package voogasalad.gameengine.configurators;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.control.gamecontrol.controllers.GameLevelsController;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.*;

/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class GameConfigurator {

    public static final String GAME_CONFIGURATION_RESOURCE_PATH = "resources/engine/EngineXMLTags";
    public static final ResourceBundle GAME_CONFIGURATION_RESOURCE_BUNDLE = ResourceBundle.getBundle(GAME_CONFIGURATION_RESOURCE_PATH);
    public static final String LEVELS_NODES_KEY = "LevelNodeTag";
    public static final String LEVELS_SEQUENCE_NODE_KEY = "LevelsSequenceNodeTag";
    public static final String PROTOTYPES_NODES_KEY = "PrototypesNodeTag";
    public static final String GAME_CONDITIONS_NODES_KEY = "GameConditionsNodeTag";
    public static final String GAME_TITLE_NODE_KEY = "GameTitleNodeTag";

    private Element myRoot;
    private Document myDocument;
    private List<Sprite> myGamePrototypes;

    /**
     * Purpose:
     * Assumptions:
     * @param doc
     * @throws GameEngineException
     */
    public GameConfigurator(Document doc) throws GameEngineException {
        myDocument = doc;
        myRoot = myDocument.getDocumentElement();
        myGamePrototypes = configurePrototypes();
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public List<Sprite> getGamePrototypesCollection() {
        return myGamePrototypes;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     * @throws GameEngineException
     */
    public GameLevelsController loadLevelsFromXML() throws GameEngineException {
        List<Level> levels = loadLevels();
        List<Integer> levelsSequence = loadLevelsSequence();
        return new GameLevelsController(levels, levelsSequence);
    }

    private List<Level> loadLevels() throws GameEngineException {
        try {
            LevelConfigurator levelConfigurator = new LevelConfigurator();
            return levelConfigurator.configureLevels(myRoot.getElementsByTagName(GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(LEVELS_NODES_KEY)), myGamePrototypes);
        } catch (NullPointerException e) {
            throw new GameEngineException(e, "NoLevelsSpecified");
        }
    }

    private List<Integer> loadLevelsSequence() {
        List<Integer> levelsSequence = new ArrayList<>();
        try {
            List<String> levelsSequenceAsStringList = Arrays.asList(myRoot.getElementsByTagName(GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(LEVELS_SEQUENCE_NODE_KEY)).item(0).getTextContent().split(" "));
            for (String levelId : levelsSequenceAsStringList) {
                levelsSequence.add(Integer.parseInt(levelId));
            }
        } catch (NullPointerException e) {
            // do nothing; empty levels sequence returned
        }
        return levelsSequence;
    }

    private List<Sprite> configurePrototypes() throws GameEngineException {
        PrototypesConfigurator prototypesConfigurator = new PrototypesConfigurator();
        NodeList prototypeNodes = myRoot.getElementsByTagName(GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(PROTOTYPES_NODES_KEY));
        return prototypesConfigurator.buildPrototypesList(prototypeNodes);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     * @throws GameEngineException
     */
    public Collection<GameCondition> configureGameConditions() throws GameEngineException {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        NodeList conditionNodes = myRoot.getElementsByTagName(GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(GAME_CONDITIONS_NODES_KEY));
        return conditionsConfigurator.buildGameConditionsCollection(conditionNodes);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public String configureGameTitle() {
        try {
            return myRoot.getElementsByTagName(GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(GAME_TITLE_NODE_KEY)).item(0).getTextContent();
        } catch (NullPointerException e) {
            return "";
        }
    }
}
