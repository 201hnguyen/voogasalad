package voogasalad.gameengine.api;

import org.w3c.dom.Document;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.List;

public class Engine {
    private Game myGame;
    private String gameTitle="Bloons TD5";

    public Engine(Document doc) throws GameEngineException {
        myGame = new Game(doc);
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        return myGame.execute(elapsedTime);
    }

    public ActionsProcessor getActionsProcessor() {
        return myGame.getActionsProcessor();
    }

    public List<Sprite> getSpritePrototypes() {
        return myGame.getCurrentLevelSpritePrototypes();
    }

    public List<Sprite> getSpritePrototypesByArchetype(SpriteArchetype spriteArchetype) throws GameEngineException {
        return myGame.getSpritePrototypesByArchetype(spriteArchetype);
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
        return gameTitle;
    }
}