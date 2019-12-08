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
    public static final String ACTIONS_DIRECTORY_ROOT = "voogasalad.gameengine.executors.control.action.";
    public static final String GAME_DIRECTORY_EXTENSION = "game.";
    public static final String LEVEL_DIRECTORY_EXTENSION = "level.";
    public static final String PROTOTYPE_EDIT_DIRECTORY_EXTENSION = "editprototype.";
    public static final String LIVE_GAME_EDITING_CLASS_PATH = "resources.engine.LiveGameEditing";
    public static final ResourceBundle LIVE_GAME_EDITING_BUNDLE = ResourceBundle.getBundle(LIVE_GAME_EDITING_CLASS_PATH);

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
        Element documentRoot = document.getDocumentElement();
        String editGameActionType = documentRoot.getElementsByTagName("EditActionType").item(0).getTextContent();
        Element editableObject = (Element) documentRoot.getElementsByTagName("EditableObject").item(0);
        String methodName = LIVE_GAME_EDITING_BUNDLE.getString(editGameActionType);
        try {
            this.getClass().getDeclaredMethod(methodName, String.class, Element.class).invoke(this, editGameActionType, editableObject);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //FIXME
        }
    }

    private void processEditOnGameAction(String editGameActionType, Element editableObject) throws GameEngineException {
        try {
            GameAction action = (GameAction) Class.forName(ACTIONS_DIRECTORY_ROOT + GAME_DIRECTORY_EXTENSION + editGameActionType).getConstructor(Element.class).newInstance(editableObject);
            myGameActionsRequester.requestAction(action);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //FIXME
        }
    }

    private void processEditOnLevelAction(String editLevelActionType, Element editableObject) {
        try {
            LevelAction action = (LevelAction) Class.forName(ACTIONS_DIRECTORY_ROOT + LEVEL_DIRECTORY_EXTENSION + editLevelActionType).getConstructor(Element.class).newInstance(editableObject);
            myLevelActionsRequester.requestAction(action);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //FIXME
        }
    }

    private void processEditOnPrototypeAction(String editPrototypeActionType, Element editableObject) {
        try {
            Object action = Class.forName(ACTIONS_DIRECTORY_ROOT + PROTOTYPE_EDIT_DIRECTORY_EXTENSION  + editPrototypeActionType).getConstructor(Element.class).newInstance(editableObject);
            myLevelActionsRequester.requestAction((LevelAction) action);
            myGameActionsRequester.requestAction((GameAction) action);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //FIXME
        }
    }

    public void updateLevel(Level level) {
        myLevelActionsRequester = level.getActionsRequester();
    }
}
