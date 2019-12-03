package voogasalad.gameengine.executors.gamecontrol;

import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

public interface GameScene {
    GameSceneObject execute(double elapsedTime) throws GameEngineException;
}
