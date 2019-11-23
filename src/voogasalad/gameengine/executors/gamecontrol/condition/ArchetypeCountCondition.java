package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.Set;

public class ArchetypeCountCondition implements LevelCondition {

    private int myMarkedCount;
    private Set<LevelAction> myActions;
    private SpriteArchetype myArchetype;

    public ArchetypeCountCondition(SpriteArchetype archetype, int markedCount, Set<LevelAction> actions) {
        myArchetype = archetype;
        myMarkedCount = markedCount;
        myActions = actions;
    }
    @Override
    public boolean hasHappened(Level level) {
        return level.getSpriteManager().getSpritesByArchetype(myArchetype).size() == myMarkedCount;
    }

    @Override
    public Set<LevelAction> getActions() {
        return myActions;
    }
}
