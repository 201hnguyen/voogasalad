package voogasalad.gameengine.engine.gamecontrol.action;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.Wave;
import voogasalad.gameengine.engine.sprites.SpriteManager;

public class SpawnWaveAction implements LevelAction {

    private Wave myWave;
    private boolean isFinished;

    @Override
    public void execute(Level level) throws GameEngineException {
        if (myWave == null) {
            setupWave(level);
        }
        spawnNextSprite(level.getSpriteManager(), level.getTimeManager().getElapsedTimeSinceLastFrame());
        checkActionFinished();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    private void spawnNextSprite(SpriteManager spriteManager, double elapsedTime) throws GameEngineException {
        Integer nextSpriteToSpawn = myWave.getNextSpriteToSpawn(elapsedTime);
        if (nextSpriteToSpawn != null) {
            spriteManager.makeSpriteFromPrototype(myWave.getSpawnPoint().getX(), myWave.getSpawnPoint().getY(), nextSpriteToSpawn);
        }
    }

    private void setupWave(Level level) throws GameEngineException {
        if (level.getWaveManager().hasNextWave()) {
            myWave = level.getWaveManager().getNextWave();
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
