package voogasalad.gameengine.executors.control.action.sprite;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;

public interface SpriteAction {
    void execute(Sprite sprite) throws GameEngineException;
}
