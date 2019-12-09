package voogasalad.gameengine.api;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.action.level.SellTowerAction;
import voogasalad.gameengine.executors.control.gamecontrol.GameActionsRequester;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.control.action.level.AddSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.action.level.RemoveSpriteAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class ActionsProcessor {
    public static final String GAME_ACTIONS_DIRECTORY_ROOT = "voogasalad.gameengine.executors.control.action.game.";
    public static final String LEVEL_ACTIONS_DIRECTORY_ROOT = "voogasalad.gameengine.executors.control.action.level.";
    public static final String ACTIONS_DIRECTORY_ROOT = "voogasalad.gameengine.executors.control.action.";
    public static final String LIVE_GAME_EDITING_CLASS_PATH = "resources/engine/LiveGameEditing";
    public static final ResourceBundle LIVE_GAME_EDITING_BUNDLE = ResourceBundle.getBundle(LIVE_GAME_EDITING_CLASS_PATH);
    public static final String LIVE_GAME_EDITING_TYPE_ROOT_KEY = "ActionTypeDocumentChildRoot";
    public static final String LIVE_GAME_EDITING_EDITABLE_ROOT_KEY = "EditableDocumentChildRoot";
    public static final String PROCESS_LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL = "LiveGameEditingRequestUnsuccessfullyProcessed";
    public static final String LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_GAME = "UnsuccessfulLiveGameEditingOnGameLevel";
    public static final String LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_LEVEL = "UnsuccessfulLiveGameEditingOnLevelLevel";
    public static final String LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_PROTOTYPE = "UnsuccessfulLiveGameEditingOnPrototypeLevel";

    private LevelActionsRequester myLevelActionsRequester;
    private GameActionsRequester myGameActionsRequester;

    public ActionsProcessor(LevelActionsRequester levelActionsRequester, GameActionsRequester gameActionsRequester) {
        myGameActionsRequester = gameActionsRequester;
        myLevelActionsRequester = levelActionsRequester;
    }

    public void processAddSpriteAction(int prototypeId, double xPos, double yPos) {
        LevelAction action = new AddSpriteAction(prototypeId, xPos, yPos);
        myLevelActionsRequester.requestAction(action);
    }

    public void processRemoveSpriteAction(int spriteId) {
        LevelAction action = new RemoveSpriteAction(spriteId);
        myLevelActionsRequester.requestAction(action);
    }

    public void processSellTowerAction(double xpos, double ypos) {
        LevelAction action = new SellTowerAction(xpos, ypos);
        myLevelActionsRequester.requestAction(action);

    }

    public void processGameEditingAction(Document doc) throws GameEngineException {
        Document document = ConfigurationTool.configureWithTestDocument("src/resources/player/EditedSpriteImageView.xml");
//        Document document = doc;
        Element documentRoot = document.getDocumentElement();
        String editGameActionType = documentRoot.getElementsByTagName(LIVE_GAME_EDITING_BUNDLE.getString(LIVE_GAME_EDITING_TYPE_ROOT_KEY)).item(0).getTextContent();
        Element editableObject = (Element) documentRoot.getElementsByTagName(LIVE_GAME_EDITING_BUNDLE.getString(LIVE_GAME_EDITING_EDITABLE_ROOT_KEY)).item(0);
        String methodName = LIVE_GAME_EDITING_BUNDLE.getString(editGameActionType);
        try {
            this.getClass().getDeclaredMethod(methodName, String.class, Element.class).invoke(this, editGameActionType, editableObject);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new GameEngineException(e, PROCESS_LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL);
        }
    }

    private void processEditOnGameAction(String editGameActionType, Element editableObject) throws GameEngineException {
        try {
            GameAction action = (GameAction) Class.forName(GAME_ACTIONS_DIRECTORY_ROOT + editGameActionType).getConstructor(Element.class).newInstance(editableObject);
            myGameActionsRequester.requestAction(action);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new GameEngineException(e, LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_GAME);
        }
    }

    private void processEditOnLevelAction(String editLevelActionType, Element editableObject) throws GameEngineException {
        try {
            LevelAction action = (LevelAction) Class.forName(LEVEL_ACTIONS_DIRECTORY_ROOT + editLevelActionType).getConstructor(Element.class).newInstance(editableObject);
            myLevelActionsRequester.requestAction(action);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new GameEngineException(LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_LEVEL);
        }
    }

    private void processEditPrototypeAction(String editPrototypeActionType, Element editableObject) throws GameEngineException {
        try {
            Object action = Class.forName(ACTIONS_DIRECTORY_ROOT + editPrototypeActionType).getConstructor(Element.class).newInstance(editableObject);
            myLevelActionsRequester.requestAction((LevelAction) action);
            myGameActionsRequester.requestAction((GameAction) action);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new GameEngineException(LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_PROTOTYPE);
        }
    }

    public void updateLevelActionsRequester(LevelActionsRequester levelActionsRequester) {
        myLevelActionsRequester = levelActionsRequester;
    }
}
