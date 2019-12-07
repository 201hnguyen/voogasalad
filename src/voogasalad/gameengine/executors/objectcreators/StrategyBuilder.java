package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

public interface StrategyBuilder {
    Object build() throws GameEngineException;
}
