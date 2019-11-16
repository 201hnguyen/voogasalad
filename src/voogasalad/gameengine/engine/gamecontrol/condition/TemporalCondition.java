package voogasalad.gameengine.engine.gamecontrol.condition;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.utils.Verifier;

import java.util.Map;

public class TemporalCondition implements LevelCondition {
    private double myMarkedtime;
    private LevelAction myAction;

    public TemporalCondition(Map<String, Object> parameters) throws GameEngineException {
        myMarkedtime = (double) Verifier.verifyAndGetActionParameter(parameters, "myMarkedTime");
        myAction = (LevelAction) Verifier.verifyAndGetActionParameter(parameters, "myAction");
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getElapsedTime() >= myMarkedtime;
    }

    @Override
    public LevelAction getAction() {
        return myAction;
    }
}
