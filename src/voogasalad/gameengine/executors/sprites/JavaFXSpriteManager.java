package voogasalad.gameengine.executors.sprites;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

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
    public List<Sprite> getSpritePrototypes(){
        ArrayList<Sprite> spritePrototypes = new ArrayList<>();
        for(Integer prot : mySpritePrototypes.keySet() ){
            spritePrototypes.add(mySpritePrototypes.get(prot));
        }
        return spritePrototypes;
    }

    @Override
    public List<Sprite> getSpritesByArchetype(SpriteArchetype archetype) {
        List<Sprite> archetypeList = new ArrayList<>();
        for (Sprite sprite : myOnScreenSprites) {
            if (sprite.getSpriteArchetype() == archetype) {
                archetypeList.add(sprite);
            }
        }
        return archetypeList;    }

    @Override
    public void makeSpriteFromPrototype(double xPos, double yPos, int prototypeId) throws GameEngineException {
        myOnScreenSprites.add(mySpritePrototypes.get(prototypeId).makeClone(xPos, yPos, mySpritePrototypeIndexGenerator++));
    }

    public void removeSpriteById(int spriteId) {
        myOnScreenSprites.stream().filter(sprite -> sprite.getId() == spriteId).forEach(sprite -> myOnScreenSprites.remove(sprite));
    }

    @Override
    public List<Sprite> getOnScreenSprites() {
        List<Sprite> listCopy = new ArrayList<>();
        for(Sprite sprite: myOnScreenSprites){
            listCopy.add(sprite);
        }
        return listCopy;
    }

    @Override
    public Map<Integer, Sprite> getPrototypesForArchetype(SpriteArchetype archetype) throws GameEngineException {
        Map<Integer, Sprite> spritePrototypesOfArchetype = new HashMap<>();
        for (Integer key : mySpritePrototypes.keySet()) {
            if (mySpritePrototypes.get(key).getSpriteArchetype()==archetype) {
                spritePrototypesOfArchetype.put(key, mySpritePrototypes.get(key).makeClone(0, 0, 0));
            }
        }
        return spritePrototypesOfArchetype;
    }
}
