package voogasalad.gameengine.configurators;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.Wave;
import voogasalad.gameengine.executors.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LevelConfigurator {

    public static final String SPRITE_PROTOTYPE_NODES_TAG = "SpritePrototype";
    public static final String WAVE_NODES_TAG = "Wave";
    public static final String RESOURCES_NODE_TAG = "Resources";
    public static final String LIVES_NODE_TAG = "Lives";
    public static final String CONDITION_NODES_TAG = "Condition";
    public static final String LEVEL_ID_NODE_TAG = "LevelId";
    public static final String BACKGROUND_PATH_TAG = "BackgroundImage";
    public static final String DEFAULT_BACKGROUND_PATH = "whitebackground.jpg";

    private Element myCurrentLevelRoot;

    public List<Level> configureLevels(NodeList levelNodes) throws GameEngineException {
        List<Level> levels = new ArrayList<>();
        for (int i=0; i<levelNodes.getLength(); i++) {
            Element levelRoot = ConfigurationTool.convertNodeToElement(levelNodes.item(i));
            myCurrentLevelRoot = levelRoot;
            List<Sprite> prototypesList = configurePrototypes();
            Collection<Wave> wavesCollection = configureWaves();
            int resources = configureIntProperties(RESOURCES_NODE_TAG);
            int lives = configureIntProperties(LIVES_NODE_TAG);
            int levelId = configureIntProperties(LEVEL_ID_NODE_TAG);
            Collection<LevelCondition> levelConditions = configureLevelConditions();
            String backgroundPath = configureBackgroundPath();
            levels.add(new LevelBuilder(levelId).setConditions(levelConditions)
                    .setLives(lives).setResources(resources).setSpritePrototypes(prototypesList)
                    .setWaves(wavesCollection).setBackgroundPath(backgroundPath).build());
        }
        return levels;
    }

    private List<Sprite> configurePrototypes() throws GameEngineException {
        PrototypesConfigurator prototypesConfigurator = new PrototypesConfigurator();
        NodeList prototypeNodes = myCurrentLevelRoot.getElementsByTagName(SPRITE_PROTOTYPE_NODES_TAG);
        return prototypesConfigurator.buildPrototypesList(prototypeNodes);
    }

    private Collection<Wave> configureWaves() {
        WavesConfigurator wavesConfigurator = new WavesConfigurator();
        NodeList waveNodes = myCurrentLevelRoot.getElementsByTagName(WAVE_NODES_TAG);
        return wavesConfigurator.buildWavesCollection(waveNodes);
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
        return conditionsConfigurator.buildConditionsCollection(conditionNodes);
    }

    private String configureBackgroundPath() {
        try {
            return myCurrentLevelRoot.getElementsByTagName(BACKGROUND_PATH_TAG).item(0).getTextContent();
        } catch (NullPointerException e) {
            return DEFAULT_BACKGROUND_PATH;
        }
    }

}
