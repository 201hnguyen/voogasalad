package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.HashSet;
import java.util.Set;

public class ChunkAreaAction implements LevelAction {

    private int mySpriteId;
    private int myDamageValue;
    private double myRadius;
    private boolean isFinished;

    public ChunkAreaAction(int spriteId, int damageValue, double radius) {
        mySpriteId = spriteId;
        myDamageValue = damageValue;
        myRadius = radius;
        isFinished = false;
    }

    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite root = level.getSpriteManager().getSpriteById(mySpriteId);
        if(checkValid(root)) {
            Set<Sprite> toChunk = new HashSet<>();
            toChunk.add(root);
            for(Sprite sprite : level.getSpriteManager().getOnScreenSpritesByArchetype(SpriteArchetype.ENEMY)) {
                if(root.distanceTo(sprite) <= myRadius) {
                    toChunk.add(sprite);
                }
            }
            for(Sprite sprite : toChunk) {
                sprite.chunkHealth(myDamageValue);
            }
        }
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    private boolean checkValid(Sprite toCheck) {
        return !(toCheck == null || toCheck.isDead());
    }
}
