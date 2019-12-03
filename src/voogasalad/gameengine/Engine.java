package voogasalad.gameengine;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.api.UIActionsProcessor;
import voogasalad.gameengine.configurators.test.GameConfigurator;
import voogasalad.gameengine.configurators.test.LevelConfigurator;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.GameSceneStatus;
import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.configurators.EngineConfigurator;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Engine {

    private EngineConfigurator myEngineConfigurator;
    private Level myCurrentLevel;
    private UIActionsProcessor myCurrentUIActionsProcessor;
    private List<Level> myLevels;

    public Engine(Document doc) throws GameEngineException {
        myCurrentLevel = new LevelBuilder().build();
        //        configureWithRealDocument(doc);
        configureWithTestDocument();
        myCurrentUIActionsProcessor = new UIActionsProcessor(myCurrentLevel);
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        if (myCurrentLevel.getStatusManager().getGameSceneStatus() == GameSceneStatus.WON) {
            myCurrentLevel = myLevels.get(1);
        }
        return myCurrentLevel.execute(elapsedTime);
    }

    private void configureWithRealDocument(Document doc) throws GameEngineException {
        myEngineConfigurator = new EngineConfigurator();
        myEngineConfigurator.loadXML(doc);
        myEngineConfigurator.initializeGame();
        myCurrentLevel = myEngineConfigurator.initializeEngineForGame();
    }

    private void configureWithTestDocument() {
        GameConfigurator gameConfigurator = new GameConfigurator();
        File testFile = new File("src/resources/player/MockData2.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder ;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(testFile);
            myLevels = gameConfigurator.loadLevelsFromXML(doc);
            myCurrentLevel = myLevels.get(0);
            myCurrentLevel.getStatusManager().setGameSceneStatus(GameSceneStatus.ONGOING);
        } catch (ParserConfigurationException | SAXException | IOException | GameEngineException e) {
            e.printStackTrace(); //FIXME
        }
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
}
