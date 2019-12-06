package voogasalad.gameengine.executors.sprites;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaFXSpriteManager implements SpriteManager {
    private List<Sprite> myOnScreenSprites;
    private Map<Integer, Sprite> mySpritePrototypes;
    private int mySpriteIdGenerator;
    private LevelActionsRequester myLevelActionsRequester;

    public JavaFXSpriteManager(LevelActionsRequester levelActionsRequester) {
        myOnScreenSprites = new ArrayList<>();
        mySpritePrototypes = new HashMap<>();
        mySpriteIdGenerator = 0;
        myLevelActionsRequester = levelActionsRequester;
    }

    @Override
    public void addSpritePrototype(Sprite sprite) {
        mySpritePrototypes.put(sprite.getPrototypeId(), sprite);
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
    public List<Sprite> getOnsScreenSpritesByArchetype(SpriteArchetype archetype) {
        List<Sprite> archetypeList = new ArrayList<>();
        for (Sprite sprite : myOnScreenSprites) {
            if (sprite.getSpriteArchetype() == archetype) {
                archetypeList.add(sprite);
            }
        }
        return archetypeList;    }

    @Override
    public Sprite makeSpriteFromPrototype(double xPos, double yPos, int prototypeId) throws GameEngineException {
        int spriteId = mySpriteIdGenerator++;
        Sprite sprite = mySpritePrototypes.get(prototypeId).makeClone(xPos, yPos, spriteId);
        myOnScreenSprites.add(sprite);
        return sprite;
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
    public List<Sprite> getPrototypesForArchetype(SpriteArchetype archetype) throws GameEngineException {
        List<Sprite> spritePrototypesOfArchetype = new ArrayList<>();
        for (Integer key : mySpritePrototypes.keySet()) {
            if (mySpritePrototypes.get(key).getSpriteArchetype()==archetype) {
                spritePrototypesOfArchetype.add(mySpritePrototypes.get(key).makeClone(0, 0, 0));
            }
        }
        return spritePrototypesOfArchetype;
    }

    @Override
    public void executeSpriteNextState(double elapsedTime) throws GameEngineException {
        for (Sprite sprite : myOnScreenSprites) {
            sprite.updatePosition(elapsedTime);
            sprite.updateAngle(elapsedTime);
            sprite.shoot(elapsedTime, myLevelActionsRequester);
        }
        System.out.println("executed next sprite state");
    }
}
