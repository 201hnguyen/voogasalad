package voogasalad.gameengine.engine.utils;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;

public class Verifier {
    public static final String PARAMETERS_IDENTIFIER_RESOURCE_PATH = "resources.engine.StrategyParameters";
    public static final ResourceBundle PARAMETERS_IDENTIFIER_BUNDLE = ResourceBundle.getBundle(PARAMETERS_IDENTIFIER_RESOURCE_PATH);

    public static Object verifyAndGetStrategyParameter(Map<String, Object> parameterMap, String key) throws GameEngineException {
       String[] keyValuePair = PARAMETERS_IDENTIFIER_BUNDLE.getString(key).split(",");
        try {
            return verifyValidKey(parameterMap.get(keyValuePair[0]), Class.forName(keyValuePair[1]));
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //TODO: Delete; currently here so we can see what is going on.
            throw new GameEngineException("InvalidValueInStrategyInitialization");
        }
    }

    public static Object verifyValidKey(Object object, Class<?> expectedType) throws GameEngineException {
        if (object != null && object.getClass().equals(expectedType)) {
            return object;
        } else {
            throw new GameEngineException("InvalidValueInStrategyInitialization");
        }
    }
}