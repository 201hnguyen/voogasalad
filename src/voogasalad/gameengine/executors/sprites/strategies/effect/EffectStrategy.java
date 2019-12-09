package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;

public interface EffectStrategy {
    void apply(Sprite sprite) throws GameEngineException;
    boolean isFinished();
    EffectStrategy makeClone() throws GameEngineException;
}
