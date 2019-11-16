package voogasalad.gameengine.engine.gamecontrol.action;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;

public class SpawnSpriteAction implements LevelAction {
    public void execute(Level level) throws GameEngineException {
        level.getSpriteManager().makeSpriteFromPrototype(level.getSpawnPoint().getX(), level.getSpawnPoint().getY(), level.getNextSpriteInQueue());
    }
}
