package voogasalad.gameengine.executors.control.gamecontrol;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import voogasalad.gameengine.api.ActionsProcessor;
import voogasalad.gameengine.api.GameSceneObject;
//import voogasalad.gameengine.configurators.EngineConfigurator;
import voogasalad.gameengine.configurators.GameConfigurator;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.control.gamecontrol.controllers.GameLevelsController;
import voogasalad.gameengine.executors.control.gamecontrol.controllers.GameRulesController;
import voogasalad.gameengine.executors.control.levelcontrol.GameSceneStatus;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class Game {
    //    private EngineConfigurator myEngineConfigurator;
    private Level myCurrentLevel;
    private GameLevelsController myGameLevelsController;
    private GameRulesController myGameRulesController;
    private Document myGameConfigDocument;
    private ActionsProcessor myCurrentActionsProcessor;
    private GameActionsRequester myGameActionsRequester;
    private boolean switchedLevel;
    private List<Sprite> myCompletePrototypesCollection;

    public Game(Document gameConfigDocument) throws GameEngineException {
        myGameRulesController = new GameRulesController();
        myGameActionsRequester = new GameActionsRequester();
        myGameConfigDocument = configureWithTestDocument();
        GameConfigurator gameConfigurator = new GameConfigurator(myGameConfigDocument);
        myCompletePrototypesCollection = gameConfigurator.getGamePrototypesCollection();
        myGameRulesController.addGameConditionsAsCollection(gameConfigurator.configureGameConditions());
        myGameLevelsController = gameConfigurator.loadLevelsFromXML();
        myCurrentLevel = myGameLevelsController.loadBaseLevel();
        myCurrentActionsProcessor = new ActionsProcessor(myCurrentLevel.getActionsRequester(), myGameActionsRequester);
        switchedLevel = false;
        loadNextLevel();
    }

    private Document configureWithRealDocument(Document doc) throws GameEngineException {
//        myEngineConfigurator = new EngineConfigurator();
//        myEngineConfigurator.loadXML(doc);
//        myEngineConfigurator.initializeGame();
//        myCurrentLevel = myEngineConfigurator.initializeEngineForGame();
        return doc;
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        switchedLevel = false;
        myGameRulesController.addGameActions(myGameActionsRequester.getRequestedActions());
        myGameRulesController.checkConditionsAndRunGameActions(this);
        return myCurrentLevel.execute(elapsedTime);
    }

    public void loadNextLevel() {
        myCurrentLevel = myGameLevelsController.getNextLevel(myCurrentLevel);
        myCurrentLevel.getStatusManager().setGameSceneStatus(GameSceneStatus.ONGOING);
        myCurrentActionsProcessor.updateLevel(myCurrentLevel);
        switchedLevel = true;
    }

    public GameSceneStatus getCurrentLevelStatus() {
        return myCurrentLevel.getStatusManager().getGameSceneStatus();
    }

    public ActionsProcessor getActionsProcessor() {
        return myCurrentActionsProcessor;
    }

    public List<Sprite> getCurrentLevelSpritePrototypes() {
        return myCurrentLevel.getSpriteManager().getSpritePrototypes();
    }

    public List<Sprite> getCompletePrototypesCollection() {
        return myCompletePrototypesCollection;
    }

    public List<Sprite> getSpritePrototypesByArchetype(SpriteArchetype spriteArchetype) throws GameEngineException {
        return myCurrentLevel.getSpriteManager().getCopyPrototypesForArchetype(spriteArchetype);
    }

    public String getCurrentLevelBackgroundPath() {
        return myCurrentLevel.getBackgroundPath();
    }

    private Document configureWithTestDocument() throws GameEngineException {
        File testFile = new File("src/resources/player/MockData.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder ;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(testFile);
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new GameEngineException(e, "ConfigurationFailedXML");
        }
    }

    public boolean didLevelSwitch() {
        boolean ret = switchedLevel;
        switchedLevel = false;
        return ret;
    }

    public int getCurrentTotalGameScore() {
        return myGameLevelsController.getTotalScoreForAllLevels();
    }

    public Collection<GameCondition> getAllGameConditions() {
        return myGameRulesController.getGameConditions();
    }

    public List<Level> getLevels() {
        return myGameLevelsController.getLevels();
    }

    public int getCurrentLevelId() {
        return myCurrentLevel.getLevelId();
    }
}
