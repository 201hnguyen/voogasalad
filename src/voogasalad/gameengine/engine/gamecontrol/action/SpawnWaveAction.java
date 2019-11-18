package voogasalad.gameengine.engine.gamecontrol.action;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.Wave;
import voogasalad.gameengine.engine.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.engine.sprites.SpriteManager;

public class SpawnWaveAction implements LevelAction {

    private Wave myWave;
    private boolean isFinished;

    @Override
    public void execute(Level level) throws GameEngineException {
        SpriteManager spriteManager = level.getSpriteManager();
        WaveManager waveManager = level.getWaveManager();
        double elapsedTime = level.getTimeManager().getElapsedTimeSinceLastFrame();
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
            throw new GameEngineException("SpecifyWavesToExecuteAction");
        }
    }
}
