package voogasalad.gameengine.engine.sprites;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaFXSpriteManager implements SpriteManager {
    private List<Sprite> myOnScreenSprites;
    private Map<Integer, Sprite> mySpritePrototypes;
    private int mySpriteIDGenerator;

    public JavaFXSpriteManager() {
        myOnScreenSprites = new ArrayList<>();
        mySpritePrototypes = new HashMap<>();
        mySpriteIDGenerator = 0;
    }

    @Override
    public void addSpritePrototype(int prototypeID, Sprite sprite) {
        mySpritePrototypes.put(prototypeID, sprite);
    }

    @Override
    public void makeSpriteFromPrototype(double xPos, double yPos, int prototypeId) throws GameEngineException {
        myOnScreenSprites.add(mySpritePrototypes.get(prototypeId).makeClone(xPos, yPos, mySpriteIDGenerator++));
    }

    @Override
    public List<JavaFXSprite> getOnScreenSprites() {
        List<JavaFXSprite> listCopy = new ArrayList<JavaFXSprite>();
        for(Sprite sprite: myOnScreenSprites){
            listCopy.add((JavaFXSprite) sprite);
        }
        return listCopy;
    }
}
