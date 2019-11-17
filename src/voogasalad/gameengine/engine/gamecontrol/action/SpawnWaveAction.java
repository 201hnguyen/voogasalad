package voogasalad.gameengine.engine.gamecontrol.action;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.Wave;

public class SpawnWaveAction implements LevelAction {

    private Wave myWave;
    private boolean isFinished;

    @Override
    public void execute(Level level) throws GameEngineException {
        if (myWave == null) {
            setupWave(level);
        }
        level.getSpriteManager().makeSpriteFromPrototype(myWave.getSpawnPoint().getX(), myWave.getSpawnPoint().getY(), myWave.getNextSpriteToSpawn());
        checkActionFinished();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    private void setupWave(Level level) throws GameEngineException {
        if (level.hasNextWave()) {
            myWave = level.getNextWave();
            isFinished = false;
        } else {
            throw new GameEngineException("SpecifyWavesToExecuteAction");
        }
    }

    private void checkActionFinished() {
        if (myWave.isEmpty()) {
            isFinished = true;
        }
    }
}
