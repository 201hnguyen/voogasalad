package voogasalad.gameengine.engine.factories;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.spritestrategies.health.HealthStrategy;

import java.lang.reflect.InvocationTargetException;

public class StrategiesFactory {

    private static final String CLASS_PATH = "voogasalad.gameengine.engine.spritestrategies.";
    private static final String HEALTH_DIRECTORY = "health.";

    public HealthStrategy makeHealth(String healthStrategy) throws GameEngineException {
        try {
            return (HealthStrategy) Class.forName(CLASS_PATH + HEALTH_DIRECTORY + healthStrategy).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new GameEngineException(e, "SpriteHealthInitializationFailed");
        }
    }
}
