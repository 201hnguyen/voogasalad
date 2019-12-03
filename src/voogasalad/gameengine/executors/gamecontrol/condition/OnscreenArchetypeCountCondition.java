package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.Map;
import java.util.Set;

public class OnscreenArchetypeCountCondition extends LevelCondition {

    private int myMarkedCount;
    private Set<LevelAction> myActions;
    private SpriteArchetype myArchetype;
    private ConditionClassification myConditionClassification;

    public OnscreenArchetypeCountCondition(SpriteArchetype archetype, int markedCount, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        super(conditionClassification, actions);
        myArchetype = archetype;
        myMarkedCount = markedCount;
    }

    public OnscreenArchetypeCountCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        super(parameters, actions);
        myArchetype = SpriteArchetype.valueOf(parameters.get("archetype")); //FIXME: have to do some null checks and not hard code string here;
        myMarkedCount = Integer.parseInt(parameters.get("markedcount")); //FIXME: have to do some null checks and not hard code string here
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getSpriteManager().getOnsScreenSpritesByArchetype(myArchetype).size() == myMarkedCount;
    }
}
