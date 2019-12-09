package voogasalad.gameengine.executors.control.action.editprototype;

import org.w3c.dom.Element;
import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.List;

public abstract class EditPrototypeAction implements GameAction, LevelAction {

    public static final String EDITED_FIELD_TAG = "EditedField";
    public static final String PROTOTYPE_ID_TAG = "PrototypeId";

    private boolean levelUpdateFinished;
    private boolean gameUpdateFinished;
    private int myPrototypeId;

    public EditPrototypeAction(Element editableObjectRoot) {
        myPrototypeId = Integer.parseInt(editableObjectRoot.getElementsByTagName(PROTOTYPE_ID_TAG).item(0).getTextContent());
    }

    @Override
    public void execute(Game game) throws GameEngineException {
        List<Sprite> gamePrototypes = game.getCompletePrototypesCollection();
        updateSprites(gamePrototypes);
        gameUpdateFinished = true;
    }

    @Override
    public void execute(Level level) throws GameEngineException {
        List<Sprite> onsScreenSprites = level.getSpriteManager().getOnsScreenSprites();
        updateSprites(onsScreenSprites);
        levelUpdateFinished = true;
    }

    protected abstract void updateSprites(List<Sprite> sprites) throws GameEngineException;

    protected int getPrototypeId() {
        return myPrototypeId;
    }

    @Override
    public boolean isFinished() {
        return levelUpdateFinished && gameUpdateFinished;
    }
}
