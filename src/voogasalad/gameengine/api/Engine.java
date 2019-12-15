package voogasalad.gameengine.api;

import org.w3c.dom.Document;
import voogasalad.gameengine.executors.control.levelcontrol.Status;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.List;

/**
 * Class: Engine
 * Purpose: Provides and API for the Player to generally communicates with the Engine and gets any necessary object that
 * is not reloaded at every game scene.
 * Assumptions: Assumes only one Game at this time (however, multiple Game functionality can be easily added).
 * Dependencies: Dependent on the Game class as this is mostly the API/the GameController that delegates actions to Games.
 * Example of how to use: Player can poll the engine for game level switches and then get the new level background and sound
 * background path to load once and not have to re-load at every single game scene.
 * Other details: N/A
 */
public class Engine {
    private Game myGame;

    /**
     * Purpose:
     * Assumptions:
     * @param gameDocument
     * @throws GameEngineException
     */
    public Engine(Document gameDocument) throws GameEngineException {
        myGame = new Game(gameDocument);
    }

    /**
     * Purpose:
     * Assumptions:
     * @param elapsedTime
     * @return
     * @throws GameEngineException
     */
    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        return myGame.execute(elapsedTime);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public ActionsProcessor getActionsProcessor() {
        return myGame.getActionsProcessor();
    }

    /**
     * Purpose:
     * Assumptions:
     * @param spriteArchetype
     * @return
     * @throws GameEngineException
     */
    public List<Sprite> getSpritePrototypesByArchetype(SpriteArchetype spriteArchetype) throws GameEngineException {
        return myGame.getCopySpritePrototypesByArchetype(spriteArchetype);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public String getCurrentLevelBackgroundPath() {
        return myGame.getCurrentLevelBackgroundPath();
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public boolean didLevelSwitch() {
        return myGame.didLevelSwitch();
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getCurrentTotalGameScore() {
        return myGame.getCurrentTotalGameScore();
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public String getGameTitle() {
        return myGame.getGameTitle();
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getCurrentLevelId() {
        return myGame.getCurrentLevelId();
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public String getCurrentLevelSoundPath() { return myGame.getCurrentLevelSoundPath(); }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public Status getGameStatus() {
        return myGame.getGameStatus();
    }

}