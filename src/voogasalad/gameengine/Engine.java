package voogasalad.gameengine;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.api.UIActionsProcessor;
import voogasalad.gameengine.configurators.test.LevelConfigurator;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
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
    private LevelConfigurator myLevelConfigurator;
    private Level myCurrentLevel;
    private UIActionsProcessor myCurrentUIActionsProcessor;


    public Engine(Document doc) throws GameEngineException {
        myCurrentLevel = new LevelBuilder().build(); //DEFAULT LEVEL
        //        configureWithRealDocument(doc);
        configureWithTestDocument();
        myCurrentUIActionsProcessor = new UIActionsProcessor(myCurrentLevel);
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        return myCurrentLevel.execute(elapsedTime);
    }

    private void configureWithRealDocument(Document doc) throws GameEngineException {
        myEngineConfigurator = new EngineConfigurator();
        myEngineConfigurator.loadXML(doc);
        myEngineConfigurator.initializeGame();
        myCurrentLevel = myEngineConfigurator.initializeEngineForGame();
    }

    private void configureWithTestDocument() {
        myLevelConfigurator = new LevelConfigurator();
        File testFile = new File("src/resources/player/MockData2.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder ;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(testFile);
            myLevelConfigurator.loadXML(doc);
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
