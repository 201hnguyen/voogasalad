package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.action.level.TickHealthAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.EffectBuilder;

public class TickHealthEffect implements EffectStrategy {

    private boolean isFinished;
    private int myDamageValue;
    private int myTickCount;
    private double myTickDelay;

    public TickHealthEffect(int damageValue, int tickCount, double tickDelay) {
        myDamageValue = damageValue;
        myTickCount = tickCount;
        myTickDelay = tickDelay;
    }

    @Override
    public LevelAction getAction(int spriteId) throws GameEngineException {
        isFinished = true;
        return new TickHealthAction(spriteId, myDamageValue, myTickCount, myTickDelay);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public EffectStrategy makeClone() throws GameEngineException {
        return new TickHealthEffect(myDamageValue, myTickCount, myTickDelay);
    }
}
