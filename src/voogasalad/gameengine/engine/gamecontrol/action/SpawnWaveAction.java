package voogasalad.gameengine.engine.gamecontrol.action;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.Wave;

public class SpawnWaveAction implements LevelAction {

    private Wave myWave;
    private boolean isFinished = false;


    @Override
    public void execute(Level level) throws GameEngineException {
        if (myWave == null) {
            if (level.hasNextWave()) {
                myWave = level.getNextWave();
            } else {
                throw new GameEngineException("SpecifyWavesToExecuteAction");
            }
        }

        level.getSpriteManager().makeSpriteFromPrototype(myWave.getSpawnPoint().getX(), myWave.getSpawnPoint().getY(), myWave.getNextSpriteToSpawn());

        if (myWave.isEmpty()) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
