package voogasalad.gameengine.playerengineapi.sprites;

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
        for (Sprite s : myOnScreenSprites) {
            s.printSpriteInfo();
        }
    }
}
