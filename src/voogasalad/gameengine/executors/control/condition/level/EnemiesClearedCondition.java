package voogasalad.gameengine.executors.control.condition.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.Map;
import java.util.Set;

public class EnemiesClearedCondition extends LevelCondition {

    public EnemiesClearedCondition(int levelConditionId, ConditionClassification classification, Set<LevelAction> actions) {
        super(levelConditionId, classification, actions);
    }

    public EnemiesClearedCondition(int levelConditionId, Map<String, String> parameters, Set<LevelAction> actions) {
        super (levelConditionId, parameters, actions);
    }

    @Override
    public boolean hasHappened(Level level) {
        System.out.println("TEST ENEMIES CLEARED:" + ((! level.getWaveManager().hasNextWave())) + " " + (level.getSpriteManager().getOnScreenSpritesByArchetype(SpriteArchetype.ENEMY).size()==0));
        return ((! level.getWaveManager().hasNextWave()) && level.getSpriteManager().getOnScreenSpritesByArchetype(SpriteArchetype.ENEMY).size()==0);
    }
}
