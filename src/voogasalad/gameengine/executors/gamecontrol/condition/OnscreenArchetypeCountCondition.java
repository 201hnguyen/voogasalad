package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.Map;
import java.util.Set;

public class OnscreenArchetypeCountCondition implements LevelCondition {

    private int myMarkedCount;
    private Set<LevelAction> myActions;
    private SpriteArchetype myArchetype;
    private ConditionClassification myConditionClassification;

    public OnscreenArchetypeCountCondition(SpriteArchetype archetype, int markedCount, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        myConditionClassification = conditionClassification;
        myArchetype = archetype;
        myMarkedCount = markedCount;
        myActions = actions;
    }

    public OnscreenArchetypeCountCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        myArchetype = SpriteArchetype.valueOf(parameters.get("archetype")); //FIXME: have to do some null checks and not hard code string here
        myMarkedCount = Integer.parseInt(parameters.get("markedcount")); //FIXME: have to do some null checks and not hard code string here
        myConditionClassification = ConditionClassification.valueOf(parameters.get("classification")); //FIXME: have to do some null checks and not hard code string here
        myActions = actions;
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getSpriteManager().getOnsScreenSpritesByArchetype(myArchetype).size() == myMarkedCount;
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
