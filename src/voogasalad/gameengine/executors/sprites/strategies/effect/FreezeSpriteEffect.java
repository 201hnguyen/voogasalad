package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.control.action.level.FreezeSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

public class FreezeSpriteEffect implements EffectStrategy {

    private int myDamageValue;
    private double myDuration;
    private boolean isFinished;

    public FreezeSpriteEffect(int damageValue, double duration) {
        myDamageValue = damageValue;
        myDuration = duration;
        isFinished = false;
    }

    @Override
    public LevelAction getAction(int spriteId) throws GameEngineException {
        isFinished = true;
        return new FreezeSpriteAction(spriteId, myDamageValue, myDuration);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public EffectStrategy makeClone() throws GameEngineException {
        return new FreezeSpriteEffect(myDamageValue, myDuration);
    }
}
