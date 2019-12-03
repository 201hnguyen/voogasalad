package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.Map;
import java.util.Set;

public class EnemiesClearedCondition implements LevelCondition {

    private Set<LevelAction> myActions;
    private ConditionClassification myConditionClassification;

    public EnemiesClearedCondition(ConditionClassification classification, Set<LevelAction> actions) {
        myConditionClassification = classification;
        myActions = actions;
    }

    public EnemiesClearedCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        myConditionClassification = ConditionClassification.valueOf(parameters.get("classification")); //FIXME: have to do some null checks and not hard code string here
        myActions = actions;
    }

    @Override
    public boolean hasHappened(Level level) {
        return ((!level.getWaveManager().hasNextWave()) && level.getSpriteManager().getOnsScreenSpritesByArchetype(SpriteArchetype.ENEMY).size()==0);
    }

    @Override
    public Set<LevelAction> getActions() {
        return myActions;
    }

    @Override
    public ConditionClassification getClassification() {
        return myConditionClassification;
    }
}
