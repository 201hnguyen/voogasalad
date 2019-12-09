package voogasalad.gameengine.executors.exceptions;

public class GameEngineException extends Exception {

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
        return myMessageKey;
    }

    public Throwable getException() {
        return myThrowableEx;
    }
}
