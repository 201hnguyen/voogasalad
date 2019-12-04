package voogasalad.gameengine;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.api.UIActionsProcessor;
import voogasalad.gameengine.configurators.GameConfigurator;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.GameSceneStatus;
import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;
//import voogasalad.gameengine.configurators.EngineConfigurator;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Engine {

//    private EngineConfigurator myEngineConfigurator;
    private Level myCurrentLevel;
    private UIActionsProcessor myCurrentUIActionsProcessor;
    private LevelsController myLevelsController;
    private Document myGameConfigDocument;

    public Engine(Document doc) throws GameEngineException {
        myCurrentUIActionsProcessor = new UIActionsProcessor();
        //        configureWithRealDocument(doc);
        configureWithTestDocument();
        GameConfigurator gameConfigurator = new GameConfigurator(myGameConfigDocument);
        myLevelsController = gameConfigurator.loadLevelsFromXML();
        Level baseDefaultLevel = new LevelBuilder(-1).build();
        myCurrentLevel = baseDefaultLevel;
        loadNextLevel();
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        if (myCurrentLevel.getStatusManager().getGameSceneStatus() == GameSceneStatus.WON) {
            loadNextLevel();
        }
        return myCurrentLevel.execute(elapsedTime);
    }

    private void configureWithRealDocument(Document doc) throws GameEngineException {
//        myEngineConfigurator = new EngineConfigurator();
//        myEngineConfigurator.loadXML(doc);
//        myEngineConfigurator.initializeGame();
//        myCurrentLevel = myEngineConfigurator.initializeEngineForGame();
        myGameConfigDocument = doc;
    }

    private void configureWithTestDocument() throws GameEngineException {
        File testFile = new File("src/resources/player/MockData2.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder ;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(testFile);
            myGameConfigDocument = doc;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new GameEngineException(e, "ConfigurationFailedXML");
        }
    }

    public void loadNextLevel() {
        myCurrentLevel = myLevelsController.getNextLevel(myCurrentLevel);
        myCurrentLevel.getStatusManager().setGameSceneStatus(GameSceneStatus.ONGOING);
        myCurrentUIActionsProcessor.updateLevel(myCurrentLevel);
    }

    public UIActionsProcessor getUIActionProcessor() {
        return myCurrentUIActionsProcessor;
    }

    public List<Sprite> getSpritePrototypes() {
        return myCurrentLevel.getSpriteManager().getSpritePrototypes();
    }

    public List<Sprite> getSpritePrototypesByArchetype(SpriteArchetype spriteArchetype) throws GameEngineException {
        return myCurrentLevel.getSpriteManager().getPrototypesForArchetype(spriteArchetype);
    }

    public GameSceneStatus getCurrentLevelStatus() {
        return myCurrentLevel.getStatusManager().getGameSceneStatus();
    }

}
