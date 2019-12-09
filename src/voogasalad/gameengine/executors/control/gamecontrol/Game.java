package voogasalad.gameengine.executors.control.gamecontrol;

import org.w3c.dom.Document;
import voogasalad.gameengine.api.ActionsProcessor;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.configurators.GameConfigurator;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.control.gamecontrol.controllers.GameLevelsController;
import voogasalad.gameengine.executors.control.gamecontrol.controllers.GameRulesController;
import voogasalad.gameengine.executors.control.levelcontrol.Status;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.utils.ConfigurationTool;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

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
    private Status myStatus;
    private String myGameTitle;

    public Game(Document gameConfigDocument) throws GameEngineException {
        myStatus = Status.ONGOING;
        myGameRulesController = new GameRulesController();
        myGameActionsRequester = new GameActionsRequester();
        myGameConfigDocument = ConfigurationTool.configureWithTestDocument("src/resources/player/MockGame.xml");
        GameConfigurator gameConfigurator = new GameConfigurator(myGameConfigDocument);
        try {
            myGameTitle = gameConfigurator.configureGameTitle();
        } catch (NullPointerException e) {
            myGameTitle = "";
        }
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
        myCurrentLevel.getStatusManager().setGameSceneStatus(Status.ONGOING);
        myCurrentActionsProcessor.updateLevelActionsRequester(myCurrentLevel.getActionsRequester());
        switchedLevel = true;
    }

    public Status getCurrentLevelStatus() {
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

    public List<Sprite> getCopySpritePrototypesByArchetype(SpriteArchetype spriteArchetype) throws GameEngineException {
        return myCurrentLevel.getSpriteManager().getCopyPrototypesForArchetype(spriteArchetype);
    }

    public String getCurrentLevelBackgroundPath() {
        return myCurrentLevel.getBackgroundPath();
    }

    public String getCurrentLevelSoundPath() { return myCurrentLevel.getSoundPath(); }

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

    public void setGameStatus(Status status) {
        myStatus = status;
    }

    public Status getGameStatus() {
        return myStatus;
    }

    public String getGameTitle() {
        return myGameTitle;
    }
}
