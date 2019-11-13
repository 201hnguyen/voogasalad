package voogasalad.gameengine.engine.factories;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.spritestrategies.health.HealthStrategy;

import java.lang.reflect.InvocationTargetException;

public class StrategiesFactory {

    private final static String CLASS_PATH = "voogasalad.gameengine.engine.spritestrategies.";
    public HealthStrategy makeHealth(String healthStrategy) throws GameEngineException {
        String healthDirectory = "health.";
        try {
            return (HealthStrategy) Class.forName(CLASS_PATH + healthDirectory + healthStrategy).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new GameEngineException(e, "SpriteHealthInitializationFailed");
        }
    }
}
