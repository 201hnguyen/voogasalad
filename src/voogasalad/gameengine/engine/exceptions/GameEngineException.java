package voogasalad.gameengine.engine.exceptions;

import java.util.ResourceBundle;

public class GameEngineException extends Exception {

    private static final String EXCEPTIONS_MESSAGES_RESOURCE_PATH = "resources.ExceptionsEnglish";
    private static final ResourceBundle EXCEPTION_MESSAGES_BUNDLE = ResourceBundle.getBundle(EXCEPTIONS_MESSAGES_RESOURCE_PATH);

    private final Throwable myThrowableEx;
    private final String myMessageKey;


    public GameEngineException(Throwable e, String exceptionMessageKey) {
        myThrowableEx = e;
        myMessageKey = exceptionMessageKey;
    }

    public GameEngineException(String exceptionMessageKey) {
        myThrowableEx = new Throwable();
        myMessageKey = exceptionMessageKey;
    }

    public String getMessage() {
        return EXCEPTION_MESSAGES_BUNDLE.getString(myMessageKey);
    }
}
