package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.configurators.ConditionsConfigurator;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.List;

public class DoNothingActionForTest implements GameAction{


    @Override
    public void execute(Game game) throws GameEngineException {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
