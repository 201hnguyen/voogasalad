package voogasalad.gameengine.executors.control.action;

import org.w3c.dom.Element;
import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.List;

public class EditPrototypeImageViewAction implements GameAction, LevelAction {

    private int myPrototypeId;
    private String myImageViewPath;
    private boolean levelUpdateFinished;
    private boolean gameUpdateFinished;

    public EditPrototypeImageViewAction(Element editableObjectRoot) {
        myPrototypeId = Integer.parseInt(editableObjectRoot.getElementsByTagName("PrototypeId").item(0).getTextContent());
        myImageViewPath = editableObjectRoot.getElementsByTagName("EditedField").item(0).getTextContent();
    }

    @Override
    public void execute(Game game) throws GameEngineException {
        List<Sprite> gamePrototypes = game.getCompletePrototypesCollection();
        updateSprites(gamePrototypes);
        gameUpdateFinished = true;
    }

    @Override
    public void execute(Level level) throws GameEngineException {
        List<Sprite> onScreenSprites = level.getSpriteManager().getOnsScreenSprites();
        updateSprites(onScreenSprites);
        levelUpdateFinished = true;
    }

    private void updateSprites(List<Sprite> spritesList) {
        for (Sprite sprite : spritesList) {
            if (sprite.getPrototypeId()== myPrototypeId) {
                sprite.updateImage(myImageViewPath);
            }
        }
    }

    @Override
    public boolean isFinished() {
        return levelUpdateFinished && gameUpdateFinished;
    }
}
