package voogasalad.gameplayer;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    public static final int WINDOW_SIZE = 500;
    private String myXMLPath;
    private XMLParser myXMLParser;
    private Stage myStage;
    private Group mapRoot;
    private SpriteProductsFactory spriteFactory;
    private StrategiesFactory strategiesFactory;
    private SpriteManager spriteManager;

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
        spriteManager = new JavaFXSpriteManager();
    }

    public Level startGame() throws GameEngineException {
        return displayMapFromXML();
    }

    private Level displayMapFromXML() throws GameEngineException {
        Scene scene = new Scene(mapRoot, 1000, 800);
        myStage.setScene(scene);
        myStage.show();
        String[] componentTypes = {"Tower", "Enemy"};
        for(String component: componentTypes) {
            ArrayList<Map<String, String>> componentList = myXMLParser.getAttributesByTagName(component);
            for(int i = 0; i < componentList.size(); i++){
                instantiateEngineObject(component, componentList.get(i));
            }
        }

        Point wave0SpawnPoint = new Point(250, 300);
        Point wave1SpawnPoint = new Point(400, 400);

        LevelAction wave0SpawnAction = new SpawnWaveAction();
        LevelAction wave1SpawnAction = new SpawnWaveAction();

        Set<LevelAction> levelActions = new HashSet<>();
        Set<LevelAction> levelActions1 = new HashSet<>();
        levelActions.add(wave0SpawnAction);
        levelActions1.add(wave1SpawnAction);
        System.out.println(levelActions.getClass().getName());

        Map<String, Object> condition0Parameter = new HashMap<>() {{ put("time", (double) 0); put("action", levelActions); }};
        Map<String, Object> condition1Parameter = new HashMap<>() {{ put("time", (double) 3); put("action", levelActions1); }};
        LevelCondition condition0 = new TemporalCondition(condition0Parameter);
        LevelCondition condition1 = new TemporalCondition(condition1Parameter);

        Set<LevelCondition> levelConditionsSet = new HashSet<>() {{ add(condition0); add(condition1); }};

        Queue<Integer> spritesWave0Queue = new LinkedList<>() {{ add(0); add(1); add(0); }};
        Queue<Integer> spritesWave1Queue = new LinkedList<>() {{ add(1); add(0); add(1); }};

        Wave wave0 = new Wave(spritesWave0Queue, 1.0, wave0SpawnPoint);
        Wave wave1 = new Wave(spritesWave1Queue, 0.5, wave1SpawnPoint);
        List<Wave> wavesList = new ArrayList<>() {{ add(wave0); add(wave1); }};

        WaveManager waveManager = new WaveManager(wavesList);
        StatusManager statusManager = new StatusManager();
        ConditionsManager conditionsManager = new ConditionsManager(levelConditionsSet);
        ActionsManager actionsManager = new ActionsManager();

        Level level = new Level(spriteManager, waveManager, statusManager, conditionsManager, actionsManager);
        return level;
    }

    //Instantiates sprite and adds it to Sprite manager
    private void instantiateEngineObject(String component, Map<String, String> componentAttributeMap ) throws GameEngineException {
        SpriteProductsFactory spriteFactory = new SpriteProductsFactory();
        //Initialise default parameter values when none set
        int id = 0, health = 0;
        double xpos = 0, ypos = 0;
        String healthstrategy = "NoHealth";

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
                xpos = Double.parseDouble(componentAttributeMap.get("ypos"));
            }
        }
        int finalHealth = health;
        Map<String, Object> prototype0HealthParameter = new HashMap<>() {{ put("health", finalHealth); }};
        spriteManager.addSpritePrototype(id, spriteFactory.makeSprite(xpos, ypos, id, strategiesFactory.makeHealth(healthstrategy, prototype0HealthParameter)));

    }

//    private void instantiateComponentAndAddToList(String component) {
//        Map<String, Object> prototype0HealthParameter = new HashMap<>() {{ put("health", 10); }};
//        Map<String, Object> prototype1HealthParameter = new HashMap<>() {{ put("health", 15); }};
//
//        HealthStrategy prototype0HealthStrategy = new Health(prototype0HealthParameter);
//        HealthStrategy prototype1HealthStrategy = new Health(prototype1HealthParameter);
//
//        SpriteManager spriteManager = new JavaFXSpriteManager();
//        Sprite prototype0 = new JavaFXSprite(0, 0, 0, prototype0HealthStrategy);
//        Sprite prototype1 = new JavaFXSprite(0, 0, 0, prototype1HealthStrategy);
//        spriteManager.addSpritePrototype(0, prototype0);
//        spriteManager.addSpritePrototype(1, prototype1);
//    }


    public void loadXML(String xmlPath){
        myXMLPath = xmlPath;
        myXMLParser = new XMLParser(TYPE, new File(xmlPath));
    }

    public String getXML(){
        return myXMLPath;
    }
}
