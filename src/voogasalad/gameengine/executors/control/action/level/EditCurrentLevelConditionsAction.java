package voogasalad.gameengine.executors.control.action.level;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.configurators.ConditionsConfigurator;
import voogasalad.gameengine.executors.control.condition.level.LevelCondition;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EditCurrentLevelConditionsAction implements LevelAction {

    private NodeList myEditedConditionsList;

    public EditCurrentLevelConditionsAction(Element editableObjectRoot) {
        myEditedConditionsList = editableObjectRoot.getElementsByTagName("Condition");
    }

    @Override
    public void execute(Level level) throws GameEngineException {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        Collection<LevelCondition> editedGameConditions = conditionsConfigurator.buildLevelConditionsCollection(myEditedConditionsList);
        Collection<LevelCondition> currentLevelConditions = level.getLevelConditions();
        Set<LevelCondition> originalConditionsToRemove = new HashSet<>();
        for (LevelCondition newCondition : editedGameConditions) {
            for (LevelCondition oldCondition : currentLevelConditions) {
                if (newCondition.getLevelConditionId() == oldCondition.getLevelConditionId()) {
                    originalConditionsToRemove.add(oldCondition);
                }
            }
        }
        currentLevelConditions.removeAll(originalConditionsToRemove);
        currentLevelConditions.addAll(editedGameConditions);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
