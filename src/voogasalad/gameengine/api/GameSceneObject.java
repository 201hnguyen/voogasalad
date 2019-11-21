package voogasalad.gameengine.api;

import voogasalad.gameengine.engine.sprites.JavaFXSprite;
import voogasalad.gameengine.engine.sprites.Sprite;

import java.util.List;

public class GameSceneObject {
    private final int myResources;
    private final int myLives;
    private final List<Sprite> myOnScreenSprites;

    public GameSceneObject(int resources, int lives, List<Sprite> sprites) {
        myResources = resources;
        myLives = lives;
        myOnScreenSprites = sprites;
    }

    public int getResources() {
        return myResources;
    }

    public int getLives() {
        return myLives;
    }

    public List<Sprite> getMyOnScreenSprites() {
        return myOnScreenSprites;
    }

}
