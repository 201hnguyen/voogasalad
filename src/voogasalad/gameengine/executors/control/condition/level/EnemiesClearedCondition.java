package voogasalad.gameengine.executors.control.condition.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.Map;
import java.util.Set;

public class EnemiesClearedCondition extends LevelCondition {

    public EnemiesClearedCondition(ConditionClassification classification, Set<LevelAction> actions) {
        super(classification, actions);
    }

    public EnemiesClearedCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        super (parameters, actions);
    }

    @Override
    public boolean hasHappened(Level level) {
        return ((!level.getWaveManager().hasNextWave()) && level.getSpriteManager().getOnsScreenSpritesByArchetype(SpriteArchetype.ENEMY).size()==0);
    }
}
