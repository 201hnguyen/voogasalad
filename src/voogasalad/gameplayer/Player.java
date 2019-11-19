package voogasalad.gameplayer;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.engine.factories.StrategiesFactory;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.Wave;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.action.SpawnWaveAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.engine.gamecontrol.condition.TemporalCondition;
import voogasalad.gameengine.engine.gamecontrol.managers.ActionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.ConditionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.StatusManager;
import voogasalad.gameengine.engine.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.engine.sprites.JavaFXSprite;
import voogasalad.gameengine.engine.sprites.JavaFXSpriteManager;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameengine.engine.sprites.SpriteManager;
import voogasalad.gameengine.engine.sprites.strategies.health.Health;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;

public class Player {

    public static final String TYPE = "GameConfig";
    public static final String ACTION_PATH = "voogasalad.gameengine.engine.gamecontrol.action.";
    public static final int WINDOW_SIZE = 500;
    private String myXMLPath;
    private XMLParser myXMLParser;
    private Stage myStage;
    private Group mapRoot;
    private SpriteProductsFactory spriteFactory;
    private StrategiesFactory strategiesFactory;
    private EngineDriverManager engineDriverManager;


    //Player expects a javaFX Stage upon instantiation
    public Player(Stage primaryStage, String xmlPath){
        myStage = primaryStage;
        mapRoot = new Group();
        loadXML(xmlPath);
        initialiseEngine();
    }

    private void initialiseEngine(){
        spriteFactory = new SpriteProductsFactory();
        strategiesFactory = new StrategiesFactory();
        engineDriverManager = new EngineDriverManager();
    }

    public Level startGame() throws GameEngineException {
        return displayMapFromXML();
    }

    private Level displayMapFromXML() throws GameEngineException {
        Scene scene = new Scene(mapRoot, 1000, 800);
        myStage.setScene(scene);
        myStage.show();
        String[] componentTypes = {"Tower", "Enemy"};
        for (String component : componentTypes) {
            ArrayList<Map<String, String>> componentList = myXMLParser.getAttributesByTagName(component);
            for (int i = 0; i < componentList.size(); i++) {
                instantiateEngineObject(component, componentList.get(i));
            }
        }
        Queue<Integer> spritesWave0Queue = new LinkedList<>() {{ add(2); add(2); add(0); }};
        engineDriverManager.addWave(createWave(new Point(250, 300), spritesWave0Queue, 1));
        engineDriverManager.instantiateEngineManagers();
        return engineDriverManager.getNewLevel();
    }

    private Wave createWave(Point waveSpawnPoint, Queue<Integer> spritesWaveQueue, double spriteInterval) throws GameEngineException {
        LevelAction levelAction = addAction("SpawnWaveAction");
        Set<LevelAction> levelActions = new HashSet<>();
        levelActions.add(levelAction);
        Map<String, Object> conditionParameter = new HashMap<>();
        conditionParameter.put("time", (double) 0);
        conditionParameter.put("action", levelActions);
        LevelCondition condition = new TemporalCondition(conditionParameter);
        engineDriverManager.addLevelCondition(condition);
        Wave wave = new Wave(spritesWaveQueue, spriteInterval, waveSpawnPoint);
        return wave;
    }

    private LevelAction addAction(String action) {
        try {
            LevelAction levelAction = (LevelAction) Class.forName(ACTION_PATH + action).getConstructor().newInstance();
            engineDriverManager.addLevelAction(levelAction);
            return levelAction;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Instantiates sprite and adds it to Sprite manager
    private void instantiateEngineObject(String component, Map<String, String> componentAttributeMap ) throws GameEngineException {
        SpriteProductsFactory spriteFactory = new SpriteProductsFactory();
        //Initialise default parameter values when none set
        int id = 0, health = 0;
        double xpos = 0, ypos = 0, width = 0, height = 0;
        String healthstrategy = "NoHealth", imagePath = "";

        //Update values for parameters that have been specified
        for(String att : componentAttributeMap.keySet()){

            if(att.equalsIgnoreCase("id")){
                id = Integer.parseInt(componentAttributeMap.get(att));
            }
            if(att.equalsIgnoreCase("Health")){
                healthstrategy = "Health";
                health = Integer.parseInt(componentAttributeMap.get("Health"));
            }
            if(att.equalsIgnoreCase("xpos")){
                xpos = Double.parseDouble(componentAttributeMap.get("xpos"));
            }
            if(att.equalsIgnoreCase("ypos")){
                ypos = Double.parseDouble(componentAttributeMap.get("ypos"));
            }
            if(att.equalsIgnoreCase("Width")){
                width = Double.parseDouble(componentAttributeMap.get("width"));
            }
            if(att.equalsIgnoreCase("Height")){
                height = Double.parseDouble(componentAttributeMap.get("height"));
            }
            if(att.equalsIgnoreCase("ImagePath")){
                imagePath = componentAttributeMap.get("imagepath");
            }
        }
        int finalHealth = health;
        Map<String, Object> prototypeHealthParameter = new HashMap<>() {{ put("health", finalHealth); }};
        LinkedList<Point> path = new LinkedList<>();
        path.add(new Point(0, 0));
        path.add(new Point(10, 0));
        Map<String, Object> prototypeMovementParameter = new HashMap<>();
        prototypeMovementParameter.put("path", path);
        prototypeMovementParameter.put("speed", 1.5);
        engineDriverManager.addSpritePrototype(id, spriteFactory.makeSprite(xpos, ypos, width, height, imagePath, id, strategiesFactory.makeHealth(healthstrategy, prototypeHealthParameter), strategiesFactory.makeMovement("PathMovement", prototypeMovementParameter)));
    }

    public void loadXML(String xmlPath){
        myXMLPath = xmlPath;
        myXMLParser = new XMLParser(TYPE, new File(xmlPath));
    }

    public String getXML(){
        return myXMLPath;
    }
}

