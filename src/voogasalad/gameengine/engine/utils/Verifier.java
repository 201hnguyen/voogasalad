package voogasalad.gameengine.engine.utils;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.util.Map;
import java.util.ResourceBundle;

public class Verifier {
    public static final String STRATEGY_PARAMETERS_IDENTIFIER_RESOURCE_PATH = "resources.engine.StrategyParameters";
    public static final String CONDITION_PARAMETERS_IDENTIFIER_RESOURCE_PATH = "resources.engine.ConditionParameters";

    public static Object verifyAndGetStrategyParameter(Map<String, Object> parameterMap, String key) throws GameEngineException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(STRATEGY_PARAMETERS_IDENTIFIER_RESOURCE_PATH);
        String[] keyValuePair = resourceBundle.getString(key).split(",");
       return verifyAndGetHelper(parameterMap, keyValuePair);
    }

    public static Object verifyAndGetActionParameter(Map<String, Object> parameterMap, String key) throws GameEngineException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(CONDITION_PARAMETERS_IDENTIFIER_RESOURCE_PATH);
        String[] keyValuePair = resourceBundle.getString(key).split(",");
        return verifyAndGetHelper(parameterMap, keyValuePair);
    }


    public static Object verifyValidKey(Object object, Class<?> expectedType) throws GameEngineException {
        //TODO: Rework how this process works; there has to be a better way of doing this with reflection or something
        if (object != null && (object.getClass().equals(expectedType))) {
            return object;
        } else {
            for (Class<?> classInterface : object.getClass().getInterfaces()) {
                if(classInterface.equals(expectedType)) {
                    return object;
                }
            }
        }
        throw new GameEngineException("InvalidValueInStrategyInitialization");
    }

    private static Object verifyAndGetHelper(Map<String, Object> parameterMap, String[] keyValuePair) throws GameEngineException {
        try {
            return verifyValidKey(parameterMap.get(keyValuePair[0]), Class.forName(keyValuePair[1]));
        } catch (ClassNotFoundException | GameEngineException e) {
            e.printStackTrace(); //TODO: Delete; currently here so we can see what is going on.
            throw new GameEngineException("InvalidValueInStrategyInitialization");
        }
    }
}