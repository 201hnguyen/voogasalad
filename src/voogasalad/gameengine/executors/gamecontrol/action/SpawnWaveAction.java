package voogasalad.gameengine.executors.gamecontrol.action;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.Wave;
import voogasalad.gameengine.executors.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.executors.sprites.SpriteManager;

public class SpawnWaveAction implements LevelAction {

    private Wave myWave;
    private boolean isFinished;

    @Override
    public void execute(Level level) throws GameEngineException {
        SpriteManager spriteManager = level.getSpriteManager();
        WaveManager waveManager = level.getWaveManager();
        double elapsedTime = level.getStatusManager().getElapsedTimeSinceLastFrame();
        if (myWave == null) {
            setupWave(waveManager);
        }
        if (! isFinished) {
            isFinished = myWave.spawnNextSprite(spriteManager, elapsedTime);
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    private void setupWave(WaveManager waveManager) {
        if (waveManager.hasNextWave()) {
            isFinished = false;
            myWave = waveManager.getNextWave();
        } else {
            isFinished = true;
        }
    }
}
