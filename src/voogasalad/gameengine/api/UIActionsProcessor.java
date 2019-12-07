package voogasalad.gameengine.api;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.gamecontrol.GameActionsRequester;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.control.action.level.AddSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.action.level.RemoveSpriteAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class UIActionsProcessor {
    public static final String GAME_ACTIONS_DIRECTORY_ROOT = "voogasalad.gameengine.executors.control.action.game.";
    private LevelActionsRequester myLevelActionsRequester;
    private GameActionsRequester myGameActionsRequester;

    public UIActionsProcessor(LevelActionsRequester levelActionsRequester, GameActionsRequester gameActionsRequester) {
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

    public void editOnGameRootLevel(Document doc) throws GameEngineException {
        Document document = configureWithTestDocument();
        Element documentRoot = document.getDocumentElement();
        String editGameActionType = documentRoot.getElementsByTagName("EditActionType").item(0).getTextContent();
        Element editableObject = (Element) documentRoot.getElementsByTagName("EditableObject").item(0);
        try {
            GameAction action = (GameAction) Class.forName(GAME_ACTIONS_DIRECTORY_ROOT + editGameActionType).getConstructor(Element.class).newInstance(editableObject);
            myGameActionsRequester.requestAction(action);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //FIXME
        }
    }

    private Document configureWithTestDocument() throws GameEngineException {
        File testFile = new File("src/resources/player/EditedLevel.xml");
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
    public void updateLevel(Level level) {
        myLevelActionsRequester = level.getActionsRequester();
    }
}
