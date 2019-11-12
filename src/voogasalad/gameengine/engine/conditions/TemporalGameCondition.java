package voogasalad.gameengine.engine.conditions;

import voogasalad.gameengine.engine.action.GameAction;
import voogasalad.gameengine.engine.elements.Level;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.ActionsFactory;

public class TemporalGameCondition implements GameCondition {

    int myMarkedTime;
    String myActionName;
    ActionsFactory myActionsFactory;

    public TemporalGameCondition(int markedTime, String actionName) {
        myMarkedTime = markedTime;
        myActionName = actionName;
        myActionsFactory = new ActionsFactory();
    }

    @Override
    public boolean isMet(Level level) {
        return level.getElapsedTime() == myMarkedTime;
    }

    @Override
    public void executeAction(Level level) throws GameEngineException {
        GameAction action = myActionsFactory.makeGameAction(myActionName);
        action.executeAction(level);
    }
}
