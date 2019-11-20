package voogasalad.gameengine.engine.gamecontrol.condition;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.utils.Verifier;

import java.util.Map;
import java.util.Set;

public class TemporalCondition implements LevelCondition {
    private double myMarkedTime;
    private Set<LevelAction> myActions;

    public TemporalCondition(Map<String, Object> parameters) throws GameEngineException {
        myMarkedTime = (double) Verifier.verifyAndGetConditionParameter(parameters, "myMarkedTime");
        myActions = (Set<LevelAction>) Verifier.verifyAndGetConditionParameter(parameters, "myActions");
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getStatusManager().getTotalElapsedTime() >= myMarkedTime;
    }

    @Override
    public Set<LevelAction> getActions() {
        return myActions;
    }
}
