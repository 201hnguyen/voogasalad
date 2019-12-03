package voogasalad.gameengine.configurators.test;

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

    private Element myCurrentLevelRoot;
    private List<Sprite> myPrototypesList;
    private Collection<Wave> myWavesCollection;
    private int myResources;
    private int myLives;
    private Collection<LevelCondition> myLevelConditions;

    public List<Level> configureLevels(NodeList levelNodes) throws GameEngineException {
        List<Level> levels = new ArrayList<>();
        for (int i=0; i<levelNodes.getLength(); i++) {
            Element levelRoot = ConfigurationTool.convertNodeToElement(levelNodes.item(i));
            myCurrentLevelRoot = levelRoot;
            myPrototypesList = configurePrototypes();
            myWavesCollection = configureWaves();
            myResources = configureResources();
            myLives = configureLives();
            myLevelConditions = configureLevelConditions();
            levels.add(new LevelBuilder().setConditions(myLevelConditions).setLives(myLives).setResources(myResources).setSpritePrototypes(myPrototypesList).setWaves(myWavesCollection).build());
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

    private int configureResources() {
        return Integer.parseInt(myCurrentLevelRoot.getElementsByTagName(RESOURCES_NODE_TAG).item(0).getTextContent());
    }

    private int configureLives() {
        return Integer.parseInt(myCurrentLevelRoot.getElementsByTagName(LIVES_NODE_TAG).item(0).getTextContent());
    }

    private Collection<LevelCondition> configureLevelConditions() {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        NodeList conditionNodes = myCurrentLevelRoot.getElementsByTagName(CONDITION_NODES_TAG);
        return conditionsConfigurator.buildConditionsCollection(conditionNodes);
    }

}
