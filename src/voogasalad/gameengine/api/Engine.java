package voogasalad.gameengine.api;

import org.w3c.dom.Document;
import voogasalad.gameengine.executors.control.levelcontrol.Status;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.List;

public class Engine {
    private Game myGame;

    public Engine(Document gameDocument) throws GameEngineException {
        myGame = new Game(gameDocument);
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        return myGame.execute(elapsedTime);
    }

    public ActionsProcessor getActionsProcessor() {
        return myGame.getActionsProcessor();
    }

    public List<Sprite> getSpritePrototypesByArchetype(SpriteArchetype spriteArchetype) throws GameEngineException {
        return myGame.getCopySpritePrototypesByArchetype(spriteArchetype);
    }

    public String getCurrentLevelBackgroundPath() {
        return myGame.getCurrentLevelBackgroundPath();
    }

    public boolean didLevelSwitch() {
        return myGame.didLevelSwitch();
    }

    public int getCurrentTotalGameScore() {
        return myGame.getCurrentTotalGameScore();
    }

    public String getGameTitle() {
        return myGame.getGameTitle();
    }

    public int getCurrentLevelId() {
        return myGame.getCurrentLevelId();
    }

    public String getCurrentLevelSound() { return myGame.getCurrentLevelSoundPath(); }

    public Status getGameStatus() {
        return myGame.getGameStatus();
    }

}