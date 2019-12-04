package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelWaveManager;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.levelcontrol.Wave;
import voogasalad.gameengine.executors.sprites.SpriteManager;

public class SpawnWaveAction implements LevelAction {

    private Wave myWave;
    private boolean isFinished;

    @Override
    public void execute(Level level) throws GameEngineException {
        SpriteManager spriteManager = level.getSpriteManager();
        LevelWaveManager levelWaveManager = level.getWaveManager();
        double elapsedTime = level.getStatusManager().getElapsedTimeSinceLastFrame();
        if (myWave == null) {
            setupWave(levelWaveManager);
        }
        if (! isFinished) {
            isFinished = myWave.spawnNextSprite(spriteManager, elapsedTime);
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    private void setupWave(LevelWaveManager levelWaveManager) {
        if (levelWaveManager.hasNextWave()) {
            isFinished = false;
            myWave = levelWaveManager.getNextWave();
        } else {
            isFinished = true;
        }
    }
}
