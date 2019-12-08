package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.EffectBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;

public class ChunkHealthEffect implements EffectStrategy {
    private EffectBuilder myOriginalBuilder;
    private int damageValue;

    public ChunkHealthEffect(EffectBuilder builder) {
        damageValue = builder.getDamage();
        myOriginalBuilder = builder;
    }

    @Override
    public void apply(Sprite sprite) throws GameEngineException {
        sprite.chunkHealth(damageValue);
    }

    @Override
    public EffectStrategy makeClone() throws GameEngineException {
        return myOriginalBuilder.build();
    }
}
