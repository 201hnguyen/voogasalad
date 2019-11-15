package voogasalad.gameengine.engine.sprites;

import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameengine.engine.sprites.SpriteManager;

import java.util.ArrayList;
import java.util.List;

public class JavaFXSpriteManager implements SpriteManager {
    List<Sprite> myOnScreenSprites;

    public JavaFXSpriteManager() {
        myOnScreenSprites = new ArrayList<>();
    }

    @Override
    public void addSprite(Sprite sprite) {
        myOnScreenSprites.add(sprite);

        System.out.println("Current number of sprites: " + myOnScreenSprites.size());
    }
}
