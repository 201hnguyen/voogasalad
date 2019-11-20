package voogasalad.gameengine.engine.sprites;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaFXSpriteManager implements SpriteManager {
    private List<Sprite> myOnScreenSprites;
    private Map<Integer, Sprite> mySpritePrototypes;
    private int mySpritePrototypeIndexGenerator;

    public JavaFXSpriteManager() {
        myOnScreenSprites = new ArrayList<>();
        mySpritePrototypes = new HashMap<>();
        mySpritePrototypeIndexGenerator = 0;
    }

    @Override
    public void addSpritePrototype(int prototypeID, Sprite sprite) {
        mySpritePrototypes.put(prototypeID, sprite);
    }

    @Override
    public void makeSpriteFromPrototype(double xPos, double yPos, int prototypeId) throws GameEngineException {
        myOnScreenSprites.add(mySpritePrototypes.get(prototypeId).makeClone(xPos, yPos, mySpritePrototypeIndexGenerator++));
    }

    public void removeSpriteById(int spriteId) {
        myOnScreenSprites.stream().filter(sprite -> sprite.getId() == spriteId).forEach(sprite -> myOnScreenSprites.remove(sprite));
    }

    @Override
    public List<Sprite> getOnScreenSprites() {
        return List.copyOf(myOnScreenSprites);
    }
}
