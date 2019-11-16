package voogasalad.gameengine.engine.gamecontrol.condition;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.utils.Verifier;

import java.util.Map;

public class AbsoluteTemporalCondition implements LevelCondition {
    private double myMarkedTime;
    private LevelAction myAction;

    public AbsoluteTemporalCondition(Map<String, Object> parameters) throws GameEngineException {
        myMarkedTime = (double) Verifier.verifyAndGetConditionParameter(parameters, "myMarkedTime");
        myAction = (LevelAction) Verifier.verifyAndGetConditionParameter(parameters, "myAction");
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getElapsedTime() >= myMarkedTime;
    }

    @Override
    public LevelAction getAction() {
        return myAction;
    }
}
