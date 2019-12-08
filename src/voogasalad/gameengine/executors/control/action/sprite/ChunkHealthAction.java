package voogasalad.gameengine.executors.control.action.sprite;
import voogasalad.gameengine.executors.sprites.Sprite;

public class ChunkHealthAction implements SpriteAction {
    private int damageValue;

    public ChunkHealthAction(int damage) {
        damageValue = damage;
    }

    @Override
    public void execute(Sprite sprite) {
        sprite.chunkHealth(damageValue);
    }
}
