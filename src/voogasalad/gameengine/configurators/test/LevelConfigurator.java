package voogasalad.gameengine.configurators.test;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.Wave;
import voogasalad.gameengine.executors.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.Collection;
import java.util.List;

public class LevelConfigurator {

    private Element myRoot;
    private List<Sprite> myPrototypesList;
    private Collection<Wave> myWavesCollection;
    private int myResources;
    private int myLives;
    private Collection<LevelCondition> myLevelConditions;

    public Level configureLevel(Element levelRoot) throws GameEngineException {
        myRoot = levelRoot;
        myPrototypesList = configurePrototypes();
        myWavesCollection = configureWaves();
        myResources = configureResources();
        myLives = configureLives();
        myLevelConditions = configureLevelConditions();
        return new LevelBuilder().setConditions(myLevelConditions).setLives(myLives).setResources(myResources).setSpritePrototypes(myPrototypesList).setWaves(myWavesCollection).build();
    }

    private List<Sprite> configurePrototypes() throws GameEngineException {
        PrototypesConfigurator prototypesConfigurator = new PrototypesConfigurator();
        NodeList prototypeNodes = myRoot.getElementsByTagName("SpritePrototype");
        return prototypesConfigurator.buildPrototypesList(prototypeNodes);
    }

    private Collection<Wave> configureWaves() {
        WavesConfigurator wavesConfigurator = new WavesConfigurator();
        NodeList waveNodes = myRoot.getElementsByTagName("Wave");
        return wavesConfigurator.buildWavesCollection(waveNodes);
    }

    private int configureResources() {
        return Integer.parseInt(myRoot.getElementsByTagName("Resources").item(0).getTextContent());
    }

    private int configureLives() {
        return Integer.parseInt(myRoot.getElementsByTagName("Lives").item(0).getTextContent());
    }

    private Collection<LevelCondition> configureLevelConditions() {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        NodeList conditionNodes = myRoot.getElementsByTagName("Condition");
        return conditionsConfigurator.buildConditionsCollection(conditionNodes);
    }

}
