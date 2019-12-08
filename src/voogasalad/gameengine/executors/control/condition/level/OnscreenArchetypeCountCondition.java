package voogasalad.gameengine.executors.control.condition.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.Map;
import java.util.Set;

public class OnscreenArchetypeCountCondition extends LevelCondition {

    public static final String ARCHETYPE_MAP_KEY = "Archetype";
    public static final SpriteArchetype DEFAULT_ARCHETYPE = SpriteArchetype.UNCLASSIFIED;
    public static final String MARKED_COUNT_MAP_KEY = "MarkedCount";
    public static final int DEFAULT_MARKED_COUNT=0;

    private int myMarkedCount;
    private SpriteArchetype myArchetype;

    public OnscreenArchetypeCountCondition(SpriteArchetype archetype, int markedCount, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        super(conditionClassification, actions);
        myArchetype = archetype;
        myMarkedCount = markedCount;
    }

    public OnscreenArchetypeCountCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        super(parameters, actions);
        try {
            myArchetype = SpriteArchetype.valueOf(parameters.get(ARCHETYPE_MAP_KEY));
        } catch (NullPointerException | IllegalArgumentException e) {
            myArchetype = DEFAULT_ARCHETYPE;
        }
        try {
            myMarkedCount = Integer.parseInt(parameters.get(MARKED_COUNT_MAP_KEY));
        } catch (NullPointerException | NumberFormatException e) {
            myMarkedCount = DEFAULT_MARKED_COUNT;
        }
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getSpriteManager().getOnsScreenSpritesByArchetype(myArchetype).size() == myMarkedCount;
    }
}