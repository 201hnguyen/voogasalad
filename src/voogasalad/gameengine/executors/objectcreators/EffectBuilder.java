package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.effect.EffectStrategy;

public class EffectBuilder implements StrategyBuilder {
    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.effect.";
    private static final String DEFAULT_TYPE = "NoEffect";

    private int myDamageValue;
    private String myEffectType;

    public EffectBuilder setType(String effectType) {
        myEffectType = effectType;
        return this;
    }

    public String getType() {
        return myEffectType;
    }

    public EffectBuilder setDamage(int value) {
        myDamageValue = value;
        return this;
    }

    public int getDamage() {
        return myDamageValue;
    }

    @Override
    public EffectStrategy build() throws GameEngineException {
        if(myEffectType == null) {
            myEffectType = DEFAULT_TYPE;
        }
        try {
            return (EffectStrategy) Class.forName(CLASS_PATH + myEffectType).getConstructor(EffectBuilder.class).newInstance(this);
        } catch (Exception e) {
            throw new GameEngineException(e, "SpriteEffectInitializationFailed");
        }
    }
}
