package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.control.action.level.ChunkAreaAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

public class ChunkAreaEffect implements EffectStrategy {

    private int myDamageValue;
    private double myRadius;
    private boolean isFinished;

    public ChunkAreaEffect(int damageValue, double radius) {
        myDamageValue = damageValue;
        myRadius = radius;
        isFinished = false;
    }

    @Override
    public LevelAction getAction(int spriteId) throws GameEngineException {
        isFinished = true;
        return new ChunkAreaAction(spriteId, myDamageValue, myRadius);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public EffectStrategy makeClone() throws GameEngineException {
        return new ChunkAreaEffect(myDamageValue, myRadius);
    }
}
