package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.EffectBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;

public class NoEffect implements EffectStrategy {

    public NoEffect(EffectBuilder builder) {
        //do nothing
    }

    @Override
    public void apply(Sprite sprite) throws GameEngineException {
        //do nothing
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public EffectStrategy makeClone() {
        return new NoEffect(new EffectBuilder());
    }
}
