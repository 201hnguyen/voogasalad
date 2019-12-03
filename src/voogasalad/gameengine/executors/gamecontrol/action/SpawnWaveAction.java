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
        isFinished = myWave.spawnNextSprite(spriteManager, elapsedTime);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    private void setupWave(WaveManager waveManager) throws GameEngineException {
        if (waveManager.hasNextWave()) {
            isFinished = false;
            myWave = waveManager.getNextWave();
        } else {
            throw new GameEngineException("SpecifyWavesToExecuteAction"); //TODO: This exception is thrown incorrectly for case where you don't have next wave but continuous condition checking
        }
    }
}
