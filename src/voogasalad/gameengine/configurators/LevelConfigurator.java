package voogasalad.gameengine.configurators;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.levelcontrol.Wave;
import voogasalad.gameengine.executors.control.condition.level.LevelCondition;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.ConfigurationTool;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LevelConfigurator {

    public static final String WAVE_NODES_TAG = "Wave";
    public static final String RESOURCES_NODE_TAG = "Resources";
    public static final String LIVES_NODE_TAG = "Lives";
    public static final String CONDITION_NODES_TAG = "Condition";
    public static final String LEVEL_ID_NODE_TAG = "LevelId";
    public static final String BACKGROUND_PATH_TAG = "BackgroundImage";
    public static final String SOUND_PATH_TAG = "SoundFile";
    public static final String PROTOTYPE_SPECIFIED_FOR_LEVEL_NODE_TAG = "AvailablePrototypes";
    public static final String DEFAULT_BACKGROUND_PATH = "whitebackground.jpg";

    private Element myCurrentLevelRoot;
    private List<Sprite> myAvailablePrototypesList;

    public List<Level> configureLevels(NodeList levelNodes, List<Sprite> gamePrototypes) throws GameEngineException {
        List<Level> levels = new ArrayList<>();
        for (int i=0; i<levelNodes.getLength(); i++) {
            Element levelRoot = ConfigurationTool.convertNodeToElement(levelNodes.item(i));
            myCurrentLevelRoot = levelRoot;
            myAvailablePrototypesList = configureLevelPrototypes(gamePrototypes);
            Collection<Wave> wavesCollection = configureWaves();
            int resources = configureIntProperties(RESOURCES_NODE_TAG);
            int lives = configureIntProperties(LIVES_NODE_TAG);
            int levelId = configureIntProperties(LEVEL_ID_NODE_TAG);
            Collection<LevelCondition> levelConditions = configureLevelConditions();
            String backgroundPath = configureBackgroundPath();
            String soundPath = configureSoundPath();
            levels.add(new LevelBuilder(levelId).setConditions(levelConditions)
                    .setLives(lives).setResources(resources).setSpritePrototypes(myAvailablePrototypesList)
                    .setWaves(wavesCollection).setBackgroundPath(backgroundPath).setSoundPath(soundPath).build());
        }
        return levels;
    }

    private Collection<Wave> configureWaves() throws GameEngineException {
        WavesConfigurator wavesConfigurator = new WavesConfigurator();
        NodeList waveNodes = myCurrentLevelRoot.getElementsByTagName(WAVE_NODES_TAG);
        return wavesConfigurator.buildWavesCollection(waveNodes, myAvailablePrototypesList);
    }

    private int configureIntProperties(String propertyNodeTagName) {
        try {
            return Integer.parseInt(myCurrentLevelRoot.getElementsByTagName(propertyNodeTagName).item(0).getTextContent());
        } catch (NullPointerException | NumberFormatException e) {
            return 0;
        }
    }

    private Collection<LevelCondition> configureLevelConditions() throws GameEngineException {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        NodeList conditionNodes = myCurrentLevelRoot.getElementsByTagName(CONDITION_NODES_TAG);
        return conditionsConfigurator.buildLevelConditionsCollection(conditionNodes);
    }

    private String configureBackgroundPath() {
        try {
            return myCurrentLevelRoot.getElementsByTagName(BACKGROUND_PATH_TAG).item(0).getTextContent();
        } catch (NullPointerException e) {
            return DEFAULT_BACKGROUND_PATH;
        }
    }

    private String configureSoundPath() {
        try {
            return myCurrentLevelRoot.getElementsByTagName(SOUND_PATH_TAG).item(0).getTextContent();
        } catch (NullPointerException e) {
            return "";
        }
    }

    private List<Sprite> configureLevelPrototypes(List<Sprite> gamePrototypes) {
        List<Sprite> prototypesSpecifiedForLevel = new ArrayList<>();
        try {
            configureLevelPrototypesHelper(gamePrototypes, prototypesSpecifiedForLevel);
        } catch (NullPointerException | NumberFormatException e) {
            return gamePrototypes;
        }
        return prototypesSpecifiedForLevel;
    }

    private void configureLevelPrototypesHelper(List<Sprite> gamePrototypes, List<Sprite> prototypesSpecifiedForLevel) {
        String[] prototypesSpecifiedForLevelAsStrings = myCurrentLevelRoot.getElementsByTagName(PROTOTYPE_SPECIFIED_FOR_LEVEL_NODE_TAG).item(0).getTextContent().split(" ");
        for (String prototypeString : prototypesSpecifiedForLevelAsStrings) {
            for (Sprite prototype : gamePrototypes) {
                if (prototype.getPrototypeId() == Integer.parseInt(prototypeString) || prototype.getSpriteArchetype()== SpriteArchetype.PROJECTILE) {
                    prototypesSpecifiedForLevel.add(prototype);
                }
            }
        }
    }

}
