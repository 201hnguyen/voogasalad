package voogasalad.gameengine.engine.utils;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.util.Map;
import java.util.ResourceBundle;

public class Verifier {
    public static final String PARAMETERS_IDENTIFIER_RESOURCE_PATH = "resources.engine.StrategyParameters";
    public static final ResourceBundle PARAMETERS_IDENTIFIER_BUNDLE = ResourceBundle.getBundle(PARAMETERS_IDENTIFIER_RESOURCE_PATH);

    public static Object verifyAndGetStrategyParameter(Map<String, Object> parameterMap, String key) throws GameEngineException {
       String[] keyValuePair = PARAMETERS_IDENTIFIER_BUNDLE.getString(key).split(",");
           return checkNotNull(parameterMap.get(keyValuePair[0]));
    }

    public static Object checkNotNull(Object object) throws GameEngineException {
        if (object != null) {
            return object;
        } else {
            throw new GameEngineException("InvalidValueInStrategyInitialization");
        }
    }
}
